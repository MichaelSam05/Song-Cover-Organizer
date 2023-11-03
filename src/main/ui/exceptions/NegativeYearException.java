package ui.exceptions;

public class NegativeYearException extends DateFormatException {
    public NegativeYearException() {
        super();
    }

    public NegativeYearException(String msg) {
        super(msg);
    }
}
