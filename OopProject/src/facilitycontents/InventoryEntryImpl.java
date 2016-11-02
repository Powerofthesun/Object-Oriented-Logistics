package facilitycontents;

import main.InvalidValueException;
import main.NullValueException;

public class InventoryEntryImpl implements InventoryEntry {
	private final String id;
	private int quantity;
	public InventoryEntryImpl(String idIn, int quantityIn) throws NullValueException, InvalidValueException
	{
		if (idIn==null){throw new NullValueException("ID cannot be null");}
		if(quantityIn<0){throw new InvalidValueException("Quantity cannot be negative");}
		id=idIn;
		quantity=quantityIn;
	}
	public String getId(){return new String(id);}
	public int getQuantity(){return quantity;}
	public String toString()
	{
		String temp = (id.length()<8?"\t\t":"\t");
		return String.format("%s%S%d\n", id, temp, quantity);
	}
	public void updateQuantity( int quantityIn) throws InvalidValueException
	{
		if(quantityIn<0){throw new InvalidValueException("Quantity cannot be negative");}
		quantity=quantityIn;
	}

}
