package facilitycontents;

import main.InvalidValueException;
import main.NullValueException;

public class LinkFactory {
private static LinkFactory instance;
	
	private LinkFactory()
	{}
	public static LinkFactory getInstance()
	{
		if (instance==null){instance=new LinkFactory();}
		return instance;
	}
	public Link createLink(String cityIn, int distanceIn) throws NullValueException, InvalidValueException
	{
		return new LinkImpl(cityIn, distanceIn);
	}


}
