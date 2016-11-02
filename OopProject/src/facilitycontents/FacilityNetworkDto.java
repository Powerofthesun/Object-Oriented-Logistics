package facilitycontents;

import java.util.ArrayList;
import java.util.Map;


public class FacilityNetworkDto 
{
	public Map<String, ArrayList<Link>> links;
	public int milesPerHour;
	public int hoursPerDay;
	
	public FacilityNetworkDto(Map<String, ArrayList<Link>> linksIn, int milesPerHourIn, int hoursPerDayIn) 
	{
		if(milesPerHour<0){milesPerHourIn=0;}
		if(hoursPerDay<0){hoursPerDay=0;}
		 links=linksIn;
		 milesPerHour=milesPerHourIn;
		 hoursPerDay=hoursPerDayIn;
	}

}
