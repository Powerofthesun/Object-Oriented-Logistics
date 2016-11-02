package ordercontents;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import facilitycontents.Facility;
import facilitycontents.FacilityOrderDto;
import facilitycontents.FacilityProperties;
import facilitycontents.FacilityService;
import itemcontents.ItemService;
import main.InvalidValueException;
import main.NullValueException;
import networkcontents.NetworkService;
import networkcontents.PathInfo;

public class OrderProcessingImpl implements OrderProcessing {
	private Map <String, Integer> facilityMap;
	private FacilityOrderDto facilityList;
	//private pathInfo;
	private ArrayList<FacilityRecord> facilityRecords;
	private ArrayList<Double> proratedDays;
	private ArrayList<String> backOrdered;
	
	public String processOrder(Order o)
	{
		ArrayList<LogisticsRecord>lRecordArr= new ArrayList<LogisticsRecord>();
		backOrdered=new ArrayList<String>();
		for(int i = 0; i<o.getItems().size();i++)
		{
			OrderItem currentItem=o.getItems().get(i);
			this.facilityList=FacilityService.getInstance().getOrderDto();
			proratedDays=new ArrayList<Double>();
			ArrayList<Integer> sources=new ArrayList<Integer>();
			int requiredQuantity=currentItem.getQuantity();
			int counter=0;
			
			this.createFacilityRecords(o, i);
			this.sortRecords();
			while(requiredQuantity >0)
			{
				if(counter>=facilityRecords.size())
				{
					backOrdered.add("- "+ requiredQuantity+" of item "+currentItem.getId()+" have been Back-Ordered.");
					break;
				}
				else
				{
					requiredQuantity=this.placeOrder(currentItem.getId(), o.getStartDay(), facilityRecords.get(counter), requiredQuantity);
					counter=counter+1;
				}
			}
			
			for (int q=0; q<counter; q++)
			{
				int temp=calculateCost(facilityRecords.get(q), currentItem.getId(), proratedDays.get(q));
				sources.add(temp);
			}
			
			LogisticsRecord finalRecord=createLogisticsRecord(counter, currentItem.getQuantity(), o.getStartDay(), currentItem.getId(), sources);
			lRecordArr.add(finalRecord);
		}
		return(finalToString(o, lRecordArr));
	}
	
	private LogisticsRecord createLogisticsRecord(int numberIn, int orderNeeded, int startDay, String itemId, ArrayList<Integer> costs)
	{
		LogisticsRecord res=null;
		ArrayList<LogisticsItem> items=new ArrayList<LogisticsItem>();
		int totalCost=0;
		for(int i=0 ;i<numberIn;i++)
		{
			FacilityRecord r=facilityRecords.get(i);
			int rCost=costs.get(i);
			totalCost=totalCost+rCost;
			try
			{
				items.add(LogisticsItemFactory.getInstace()
					.createLogisticsItem(r.getSource(), r.getItemCount(), orderNeeded, startDay, r.getEndDay(), r.getEndDay()+1, r.getArrivalDay(), rCost));
			}
			//this catch does not propagate because for this class, we assume correct data entry. In the real world, this would probably be sent to an error log
			catch(Exception e){e.printStackTrace();}
		}
		
		try {
				res=LogisticsRecordFactory.getInstance().createRecord(itemId, orderNeeded, totalCost, items);
			} 
		//this catch does not propagate because for this class, we assume correct data entry. In the real world, this would probably be sent to an error log
		catch (NullValueException | InvalidValueException e) {e.printStackTrace();} 
		
		return res;
	}
	
	private ArrayList<FacilityRecord> createFacilityRecords(Order o, int i)
	{
		 this.facilityMap=gatherFacilities(o.getItems().get(i).getId(),o.getDestination());
		 facilityRecords=new ArrayList<FacilityRecord>();
		 
		 double hoursPerDay = FacilityProperties.getInstance().getHoursPerDay();
		 double milesPerHour=FacilityProperties.getInstance().getMilesPerHour();
		 
		 for(String s: facilityMap.keySet())
		 {
			 Facility f = this.findFacility(s);
			 int processTime = (int) Math.ceil(facilityMap.get(s)/f.getRate());
			 PathInfo p = NetworkService.getInstance().findShortestPath(o.getDestination(), s);
			 int travelTime=(int)Math.ceil(p.getLength()/(hoursPerDay*milesPerHour));
			 int arrivalDay=o.getStartDay()+travelTime+processTime;
			 
			 facilityRecords.add(FacilityRecordFactory.getInstance().createRecord(s, facilityMap.get(s), o.getStartDay()+processTime, travelTime, arrivalDay));
		 }
		 return facilityRecords;
	}

