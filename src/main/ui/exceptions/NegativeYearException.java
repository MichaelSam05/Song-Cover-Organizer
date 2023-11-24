package ui.exceptions;

public class NegativeYearException extends Exception {
    public NegativeYearException() {
        super();
    }

    public NegativeYearException(String msg) {
        super(msg);
    }
}
