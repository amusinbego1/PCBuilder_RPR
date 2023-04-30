package ba.unsa.etf.rpr.exceptions;

public class PCBuilderException extends Exception{
    public PCBuilderException(String message) {
        super(message);
    }

    public PCBuilderException(String message, Exception reason){
        super(message, reason);
    }
}
