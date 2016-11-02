package facilitycontents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public  class DtoFactory {
	private static DtoFactory instance;
	
	private DtoFactory(){}

	public static DtoFactory getInstance()
	{
		if (instance==null){instance=new DtoFactory();}
		return instance;
	}
	
	public FacilityNetworkDto createNetworkDto( ArrayList<Facility> facilityList )
	{
		Map<String, ArrayList<Link>>temp = new HashMap<String, ArrayList<Link>>();
		for (Facility element: facilityList)
		{
			temp.put(element.getLocation(), element.getLinks());
		}
		int hoursPerDay = FacilityProperties.getInstance().getHoursPerDay();
		int milesPerHour=FacilityProperties.getInstance().getMilesPerHour();
		return new FacilityNetworkDto(temp, milesPerHour, hoursPerDay) ;
	}
	public FacilityOrderDto createOrderDto(ArrayList<Facility> facilityList)
	{
		return new FacilityOrderDto(facilityList);
	}

}
