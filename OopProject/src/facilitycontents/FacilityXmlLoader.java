package facilitycontents;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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


public class FacilityXmlLoader implements FacilityLoader
{
	ArrayList<Facility> facilities;
	public ArrayList<Facility> loadFacilities()
	{
	     try {
	            
	    	 	String fileName = FacilityProperties.getInstance().getFilename();
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();

	            File xml = new File(fileName);
	            if (!xml.exists()) {
	                System.err.println("**** XML File '" + fileName + "' cannot be found");
	                System.exit(-1);
	            }

	            Document doc = db.parse(xml);
	            doc.getDocumentElement().normalize();

	            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();
	            facilities=new ArrayList<Facility>();
	            for (int i = 0; i < facilityEntries.getLength(); i++) {
	                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
	                    continue;
	                }
	                
	                String entryName = facilityEntries.item(i).getNodeName();
	                if (!entryName.equals("Facility")) {
	                    System.err.println("Unexpected node found: " + entryName);
	                    return null;
	                }
	                
	                
	                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
	                String facilityLocation = aMap.getNamedItem("Location").getNodeValue();

	                
	                Element elem = (Element) facilityEntries.item(i);
	                int rate = Integer.parseInt(elem.getElementsByTagName("RatePerDay").item(0).getTextContent());
	                double cost = Double.parseDouble(elem.getElementsByTagName("Cost").item(0).getTextContent());

	               //gathering inventory info
	                ArrayList<InventoryEntry> entryList = new ArrayList<>();
	                NodeList inventoryList = elem.getElementsByTagName("Inventory");
	                for (int j = 0; j < inventoryList.getLength(); j++) {
	                    if (inventoryList.item(j).getNodeType() == Node.TEXT_NODE) {
	                        continue;
	                    }

	                    entryName = inventoryList.item(j).getNodeName();
	                    if (!entryName.equals("Inventory")) {
	                        System.err.println("Unexpected node found: " + entryName);
	                        return null;
	                    }
	                    NodeList quantityList = elem.getElementsByTagName("ItemQuantity");
	                    for (int x = 0; x < quantityList.getLength(); x++)
	                    {
	                    	NamedNodeMap fMap = quantityList.item(x).getAttributes();
	    	                String itemId = fMap.getNamedItem("id").getNodeValue();
	    	              
	    	                int itemQuantity=Integer.parseInt(elem.getElementsByTagName("ItemQuantity").item(x).getTextContent());
	    	                try{
	                    	entryList.add(InventoryEntryFactory.getInstance().createInventoryEntry(itemId, itemQuantity));}
	    	                catch(InvalidValueException | NullValueException e){System.out.println("Error when creating Inventory Entry List. Value not added.");}
	                    }
	                }
	                //sorting Inventory
	                Collections.sort(entryList, new Comparator<InventoryEntry>() {
            		    @Override
            		    public int compare(InventoryEntry o1, InventoryEntry o2) {
            		        return o1.getId().compareTo(o2.getId());
            		    }
            		});
	                
	                //gathering link info
	                ArrayList<Link> linkList= new ArrayList<Link>();
	                NodeList linkNodes = elem.getElementsByTagName("CityLinks");
	                for (int j = 0; j < linkNodes.getLength(); j++) {
	                    if (linkNodes.item(j).getNodeType() == Node.TEXT_NODE) {
	                        continue;
	                    }

	                    entryName = linkNodes.item(j).getNodeName();
	                    if (!entryName.equals("CityLinks")) {
	                        System.err.println("Unexpected node found: " + entryName);
	                        return null;
	                    }
	                    NodeList distanceList = elem.getElementsByTagName("Distance");
	                    for (int x = 0; x < distanceList.getLength(); x++)
	                    {
	                    	NamedNodeMap lMap = distanceList.item(x).getAttributes();
	    	                String locationId = lMap.getNamedItem("Location").getNodeValue();
	    	              
	    	                int locationDistance=Integer.parseInt(elem.getElementsByTagName("Distance").item(x).getTextContent());
	    	                try{
	                    	linkList.add(LinkFactory.getInstance().createLink(locationId, locationDistance));}
	    	                catch(NullValueException| InvalidValueException e){System.out.println("Error when creating list of links. Link will be ignored");}
	                    }
	                 
	                }
	                //sorting links
	                Collections.sort(linkList, new Comparator<Link>() {
            		    @Override
            		    public int compare(Link o1, Link o2) {
            		        return o1.getCity().compareTo(o2.getCity());
            		    }
            		});
	              
	                try{
	                LinkList tempList=LinkListFactory.getInstance().createList(linkList);
	                InventoryList tempInventory = InventoryListFactory.getInstance().createList(entryList);
	                facilities.add(FacilityFactory.getInstance().createFacility(facilityLocation,rate,cost,tempInventory, tempList ));}
	                catch(InvalidValueException | NullValueException e){System.out.println("Error with Input during facility load");}
	            }
	           

	        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
	        	
	            e.printStackTrace();
	            System.err.println("**** XML File cannot be loaded");
                System.exit(-1);
	        }
	     //sorting facilities 
	     Collections.sort(facilities, new Comparator<Facility>() {
 		    @Override
 		    public int compare(Facility o1, Facility o2) {
 		        return o1.getLocation().compareTo(o2.getLocation());
 		    }
 		});
	     return facilities;
	    }
	}