package itemcontents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;

import main.InvalidValueException;
import main.NullValueException;

public class ItemXmlLoader implements ItemLoader {
	static ArrayList<Item> itemList;
	static ItemList result;
	public  ItemList loadItems() {
        try {
        	//gathering filename from properties singleton//
        	String fileName = ItemLoaderProperties.getInstance().getFilename();
        	//creating document builder//
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            //does filename exist?//
            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            //parsing variables
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            itemList=new ArrayList<Item>();
            result=ItemListFactory.getInstance().createList();
            NodeList itemCatalog = doc.getDocumentElement().getChildNodes();
            //go through entries to find all "items"
            for (int i = 0; i < itemCatalog.getLength(); i++) {
                if (itemCatalog.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = itemCatalog.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return null;
                }
                
                // gather ID(string) information based on attribute
                NamedNodeMap aMap = itemCatalog.item(i).getAttributes();
                String itemId = aMap.getNamedItem("Id").getNodeValue();

                //gather price(int) information based on element
                Element elem = (Element) itemCatalog.item(i);
                int itemPrice = Integer.parseInt(elem.getElementsByTagName("Price").item(0).getTextContent());
                
                //create item list using input data
                try{
                itemList.add(ItemFactory.getInstance().createItem(itemId, itemPrice));}
                catch(NullValueException|InvalidValueException e){System.out.println("Invalid values for item read. Ignoring item");}

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
        	 System.err.println("**** XML File cannot be loaded");
             System.exit(-1);
            e.printStackTrace();
        }
        //sorting the values before they're returned//
		Collections.sort(itemList, new Comparator<Item>() {
		    @Override
		    public int compare(Item o1, Item o2) {
		        return o1.getId().compareTo(o2.getId());
		    }
		});
		try{
	    for(Item i: itemList){result.addItem(i);}}
		catch( NullValueException e){System.out.println("Error with Input during Item load");}
        return result;
    }
}


