package ordercontents;

public interface LogisticsRecord {
	public String getItemId();
	public int getQuantity();
	public int getCost();
	public int sourcesUsed();
	public int firstDeliveryDay();
	public int lastDeliveryDay();
	

}
