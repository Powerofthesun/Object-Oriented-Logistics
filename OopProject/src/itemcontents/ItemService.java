package itemcontents;

public class ItemService {
	private static ItemService itemServiceInstance;
	ItemList itemList;
	
	private ItemService(){
		itemList=ItemLoaderFactory.getInstance()
				.createLoader()
				.loadItems();}
	
	public static ItemService getInstance()
	{
		if (itemServiceInstance == null){itemServiceInstance=new ItemService();}
		
		return itemServiceInstance;
	}
	public int getItemCost(String itemName)
	{
		return this.itemList.getItemCost(itemName);
	}
	public String toString()
	{
		int counter = 0;
		String res = "Item Catalog:\n";
		for(Item element : itemList.getItems())
		{
			res=res+element.toString()+"\t";
			if(counter%4==3){res=res+"\n";}
			counter++;
		}
		return res;
	}

}