	private int placeOrder(String item, int startDay, FacilityRecord record, int quantityNeeded)
	{
		int availableQuantity =facilityMap.get(record.getSource());
		int toBeUpdated;
		int toBeBooked;
		if(quantityNeeded>availableQuantity)
		{
			toBeBooked=availableQuantity;
			quantityNeeded=quantityNeeded-availableQuantity;
			toBeUpdated=0;
		}
		else
		{
			try
				{
					record.setItemCount(quantityNeeded);
				}
			//same clean-value assumption made here
			catch(InvalidValueException e){e.printStackTrace();}
			toBeBooked=quantityNeeded;
			toBeUpdated=availableQuantity-quantityNeeded;
			quantityNeeded=0;
		}
		try
		{
			FacilityService.getInstance().updateQuantity(record.getSource(),item , toBeUpdated);
			double temp=FacilityService.getInstance().bookItems(record.getSource(), startDay, toBeBooked);
			proratedDays.add(temp);
			if((int)Math.ceil(temp)>record.getArrivalDay())
				{
					record.updateEndDay(startDay+(int)Math.ceil(temp));
				}
		}
		catch( InvalidValueException e){e.printStackTrace();}
		
		return quantityNeeded;
	}
	
	private int calculateCost(FacilityRecord record, String itemName, double fCost)
	{
		int itemCost=record.getItemCount()*ItemService.getInstance().getItemCost(itemName);
		Facility f= findFacility(record.getSource());
		int  facilityCost=(int) Math.ceil(f.getRate()*fCost);
		int transportationCost=record.getTravelTime()*500;
		return itemCost+facilityCost+transportationCost;
	}
	
	private Map<String, Integer> gatherFacilities(String idIn, String destinationIn)
	{
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		for(Facility f: facilityList.facilities)
		{
			int x=f.containsItem(idIn);
			if(x>0 && f.getLocation().compareTo(destinationIn)!=0)
			{
				res.put(f.getLocation(), x);
			}
		}
		return res;
	}
	
	private Facility findFacility(String id)
	{
		for(Facility f: this.facilityList.facilities)
		{
			if(f.getLocation().compareTo(id)==0)
			{
				return f;
			}
		}
		return null;
	}
	
	private void sortRecords()
	{
		 Collections.sort(this.facilityRecords, new Comparator<FacilityRecord>() {
 		    @Override
 		    public int compare(FacilityRecord o1, FacilityRecord o2) {
 		    	int a=o1.getArrivalDay();
 		    	int b=o2.getArrivalDay();
  		    	return Integer.compare(a, b);
 		    }
 		});
	}
	
	private String finalToString(Order o, ArrayList<LogisticsRecord> l)
	{
		String res="------------------------------------------"
				+ "----------------------------------------\nOrder #"+o.getOrderId().charAt(5)+"\n";
		res=res+"   OrderId:\t"+o.getOrderId()+"\n";
		res=res+"   Order Time:\tDay "+o.getStartDay()+"\n";
		res=res+"   Destination:\t"+o.getDestination()+"\n";
		res=res+"   List of Order Items:\n";
		int totalCost=0;
		int firstDeliveryDay=Integer.MAX_VALUE;
		int lastDeliveryDay=-1;
		for(int i = 0; i<l.size();i++)
		{
			LogisticsRecord temp=l.get(i);
			firstDeliveryDay=(firstDeliveryDay>temp.firstDeliveryDay()?temp.firstDeliveryDay():firstDeliveryDay);
			lastDeliveryDay=(temp.lastDeliveryDay()>lastDeliveryDay?temp.lastDeliveryDay():lastDeliveryDay);
			totalCost=totalCost+l.get(i).getCost();//
			res=res+String.format("\t%d) Item ID:\t%-12sQuantity:%d\n", (i+1),temp.getItemId()+",",temp.getQuantity());
		}
		res=res+"Processing Solution:\n   TotalCost:\t\t$"+NumberFormat.getIntegerInstance().format(totalCost)+"\n";
		res=res+"   1st Delivery Day:\t"+firstDeliveryDay+"\n";
		res=res+"   Last Delivery Day:\t"+lastDeliveryDay+"\n";
		res=res+"   Order Items:\n\tItem ID    Quantity  Cost\t # Sources Used  First Day   Last Day\n";
		for (int x=0;x<l.size();x++)
		{
			LogisticsRecord temp=l.get(x);
			res=res+String.format("     %d) %-11s%-10s$%-11s%-16s%-12s%-5s\n",(x+1), temp.getItemId(),Integer.toString(temp.getQuantity()),NumberFormat.getIntegerInstance().format(temp.getCost()),
					Integer.toString(temp.sourcesUsed()),Integer.toString(temp.firstDeliveryDay()),Integer.toString(temp.lastDeliveryDay()));
		}
		for (int q=0;q<backOrdered.size();q++)
		{
			res=res+"\n"+backOrdered.get(q);
		}
		res=res+"\n----------------------------------------------------------------------------------\n";
		return res;
	}
}