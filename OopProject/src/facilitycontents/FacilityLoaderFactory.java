package facilitycontents;

public class FacilityLoaderFactory {
private static FacilityLoaderFactory facilityLoaderInstance;
	
	private FacilityLoaderFactory(){}
	
	public static FacilityLoaderFactory getInstance()
	{
		if (facilityLoaderInstance==null)
		{facilityLoaderInstance=new FacilityLoaderFactory();}
		return facilityLoaderInstance;
	}
	
	
	public FacilityLoader createLoader()
	{
		String fileType = FacilityProperties.getInstance().getFileType();
		if(fileType.compareTo("xml")==0)
		{
			return new FacilityXmlLoader();
		}
		else{return null;}
	}

}
