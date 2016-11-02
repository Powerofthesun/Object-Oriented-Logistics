package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class InventoryListFactory {
	private static InventoryListFactory instance;
	
	private InventoryListFactory()
	{}
	public static InventoryListFactory getInstance()
	{
		if (instance==null){instance=new InventoryListFactory();}
		return instance;
	}
	public InventoryList createList(ArrayList<InventoryEntry> entries) throws NullValueException, InvalidValueException
	{
		return new InventoryListImpl(entries);
	}
	public InventoryList createList()
	{
		return new InventoryListImpl();
	}
	

}
