package itemcontents;

import java.util.ArrayList;

import main.NullValueException;

public interface ItemList {

	public void addItem(Item i)throws NullValueException ;
	public ArrayList<Item> getItems();
	public int getItemCost(String itemName);
	

}
