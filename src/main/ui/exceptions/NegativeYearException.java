package ui.exceptions;

//Represents the NegativeYearException class that is used for instances when the year entered is negative
public class NegativeYearException extends Exception {

    //EFFECTS: constructs the NegativeYearException
    public NegativeYearException(String msg) {
        super(msg);
    }
}
