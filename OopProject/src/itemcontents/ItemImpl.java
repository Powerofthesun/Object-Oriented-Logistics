package itemcontents;

import java.text.NumberFormat;

import main.InvalidValueException;
import main.NullValueException;

public class ItemImpl implements Item {
	private String id;
	private int price;
	ItemImpl(String idIn, int priceIn) throws InvalidValueException, NullValueException
	{
		if(price<0){throw new InvalidValueException("Price cannot be negative");}
		if(idIn==null){throw new NullValueException("ID cannot be null");}
		price=priceIn;
		id=idIn;
	}
	
	public int getPrice()
	{return price;}
	
	public String getId()
	{
		String temp = id;
		return temp;
	}
	public String toString()
	{
		return String.format("%s%s: $%s", id, (id.length()>=8?"":"\t"),NumberFormat.getIntegerInstance().format(price)+"  ");
	}

}
