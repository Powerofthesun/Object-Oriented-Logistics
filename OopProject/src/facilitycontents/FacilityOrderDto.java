package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class FacilityOrderDto {
	public ArrayList<Facility> facilities;
	public FacilityOrderDto(ArrayList<Facility> facilitiesIn)
	{
		facilities=new ArrayList<Facility>();
		for (Facility f: facilitiesIn)
		{
			InventoryList temp = null;
			LinkList temp2=null;
			Facility temp3;
			try{
				temp=InventoryListFactory.getInstance().createList(f.getInventoryEntries());
				temp2=LinkListFactory.getInstance().createList(f.getLinks());
				temp3= FacilityFactory.getInstance().createFacility(
							f.getLocation(), f.getRate(), f.getCost(),temp,
							temp2);
			this.facilities.add(temp3);
			}
			catch(InvalidValueException|NullValueException e){e.printStackTrace();}
			
		}
	}

}
