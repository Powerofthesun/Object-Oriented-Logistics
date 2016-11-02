package facilitycontents;
import java.util.ArrayList;

import main.InvalidValueException;
public interface Facility {
	public ArrayList<Link> getLinks();
	public ArrayList<InventoryEntry> getInventoryEntries();
	public String getLocation();
	public int getRate();
	public double getCost();
	public int containsItem(String idIn);
	public double bookItems(int orderCount, int startDay);
	public void updateItem(String item, int quantity)throws InvalidValueException;
	public String toString();
}
