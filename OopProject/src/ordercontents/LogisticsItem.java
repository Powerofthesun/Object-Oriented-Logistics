package ordercontents;

public interface LogisticsItem {
	public String getName();
	public int getQuantityRequested ();
	public int getTotalQuantity();
	public int getProcessingStart();
	public int getProcessingEnd();
	public int getTransportationStart();
	public int getTransportationEnd();
	public int getCost();
}
