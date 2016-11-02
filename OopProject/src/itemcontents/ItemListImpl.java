package itemcontents;

import java.util.ArrayList;
import main.NullValueException;

public class ItemListImpl implements ItemList
{
private ArrayList<Item> itemList;
public ItemListImpl(){itemList=new ArrayList<Item>();}
public ItemListImpl(ArrayList<Item> itemIn) throws NullValueException
{
	itemList=new ArrayList<Item>();
	for (Item i: itemIn)
	{
		addItem(i);
	}
}
public void addItem(Item itemIn) throws NullValueException {
	if (itemIn==null){throw new NullValueException();}
	itemList.add(itemIn);
}
public ArrayList<Item> getItems() {
	ArrayList<Item> temp = new ArrayList<Item>();
	
	for (Item i: itemList)
	{
		try{
		temp.add(ItemFactory.getInstance().createItem(i.getId(), i.getPrice()));} catch(Exception e){e.printStackTrace();}
	}
	return temp;
	}
	public int getItemCost(String itemName)
	{
		int temp=-1;
		for(Item i: itemList)
		{
			if (i.getId().compareTo(itemName)==0)
			{
				temp=i.getPrice();
			}
		}
		return temp;
	}

}
