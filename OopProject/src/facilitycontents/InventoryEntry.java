package facilitycontents;

import main.InvalidValueException;

public interface InventoryEntry {
	public String getId();
	public int getQuantity();
	public String toString();
	public void updateQuantity( int quantityIn) throws InvalidValueException;

}
