package itemcontents;

public class ItemLoaderFactory {
	private static ItemLoaderFactory itemLoaderInstance;
	
	private ItemLoaderFactory(){}
	
	public static ItemLoaderFactory getInstance()
	{
		if (itemLoaderInstance==null)
		{itemLoaderInstance=new ItemLoaderFactory();}
		return itemLoaderInstance;
	}
	
	
	public ItemLoader createLoader()
	{
		String fileType = ItemLoaderProperties.getInstance().getFileType();
		if(fileType.compareTo("xml")==0)
		{
			return new ItemXmlLoader();
		}
		else{return null;}
	}

}
