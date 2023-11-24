package ui.exceptions;

//Represents the MonthOutOfRangeException class that is used for instances where the month entered does not
//exist in the standard 12-month calendar
public class MonthOutOfRangeException extends Exception {

    //EFFECTS: constructs the MonthOutOfRangeException
    public MonthOutOfRangeException(String msg) {
        super(msg);
    }
}
