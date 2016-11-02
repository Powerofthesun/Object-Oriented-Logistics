package ordercontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class LogisticsRecordFactory {
	private static LogisticsRecordFactory instance;
	
	private LogisticsRecordFactory(){}
	public static LogisticsRecordFactory getInstance()
	{
		if (instance==null){instance= new LogisticsRecordFactory();}
		return instance;
	}
	public LogisticsRecord createRecord(String itemIdIn, int quantityIn, int totalCostIn, ArrayList<LogisticsItem> logisticDetailsIn) throws NullValueException, InvalidValueException
	{
		return new LogisticsRecordImpl( itemIdIn,  quantityIn,  totalCostIn, logisticDetailsIn);
	}

}
