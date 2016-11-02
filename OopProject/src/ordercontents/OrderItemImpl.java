package ordercontents;

import main.InvalidValueException;
import main.NullValueException;

public class OrderItemImpl implements OrderItem {
	private String itemId;
	private int quantity;
	public OrderItemImpl(String idIn, int quantityIn) throws NullValueException, InvalidValueException
	{
		if(idIn==null){throw new NullValueException("Name cannot be null");}
		if(quantity<0){throw new InvalidValueException("Quantity cannot be negative");}
		this.itemId=idIn;
		this.quantity=quantityIn;
	}
	
	public String getId()
	{
		String temp=new String(itemId);
		return temp;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public String toString()
	{
		String temp = "Item "+itemId+": "+quantity+"\n";
		return temp;
	}

}
