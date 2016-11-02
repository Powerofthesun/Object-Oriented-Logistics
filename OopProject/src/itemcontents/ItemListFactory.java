package itemcontents;

import java.util.ArrayList;
import main.InvalidValueException;
import main.NullValueException;

public class ItemListFactory {
	private static ItemListFactory instance;
	
	private ItemListFactory()
	{}
	public static ItemListFactory getInstance()
	{
		if (instance==null){instance=new ItemListFactory();}
		return instance;
	}
	public ItemList createList(ArrayList<Item> items) throws NullValueException, InvalidValueException
	{
		return new ItemListImpl(items);
	}
	public ItemList createList()
	{
		return new ItemListImpl();
	}
}
