package ordercontents;

public class OrderLoaderFactory {
	private static OrderLoaderFactory instance;
	private OrderLoaderFactory(){}
	public static OrderLoaderFactory getInstance()
	{
		if(instance==null){instance=new OrderLoaderFactory();}
		return instance;
	}
	public OrderLoader getLoader()
	{
		String filetype=OrderLoaderProperties.getInstance().getFileType();
		if(filetype.compareTo("xml")==0)
		{return new OrderXmlLoader();}
		return null;
	}

}
