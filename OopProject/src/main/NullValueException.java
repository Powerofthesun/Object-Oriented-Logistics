package main;

@SuppressWarnings("serial")
public class NullValueException extends Exception {
    public NullValueException() {}

    public NullValueException(String message)
    {
       super(message);
    }
}
