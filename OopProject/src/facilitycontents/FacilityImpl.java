package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class FacilityImpl implements Facility 
{

	private String location;
	private double cost;
	private int rate;
	private InventoryList inventory;
	private LinkList links;
	private Schedule schedule;
	
	public FacilityImpl(String locationIn,int rateIn,double costIn,InventoryList inventoryIn, LinkList linksIn) throws NullValueException, InvalidValueException
	{
		if(rateIn<0){throw new InvalidValueException("Rate must not be negative");}
		if(costIn<0){throw new InvalidValueException("Cost must not be negative");}
		if(inventoryIn == null || linksIn==null){System.out.println("Its this");throw new NullValueException("inventory or links were an issue");}	
		location=locationIn;
		rate=rateIn;
		cost=costIn;
		inventory=inventoryIn;
		links=linksIn;
		schedule=new ScheduleImpl(rate);
	}
	
	public String getLocation()
	{
		String temp = new String(location);
		return temp;
	}

	public ArrayList<Link> getLinks() {
		//implemented so that DTO can call it
		ArrayList<Link> temp = new ArrayList<Link>();
		temp.addAll(links.getLinks());
		return temp;
	}
	public ArrayList<InventoryEntry> getInventoryEntries() {
		//implemented so that DTO can call it
		ArrayList<InventoryEntry> temp = new ArrayList<InventoryEntry>();
		for(InventoryEntry i: inventory.getInventory())
		{
			try{
			temp.add(InventoryEntryFactory.getInstance().createInventoryEntry(i.getId(), i.getQuantity()));}
			catch(Exception e){e.printStackTrace();}
		}
		return temp;
	}
	public int getRate()
	{
		return rate;
	}
	public double getCost()
	{
		return cost;
	}
	public ArrayList<Integer> getSchedule()
	{
		//full copy to prevent reference sharing//
		return null;
	}
	public double bookItems(int orderCount, int startDay)
	{
		return schedule.bookItems(orderCount, startDay);
		
	}
	
	private String depletedItems()
	{
		String res="";
		for (InventoryEntry element: inventory.getInventory())
		{
			if (element.getQuantity()==0){res=res+element.getId()+" ";}
		}
		return (res.compareTo("")==0?null:res);
	}
	public int containsItem(String idIn)
	{
		InventoryEntry temp = this.inventory.getItem(idIn);
		if(temp == null){return -1;}
		return temp.getQuantity();
	}
	public void updateItem(String item, int quantity) throws InvalidValueException
	{
		inventory.updateItem(item, quantity);
	}
	public String toString()
	{
		//formats facility output for abstraction at higher levels
		String res = "--------------------------------------------------------------------------------- \n"
				+location
				+"\n-----------\n";
		res=res+String.format("Rate per Day: %d\nCost per Day: %.1f\n\n", rate, cost);
		res=res+"Direct Links:\n";
		ArrayList<Link> tempLinks=links.getLinks();
		for (int i = 0; i<tempLinks.size(); i++)
		{
			res=res+String.format("%s; ", tempLinks.get(i).toString());
		}
		
		res=res+"\n\nActive Inventory:\n\tItem ID\t\tQuantity\n";
		ArrayList<InventoryEntry> tempInventory=inventory.getInventory();
		for (int i = 0; i<tempInventory.size(); i++)
		{
			res=res+"\t"+tempInventory.get(i).toString();
		}
		
		res=res+String.format("Depleted (Used-Up) Inventory: %s\n",(depletedItems()==null ? "None": depletedItems()));
		res=res+schedule.toString();
		
		res=res+"\n------------------------------------------------"
				+ "--------------------------------- \n\n";
		
//		
//		res=res+"TRIAL \n\n";
//	
//		res=res+"\n"+schedule.bookItems(15, 24);
//		res=res+schedule.toString();
//		res=res+"\n"+schedule.bookItems(17, 24);
//		res=res+"\n"+schedule.toString();
		return res;		
	}
	
}
