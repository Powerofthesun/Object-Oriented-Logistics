package facilitycontents;

import java.util.ArrayList;

public class ScheduleImpl implements Schedule {
	private ArrayList<Integer> schedule;
	private int rate;
	private boolean usedDay=false;
	public ScheduleImpl(int rate){this.schedule=new ArrayList<Integer>();this.rate=rate; for (int i = 0; i<20;i++){schedule.add(rate);}}
	
	public double bookItems(int numberIn, int day)
	{
		day--;
		double counter=0;
		while(numberIn>0)
		{
			if(day>=schedule.size())
			{
				for(int i=schedule.size(); i<day+1;i++)
				{schedule.add(rate);}
			}
			if(schedule.get(day)>0)
			{
				usedDay=true;
				while(numberIn>0 && schedule.get(day)>0 )
				{
					numberIn--;
					schedule.set(day,schedule.get(day)-1);
				//	System.out.println("Updating: NumberIn="+numberIn+" scheduleValue="+schedule.get(day));
				}
				if (schedule.get(day)>0)
				{
					counter--;
					counter=counter+((double)(rate-schedule.get(day))/(double)rate);
				}
			}
			
			if(usedDay==true){counter++;}
			day++;
		}
		return counter;
	}
	
	public String toString()
	{
		String res="Schedule:\nDay:\t\t";
		for (int i = 1; i<schedule.size()+1;i++)
		{
			res=res+String.format("%2d", i)+" ";
		}
		
		res=res+"\nAvailable:\t";
		for (int i = 0; i<schedule.size();i++)
		{
			res=res+String.format("%2d", schedule.get(i))+" ";
		}
		return res;
	}

}
