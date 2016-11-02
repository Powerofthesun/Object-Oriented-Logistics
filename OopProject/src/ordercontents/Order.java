package ordercontents;

import java.util.ArrayList;

public interface Order {
	public String getOrderId();
	public int getStartDay();
	public String getDestination();
	public ArrayList<OrderItem> getItems();
	public String toString();

}
