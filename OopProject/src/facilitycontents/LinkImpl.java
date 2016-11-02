package facilitycontents;

import main.InvalidValueException;
import main.NullValueException;

public class LinkImpl implements Link {
	private String city;
	private int distance;
	public LinkImpl(String cityIn, int distanceIn) throws NullValueException, InvalidValueException
	{
		if(cityIn==null){throw new NullValueException("City must have a name");}
		if(distance<0){throw new InvalidValueException("Distance cannot be negative");}
		city=cityIn;
		distance=distanceIn;
	}
	
	public String getCity()
	{
		String temp= new String(city);
		return temp;}
	public int getDistance()
	{
		return distance;
	}
	public String toString()
	{
		int milesPerHour = FacilityProperties.getInstance().getMilesPerHour();
		int hoursPerDay=FacilityProperties.getInstance().getHoursPerDay();
		float temp=distance;
		temp=temp/(milesPerHour*hoursPerDay);
		return (""+city+String.format(" (%.1fd)", temp));
	}
}
