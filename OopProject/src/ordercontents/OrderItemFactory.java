package ordercontents;

import main.InvalidValueException;
import main.NullValueException;

public class OrderItemFactory {
	private static OrderItemFactory instance;
	private OrderItemFactory(){}
	
	public static OrderItemFactory getInstance()
	{
		if (instance==null)
		{
			instance=new OrderItemFactory();
		}
		return instance;
	}
	public OrderItem createItem(String idIn, int quantityIn) throws NullValueException, InvalidValueException
	{
		return new OrderItemImpl(idIn, quantityIn);
	}

}
