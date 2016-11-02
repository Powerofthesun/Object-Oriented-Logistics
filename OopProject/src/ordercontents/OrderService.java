package ordercontents;

import java.util.ArrayList;

public class OrderService {
	private static OrderService instance;
	ArrayList<Order> orders;
	private OrderService()
	{
		orders= OrderLoaderFactory.getInstance().getLoader().loadOrders();
	}
	public static OrderService getInstance()
	{
		if (instance==null){instance= new OrderService();}
		return instance;
	}
	public String processOrders()
	{
		String res="Logistics Records: \n";
		OrderProcessingImpl temp = new OrderProcessingImpl();
		for(Order o: orders){
			res=res+temp.processOrder(o);}
		return res;
	}
	public String toString()
	{
		String res="Orders: \n";
		for (Order o: orders)
		{
			res=res+o.toString();
		}
		return res;
	}
	
	

}
