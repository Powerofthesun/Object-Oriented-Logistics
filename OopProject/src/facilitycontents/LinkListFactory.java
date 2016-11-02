package facilitycontents;

import java.util.ArrayList;

import main.InvalidValueException;
import main.NullValueException;

public class LinkListFactory {
		private static LinkListFactory instance;
			
			private LinkListFactory()
			{}
			public static LinkListFactory getInstance()
			{
				if (instance==null){instance=new LinkListFactory();}
				return instance;
			}
			public LinkList createList(ArrayList<Link> links) throws NullValueException, InvalidValueException
			{
				return new LinkListImpl(links);
			}
			public LinkList createList()
			{
				return new LinkListImpl();
			}
			


}
