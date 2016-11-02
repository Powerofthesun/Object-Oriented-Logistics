package itemcontents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ItemLoaderProperties {
	private static ItemLoaderProperties instance;
	private String filename;
	private Properties properties;
	private ItemLoaderProperties()
	{
		properties = new Properties();
		try {
			properties.load(new FileInputStream("items.properties"));
		} catch (IOException e) {
			e.printStackTrace();}
		filename=properties.getProperty("filename");
	}
	
	public static ItemLoaderProperties getInstance()
	{
		if (instance==null){instance=new ItemLoaderProperties();}
		return instance;
	}
	public String getFilename(){
		
		return new String(filename);
	}
	public String getFileType()
	{
		return properties.getProperty("fileType");
	}
}
