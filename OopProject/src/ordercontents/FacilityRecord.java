package ordercontents;

import main.InvalidValueException;

public interface FacilityRecord {
	public String getSource();
	public int getItemCount();
	public int getEndDay();
	public int getTravelTime();
	public int getArrivalDay();
	public String toString();
	public void setItemCount(int newCount)throws InvalidValueException;
	public void updateEndDay(int newDay) throws InvalidValueException;
}
