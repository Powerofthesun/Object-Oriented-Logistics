package ordercontents;

import main.InvalidValueException;
import main.NullValueException;

public class LogisticsItemFactory {
	private static  LogisticsItemFactory instance;
	private LogisticsItemFactory(){}
	public static LogisticsItemFactory getInstace()
	{
		if(instance==null){instance=new LogisticsItemFactory();}
		return instance;
	}
	public LogisticsItem createLogisticsItem(String nameIn,int quantityRequestedIn, 
			int totalQuantityIn, int pStartIn,int pEndIn, int tStartIn, int tEndIn, int costIn) throws InvalidValueException, NullValueException
	{
		return new LogisticsItemImpl( nameIn, quantityRequestedIn, 
				 totalQuantityIn,  pStartIn, pEndIn,  tStartIn,  tEndIn,  costIn);
	}

}
