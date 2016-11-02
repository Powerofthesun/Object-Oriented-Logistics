package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public interface InventoryList {
	public void addEntry(InventoryEntry e)throws NullValueException ;
	public ArrayList<InventoryEntry> getInventory();
	public InventoryEntry getItem(String idIn);
	public void updateItem(String item, int quantity) throws InvalidValueException;

}
