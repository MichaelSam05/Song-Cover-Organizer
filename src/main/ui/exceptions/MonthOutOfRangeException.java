package ui.exceptions;

public class MonthOutOfRangeException extends DateFormatException {
    public MonthOutOfRangeException() {
        super();
    }

    public MonthOutOfRangeException(String msg) {
        super(msg);
    }
}
