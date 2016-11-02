package ordercontents;

public class FacilityRecordFactory {
	public static FacilityRecordFactory instance;
	private FacilityRecordFactory(){}
	
	public static FacilityRecordFactory getInstance()
	{
		if (instance==null){instance = new FacilityRecordFactory();}
		return instance;
	}
	public FacilityRecord createRecord(String source, int itemCount, int endDay, int travelTime, int arrivalDay)
	{
		try{
		return new FacilityRecordImpl( source,  itemCount,  endDay,  travelTime,  arrivalDay);}
		catch(Exception e){e.printStackTrace();return null;}
	}
}
