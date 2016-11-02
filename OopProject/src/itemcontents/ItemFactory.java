package itemcontents;

import main.InvalidValueException;
import main.NullValueException;

public class ItemFactory {
	private static ItemFactory instance;
	
	private ItemFactory()
	{}
	public static ItemFactory getInstance()
	{
		if (instance==null){instance=new ItemFactory();}
		return instance;
	}
	public Item createItem(String idIn, int priceIn) throws InvalidValueException, NullValueException
	{
		return new ItemImpl(idIn, priceIn);
	}

}
