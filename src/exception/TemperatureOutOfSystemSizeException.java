package exception;

public class TemperatureOutOfSystemSizeException extends IndexOutOfBoundsException {

    public TemperatureOutOfSystemSizeException() {
    }

    public TemperatureOutOfSystemSizeException(String s) {
        super(s);
    }

}
