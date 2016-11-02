package facilitycontents;


import main.InvalidValueException;
import main.NullValueException;

public class FacilityFactory 
{
	private static FacilityFactory facilityFactoryInstance;
	
	private FacilityFactory()
	{}
	
	public static FacilityFactory getInstance()
	{
		if (facilityFactoryInstance==null){facilityFactoryInstance=new FacilityFactory();}
		return facilityFactoryInstance;
	}
	public Facility createFacility(String locationIn,int rateIn,double costIn,InventoryList inventoryIn, LinkList linksIn) throws NullValueException, InvalidValueException
	{
		if(inventoryIn==null||linksIn==null){System.out.println("Cannot Create facility");throw new NullValueException("Cannot Create Facility");}

		return new FacilityImpl(locationIn,rateIn,costIn,inventoryIn, linksIn);
	}

}
