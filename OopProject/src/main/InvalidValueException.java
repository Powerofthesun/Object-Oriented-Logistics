package main;

@SuppressWarnings("serial")
public class InvalidValueException extends Exception {
	 public InvalidValueException() {}

	    public InvalidValueException(String message)
	    {
	       super(message);
	    }

}
