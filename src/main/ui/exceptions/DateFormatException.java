package ui.exceptions;

//Represents the DateFormatException class which is used for instances when the date entered does not have
//the appropriate number
//of digits
public class DateFormatException extends Exception {

    //EFFECTS: constructs the DateFormatException
    public DateFormatException(String msg) {
        super(msg);
    }
}
