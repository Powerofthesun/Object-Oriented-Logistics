package ordercontents;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class OrderLoaderProperties {
	

		private static OrderLoaderProperties instance;
		private String filename;
		private String filetype;
		private Properties properties;
		private OrderLoaderProperties()
		{
			properties = new Properties();
			try {
				properties.load(new FileInputStream("orders.properties"));
			} catch (IOException e) {
				e.printStackTrace();}
			filename=properties.getProperty("filename");
			filetype=properties.getProperty("fileType");
		}
		
		public static OrderLoaderProperties getInstance()
		{
			if (instance==null){instance=new OrderLoaderProperties();}
			return instance;
		}
		public String getFilename(){
			
			return new String(filename);
		}
		public String getFileType()
		{
			return new String(filetype);
		}
}



