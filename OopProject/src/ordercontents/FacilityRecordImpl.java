package ordercontents;

import main.InvalidValueException;
import main.NullValueException;

public class FacilityRecordImpl implements FacilityRecord {
	private String source;
	private int itemCount;
	private int endDay;
	private int travelTime;
	private int arrivalDay;
	public FacilityRecordImpl(String source, int itemCount, int endDay, int travelTime, int arrivalDay) throws InvalidValueException, NullValueException
	{
		if (source==null)
		{
			throw new NullValueException("Cannot have null source");
		}
		if(itemCount<0||endDay<0||travelTime<0||arrivalDay<0)
		{
			throw new InvalidValueException("Cannot have negative numbers for record");
		}
		
		this.source=source;
		this.itemCount=itemCount;
		this.endDay=endDay;
		this.travelTime=travelTime;
		this.arrivalDay=arrivalDay;
	}
	public String getSource()
	{
		String temp = new String(source);
		return temp;
	}
	public int getItemCount()
	{
		return itemCount;
	}
	public int getEndDay()
	{
		return endDay;
	}
	public int getTravelTime()
	{
		return travelTime;
	}
	public int getArrivalDay()
	{
		return arrivalDay;
	}
	public void setItemCount(int newCount) throws InvalidValueException
	{
		if(itemCount<0){throw new InvalidValueException("Cannot have negative itemCount for record");}
	}
	public void updateEndDay(int newDay) throws InvalidValueException
	{
		if(newDay<0){throw new InvalidValueException("Cannot have negative arrival day for record");}
		endDay=newDay;
		arrivalDay=endDay+travelTime;
	}
	public String toString()
	{
		String res="Record:\n";
		res=res+"Source: "+source+"\n";
		res=res+"Item count: "+itemCount+"\n";
		res=res+"End Day: "+endDay+"\n";
		res=res+"Travel time : "+travelTime+"\n";
		res=res+"arrival day: "+arrivalDay+"\n";
		return res;
	}
}

