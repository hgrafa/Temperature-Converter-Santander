package exception;

public class TemperatureParseException extends RuntimeException {

    public TemperatureParseException() {
    }

    public TemperatureParseException(String message) {
        super(message);
    }

}
