package networkcontents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import facilitycontents.Link;

public class ShortestPath {
	private static Map<String, Integer> heap;
	public static PathInfo calculate (Map<String, ArrayList<Link>>nodes, String start, String finish)
	{
		//variable declaration
		Map<String, String> parent = new HashMap<String, String>();
		Map<String, Integer> key = new HashMap<String, Integer>();
		heap= new HashMap<String, Integer>();
		
		for(String element: nodes.keySet())	
		{
			key.put(element, Integer.MAX_VALUE);
			parent.put(element, null);
		}
		
		parent.put(start, start);
		key.put(start, 0);
		
		for(String element: nodes.keySet())	
		{heap.put(element, key.get(element) );}
		//shortestPath
		while (!heap.isEmpty())
		{
			String v=findMin();
			heap.remove(v);
			for(Link element: nodes.get(v))
			{
				
				if((element.getDistance()+key.get(v)) < (key.get(element.getCity())))
				{	
					key.put(element.getCity(), element.getDistance()+key.get(v));
					parent.put(element.getCity(), v);
					heap.put(element.getCity(), element.getDistance()+key.get(v));
				}
			}
		
		}
		Stack<String> result = new Stack<String>();
		String progression=finish;
		while(  progression != start )
		{
			result.push(progression);
			progression=parent.get(progression);
		}
		return PathInfoFactory.getInstance().createPathInfo(key.get(finish), result);
	}
	private static String findMin( )
	{
		int minItem=Integer.MAX_VALUE;
		String minLocation = "";
		for(String element: heap.keySet())
		{
			if(heap.get(element)<minItem)
			{
				minLocation=element;
				minItem=heap.get(element);
			}
		}
		return minLocation;
	}
}
