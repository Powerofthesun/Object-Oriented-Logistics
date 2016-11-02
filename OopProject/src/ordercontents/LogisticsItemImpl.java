package ordercontents;

import main.InvalidValueException;
import main.NullValueException;

public class LogisticsItemImpl implements LogisticsItem {
	private String name;
	private int cost;
	private int quantityRequested;
	private int totalQuantity;
	private int pStart;
	private int pEnd;
	private int tStart;
	private int tEnd;
	
	public LogisticsItemImpl(String nameIn,int quantityRequestedIn, 
					int totalQuantityIn, int pStartIn,int pEndIn, int tStartIn, int tEndIn, int costIn) throws InvalidValueException, NullValueException
	{
		if(quantityRequestedIn<0|| totalQuantityIn<0|| pStartIn<0|| pEndIn<0 || tStartIn<0||tEndIn<0||costIn<0)
		{
			throw new InvalidValueException("Cannot use negative parameters");
		}
		if (nameIn==null)
		{
			throw new NullValueException("Cannot add null value");
		}
		
		name=nameIn;
		quantityRequested=quantityRequestedIn;
		totalQuantity=totalQuantityIn;
		pStart=pStartIn;
		pEnd=pEndIn;
		tStart=tStartIn;
		tEnd=tEndIn;
		cost=costIn;
	}
	public int getCost()
	{
		return this.cost;
	}
	public String getName()
	{
		String temp=new String(this.name);
		return temp;
	}
	public int getQuantityRequested ()
	{
		return this.quantityRequested;
	}
	public int getTotalQuantity()
	{
		return this.totalQuantity;
	}
	public int getProcessingStart()
	{
		return this.pStart;
	}
	public int getProcessingEnd()
	{
		return this.pEnd;
	}
	public int getTransportationStart()
	{
		return this.tStart;
	}
	public int getTransportationEnd()
	{
		return this.tEnd;
	}
}
