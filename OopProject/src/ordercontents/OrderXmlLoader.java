package ordercontents;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.InvalidValueException;
import main.NullValueException;

public class OrderXmlLoader implements OrderLoader {
	ArrayList<Order> orderList;
	public ArrayList<Order> loadOrders() {
		try {
            String fileName = OrderLoaderProperties.getInstance().getFilename();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList orderEntries = doc.getDocumentElement().getChildNodes();
            orderList = new ArrayList<Order>();
            for (int i = 0; i < orderEntries.getLength(); i++) {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("Order")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return null;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = orderEntries.item(i).getAttributes();
                String orderId = aMap.getNamedItem("id").getNodeValue();////////////////////////////////////////

                // Get a named nodes
                Element elem = (Element) orderEntries.item(i);
                int startDay = Integer.parseInt(elem.getElementsByTagName("StartTime").item(0).getTextContent());//////////////////////////
                String destination = elem.getElementsByTagName("Destination").item(0).getTextContent();////////////////////////////////////

                // Get all nodes named "Book" - there can be 0 or more
                ArrayList<OrderItem> orderItems = new ArrayList<>();
                NodeList itemList = elem.getElementsByTagName("OrderItems");
                for (int j = 0; j < itemList.getLength(); j++) {
                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(j).getNodeName();
                    if (!entryName.equals("OrderItems")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return null;
                    }
                    NodeList iList = elem.getElementsByTagName("Item");
                    for (int x = 0; x < iList.getLength(); x++)
                    {	
                    	
                    	NamedNodeMap bMap = iList.item(x).getAttributes();
               
                        String itemId = bMap.getNamedItem("name").getNodeValue();///////////////////////////////////////////////////////////
                        elem = (Element) itemList.item(j);
                        int quantity = Integer.parseInt(elem.getElementsByTagName("Item").item(x).getTextContent());/////////////////////////
                        try{
                        orderItems.add(OrderItemFactory.getInstance().createItem(itemId, quantity));}
                        catch(NullValueException | InvalidValueException e){System.out.println("Invalid value entered from XML for orderItem. Ignoring. Check orders.xml");}
                    }
                    
                }
                try{
                orderList.add(OrderFactory.getInstance().createOrder(orderId, startDay, destination, orderItems));}
                catch(NullValueException|InvalidValueException e){System.out.println("Invalid value entered from XML for order. Ignoring. Check orders.xml");}
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
		return orderList;
    }
}





        


