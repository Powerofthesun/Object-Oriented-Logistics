package networkcontents;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Stack;

import main.NullValueException;

public class NetworkTest 
{
	ArrayList<TestHelper> temp;
	public NetworkTest()
	{
		temp=new ArrayList<TestHelper>();
		temp.add(new TestHelper("Santa Fe, NM","Chicago, IL"));
		temp.add(new TestHelper("Atlanta, GA","St. Louis, MO"));
		temp.add(new TestHelper("Seattle, WA","Nashville, TN"));
		temp.add(new TestHelper("New York City, NY","Phoenix, AZ"));
		temp.add(new TestHelper("Fargo, ND","Austin, TX"));
		temp.add(new TestHelper("Denver, CO","Miami, FL"));
		temp.add(new TestHelper("Austin, TX","Norfolk, VA"));
		temp.add(new TestHelper("Miami, FL","Seattle, WA"));
		temp.add(new TestHelper("Los Angeles, CA","Chicago, IL"));
		temp.add(new TestHelper("Detroit, MI","Nashville, TN"));
	}
	public String runTest(int milesPerHourIn , int hoursPerDayIn) throws NullValueException
	{
		int milesPerHour=milesPerHourIn;
		int hoursPerDay=hoursPerDayIn;
		char holder = 'a';
		String res="";
		PathInfo tempResult;
		for (TestHelper element: temp)
		{
			res=res+holder+") "+element.start+" to "+element.finish+":\n"+"\t-";
			tempResult=NetworkService.getInstance().findShortestPath(element.start, element.finish);
			Stack<String> tempPath = tempResult.getPath();
			res=res+tempPath.pop();
			while(!tempPath.isEmpty())
			{res=res+"->"+tempPath.pop();}
			res=res+" = "+NumberFormat.getIntegerInstance().format(tempResult.getLength())+" mi\n\t-";
			double days=tempResult.getLength();
			days=days/(hoursPerDay*milesPerHour);
			res=res+NumberFormat.getIntegerInstance().format(tempResult.getLength())+" mi / (8 hours per day * 50 mph) = ";
			res=res+String.format("%.2f days\n",days );
			int h = holder;
			h++;
			holder=(char)h ;
		}
		return res;
	}
	private class TestHelper
	{
		public TestHelper(String startIn, String finishIn)
		{
			start=startIn;
			finish=finishIn;
		}
		public String start;
		public String finish;
	}
}
