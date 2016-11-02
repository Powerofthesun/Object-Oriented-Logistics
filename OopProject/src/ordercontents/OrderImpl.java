package ordercontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class OrderImpl implements Order{
	private String orderId;
	private int startDay;
	private String destination;
	private ArrayList<OrderItem> itemList;

	public OrderImpl(String orderIdIn,  int startDayIn,String destinationIn, ArrayList<OrderItem> itemListIn) throws NullValueException, InvalidValueException
	{
		if(orderIdIn==null){throw new NullValueException("OrderID cannot be null");}
		if(startDayIn<1){throw new InvalidValueException("Start day must be greater than 1");}
		if(destinationIn==null){throw new NullValueException("destination cannot be null");}
		if(itemListIn==null){throw new NullValueException("Itemlist cannot be null");}
		
		this.orderId= orderIdIn;
		this.startDay=startDayIn;
		this.destination=destinationIn;
		this.itemList=itemListIn;
	}
	public String getOrderId()
	{
		String temp = new String (orderId);
		return temp;
	}
	
	public int getStartDay()
	{
		return this.startDay;
	}
	public String getDestination()
	{
		String temp = new String (destination);
		return temp;
	}
	public ArrayList<OrderItem> getItems()
	{
		ArrayList<OrderItem> temp = new ArrayList<OrderItem>();
		for(OrderItem o: this.itemList)
		{
			try{
			temp.add(OrderItemFactory.getInstance().createItem(o.getId(), o.getQuantity()));}catch(Exception e){e.printStackTrace();}
		}
		return temp;
	}
	public String toString()
	{
		String temp = "Order "+orderId+" :\n";
		temp=temp+"Start Day: "+startDay+"\tDestination: "+destination+"\n";
		temp=temp+"Items: \n";
		for (OrderItem o: itemList)
		{
			temp=temp+o.toString();
		}
		return temp+"\n\n";
	}
}
