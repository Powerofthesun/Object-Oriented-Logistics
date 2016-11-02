package facilitycontents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FacilityProperties {
	private static FacilityProperties instance;
	private Properties properties;
	private FacilityProperties()
	{
		 properties= new Properties();
		try {
			properties.load(new FileInputStream("facilities.properties"));
		} catch (IOException e) {
			e.printStackTrace();}
	}
	
	public static FacilityProperties getInstance()
	{
		if (instance==null){instance=new FacilityProperties();}
		return instance;
	}
	public String getFilename(){
		
		return properties.getProperty("filename");
	}
	public String getFileType()
	{
		return properties.getProperty("fileType");
	}
	public int getMilesPerHour()
	{
		return Integer.parseInt(properties.getProperty("milesPerHour"));
	}
	public int getHoursPerDay()
	{
		return Integer.parseInt(properties.getProperty("hoursPerDay"));
	}
	
}
