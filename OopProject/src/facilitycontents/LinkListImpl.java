package facilitycontents;

import java.util.ArrayList;

import main.NullValueException;

public class LinkListImpl implements LinkList {
	private ArrayList<Link> linkList;
	public LinkListImpl(){linkList=new ArrayList<Link>();}
	public LinkListImpl(ArrayList<Link> linksIn) throws NullValueException
	{
		linkList=new ArrayList<Link>();
		for (Link e: linksIn)
		{
			addLink(e);
		}
	}
	public void addLink(Link linkIn) throws NullValueException {
		if (linkIn==null){throw new NullValueException("Cannot have null links");}
		linkList.add(linkIn);
	}
	public ArrayList<Link> getLinks() {
		ArrayList<Link> temp = new ArrayList<Link>();
		
		for (Link l: linkList)
		{
			try{
			temp.add(LinkFactory.getInstance().createLink(l.getCity(), l.getDistance()));} catch(Exception e){e.printStackTrace();}
		}
		return temp;
	}

}
