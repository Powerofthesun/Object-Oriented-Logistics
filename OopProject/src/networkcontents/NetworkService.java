package networkcontents;

import java.util.ArrayList;
import java.util.Map;
import facilitycontents.FacilityNetworkDto;
import facilitycontents.FacilityService;
import facilitycontents.Link;
import main.NullValueException;

public class NetworkService {
	private static NetworkService instance;
	Map<String, ArrayList<Link>> nodes;
	private int milesPerHour;
	private int hoursPerDay;
	private NetworkService() 
	{
		FacilityNetworkDto temp=FacilityService.getInstance().getNetworkDto();
		nodes=temp.links;
		milesPerHour=temp.milesPerHour;
		hoursPerDay=temp.hoursPerDay;
	}
	
	public static NetworkService getInstance()
	{
		if (instance==null){instance=new NetworkService();}
		return instance;
	}
	
	public PathInfo findShortestPath(String city1, String city2)
	{
		return ShortestPath.calculate(nodes, city1, city2);
	}
	
	public  String testCases()
	{
		String result;
		try{
		result= new NetworkTest().runTest(milesPerHour, hoursPerDay);}
		catch(NullValueException e)
		{
			e.printStackTrace();
			result = "Cannot Complete Network Test";
		}
		return result;
	}
	
}
