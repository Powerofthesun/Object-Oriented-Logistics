package facilitycontents;

import main.InvalidValueException;
import main.NullValueException;

public class InventoryEntryFactory {
private static InventoryEntryFactory instance;
	
	private InventoryEntryFactory()
	{}
	public static InventoryEntryFactory getInstance()
	{
		if (instance==null){instance=new InventoryEntryFactory();}
		return instance;
	}
	public InventoryEntry createInventoryEntry(String idIn, int quantityIn) throws NullValueException, InvalidValueException
	{
		
		return new InventoryEntryImpl(idIn, quantityIn);
	}

}
