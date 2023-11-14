package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.time.Month;

import ui.SongOrganizerApp;

import static java.lang.Integer.parseInt;

import model.exceptions.DateFormatException;
import model.exceptions.MonthOutOfRangeException;
import model.exceptions.NegativeYearException;

public class AddSongAction extends AbstractAction {
    private final static int WIDTH = 400;

    private final static int HEIGHT = 400;



    public AddSongAction() {
        super("Add New Song");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean valid = false;
        int month = 0;
        int year = 0;
        String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name", "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        String artistName = JOptionPane.showInputDialog(null, "Please Enter Artist Name", "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        String instrument = JOptionPane.showInputDialog(null, "Please Enter Featured Instrument", "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        do {
            try {
                month = parseInt(JOptionPane.showInputDialog(null, "Please Enter Upload Month (mm)", "Add New Song",
                        JOptionPane.PLAIN_MESSAGE));

                year = parseInt(JOptionPane.showInputDialog(null, "Please Enter Upload Year (yyyy)", "Add New Song",
                        JOptionPane.PLAIN_MESSAGE));

                try {
                    valid = validDate(month, year);
                } catch (DateFormatException dfe) {
                    JOptionPane.showMessageDialog(null, dfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NegativeYearException nde) {
                    JOptionPane.showMessageDialog(null, nde.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (MonthOutOfRangeException mor) {
                    JOptionPane.showMessageDialog(null, mor.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Month and Year Must Be Integers", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } while (valid == false);

        //finish rest of input.


    }

    //THROWS:
    //- MonthOutOfRangeException: if the month entered does not exist, that is, not >= 01 && <= 12
    //- NegativeYearException: if the year entered is negative
    //- DateFormatException: if either the month does not contain 2 digits (mm) or the year does
    //  not contain 4 digits (yyyy)
    //EFFECTS: returns true if a valid date, that is, obeys the required format
    // else throw the relevant exception
    public boolean validDate(int month, int year) throws MonthOutOfRangeException, NegativeYearException,
            DateFormatException {
        if (year < 0) {
            throw new NegativeYearException("The year entered cannot be negative");
        } else if (String.valueOf(year).length() != 4 || String.valueOf(month).length() != 2) {
            throw new DateFormatException("Your date is not the right format");
        } else if (month < 1 || month > 12) {
            throw new MonthOutOfRangeException("The month entered must be between 01-12");
        } else {
            return true;
        }
    }

}

