package facilitycontents;

import java.util.ArrayList;
import main.InvalidValueException;
public class FacilityService {
	private static FacilityService facilityServiceInstance;
	ArrayList<Facility> facilityList;
	
	private FacilityService(){
		facilityList=FacilityLoaderFactory.getInstance()
				.createLoader()
				.loadFacilities();}
	
	public static FacilityService getInstance()
	{
		if (facilityServiceInstance == null){facilityServiceInstance=new FacilityService();}
		
		return facilityServiceInstance;
	}
	public FacilityOrderDto getOrderDto()
	{
		ArrayList<Facility> temp =new ArrayList<Facility>();
		temp.addAll(facilityList);
		return DtoFactory.getInstance().createOrderDto(temp);
	}
	public FacilityNetworkDto getNetworkDto()
	{
		ArrayList<Facility> temp =new ArrayList<Facility>();
		temp.addAll(facilityList);
		return DtoFactory.getInstance().createNetworkDto(temp);
	}
	public String toString()
	{
		String res= new String();
		for (Facility element: facilityList)
		{
			res=res+element.toString();
		}
		return res;
	}
	public double bookItems(String location, int startDay, int number)
	{
		for(int i=0;i<facilityList.size();i++)
		{
			if(facilityList.get(i).getLocation().compareTo(location)==0)
			{
				return facilityList.get(i).bookItems(number, startDay);
			}
		}
		return -1;
	}
	public void updateQuantity(String location, String item, int number) throws InvalidValueException
	{
		for(int i=0;i<facilityList.size();i++)
		{
			if(facilityList.get(i).getLocation().compareTo(location)==0)
			{
				facilityList.get(i).updateItem(item, number);
			
			}
		}

	}
}
