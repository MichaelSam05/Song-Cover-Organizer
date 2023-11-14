package model.exceptions;

public class MonthOutOfRangeException extends Exception {
    public MonthOutOfRangeException() {
        super();
    }

    public MonthOutOfRangeException(String msg) {
        super(msg);
    }
}
