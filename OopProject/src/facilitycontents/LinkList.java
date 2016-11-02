package facilitycontents;

import java.util.ArrayList;

import main.NullValueException;

public interface LinkList {
	public void addLink(Link L)throws NullValueException ;
	public ArrayList<Link> getLinks();
	

}
