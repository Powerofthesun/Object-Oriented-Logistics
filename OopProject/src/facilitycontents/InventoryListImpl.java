package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class InventoryListImpl implements InventoryList {
	private ArrayList<InventoryEntry> inventoryList;
	public InventoryListImpl(){inventoryList=new ArrayList<InventoryEntry>();}
	public InventoryListImpl(ArrayList<InventoryEntry> entriesIn) throws NullValueException
	{
		inventoryList=new ArrayList<InventoryEntry>();
		for (InventoryEntry e: entriesIn)
		{
			addEntry(e);
		}
	}
	public void addEntry(InventoryEntry iIn) throws NullValueException {
		if (iIn==null){throw new NullValueException("Cannot have null link");}
		inventoryList.add(iIn);
	}
	public ArrayList<InventoryEntry> getInventory() {
		ArrayList<InventoryEntry> temp = new ArrayList<InventoryEntry>();
		
		for (InventoryEntry entry: inventoryList)
		{
			try{
			temp.add(InventoryEntryFactory.getInstance().createInventoryEntry(entry.getId(), entry.getQuantity()));} catch(Exception e){e.printStackTrace();}
		}
		return temp;
	}
	public InventoryEntry getItem(String idIn)
	{
		InventoryEntry temp=null;
		for (InventoryEntry i:inventoryList)
		{
			if(i.getId().compareTo(idIn)==0)
			{
				try{
				temp=InventoryEntryFactory.getInstance().createInventoryEntry(i.getId(), i.getQuantity());}
				catch(Exception e){e.printStackTrace();}
				return temp;
			}
		}
		return temp;
	}
	public void updateItem(String item, int quantity) throws InvalidValueException
	{
		for(InventoryEntry i: inventoryList)
		{
			if(i.getId().compareTo(item)==0)
			{
				i.updateQuantity( quantity);
			}
		}
	}


}
