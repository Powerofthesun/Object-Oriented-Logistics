package ordercontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class OrderFactory {
	private static OrderFactory instance;
	private OrderFactory(){}
	
	public static OrderFactory getInstance()
	{
		if(instance==null){instance=new OrderFactory();}
		return instance;
	}
	public Order createOrder(String orderIdIn,  int startDayIn,String destinationIn, ArrayList<OrderItem> itemListIn) throws NullValueException, InvalidValueException
	{
		return new OrderImpl( orderIdIn,   startDayIn, destinationIn, itemListIn);
	}

}
