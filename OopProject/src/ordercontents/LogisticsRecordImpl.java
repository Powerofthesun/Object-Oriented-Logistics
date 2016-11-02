package ordercontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class LogisticsRecordImpl implements LogisticsRecord {
	private String itemId;
	private int quantity;
	private int totalCost;
	private ArrayList<LogisticsItem> logisticDetails;
	public LogisticsRecordImpl(String itemIdIn, int quantityIn, int totalCostIn, ArrayList<LogisticsItem> logisticDetailsIn) throws NullValueException, InvalidValueException
	{
		if (itemIdIn==null||logisticDetailsIn==null){throw new NullValueException("Cannot Add null value!");}
		if(quantityIn<0||totalCostIn<0){throw new InvalidValueException("Cannot use negative value");}
		this.quantity=quantityIn;
		this.totalCost=totalCostIn;
		this.itemId=itemIdIn;
		this.logisticDetails=logisticDetailsIn;
	}
	public String getItemId()
	{
		String temp = new String(itemId);
		return temp;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public int getCost()
	{
		return this.totalCost;
	}
	public int sourcesUsed()
	{
		return this.logisticDetails.size();
	}
	public int firstDeliveryDay()
	{
		
		return logisticDetails.get(0).getTransportationEnd();
	}
	public int lastDeliveryDay()
	{
		
		return logisticDetails.get(logisticDetails.size()-1).getTransportationEnd();
	}

}
