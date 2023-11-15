package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.time.Month;

import model.Song;
import model.SongDatabase;
import ui.SongOrganizerAppUI;

import static java.lang.Integer.parseInt;

import model.exceptions.DateFormatException;
import model.exceptions.MonthOutOfRangeException;
import model.exceptions.NegativeYearException;

public class AddSongAction extends AbstractAction {
    private final static int WIDTH = 400;

    private final static int HEIGHT = 400;
    private SongDatabase sd;


    public AddSongAction(SongDatabase sd) {
        super("Add New Song");
        this.sd = sd;
    }

    //MODIFIES: this
    //EFFECTS: prompts the user to enter the fields related to a song
    @Override
    public void actionPerformed(ActionEvent e) {

        String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        String artistName = JOptionPane.showInputDialog(null, "Please Enter Artist Name",
                "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        String instrument = JOptionPane.showInputDialog(null, "Please Enter Featured Instrument",
                "Add New Song",
                JOptionPane.PLAIN_MESSAGE);

        String date = getFormatedDate();

        int views = getValidViews();
        int likes = getValidLikes();
        int dislikes = getValidDislikes();

        Song song = new Song(songName, artistName, instrument, date, views, likes, dislikes, false);
        sd.addSong(song);
    }

    private int getValidViews() {
        boolean valid = false;
        int views = 0;
        do {
            try {
                views = parseInt(JOptionPane.showInputDialog(null, "Please Enter View Count",
                        "Add New Song", JOptionPane.PLAIN_MESSAGE));
                valid = true;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input Must Be An Integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (valid == false);

        return views;
    }

    private int getValidLikes() {
        boolean valid = false;
        int likes = 0;
        do {
            try {
                likes = parseInt(JOptionPane.showInputDialog(null, "Please Enter Like Count",
                        "Add New Song", JOptionPane.PLAIN_MESSAGE));
                valid = true;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input Must Be An Integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (valid == false);

        return likes;
    }

    private int getValidDislikes() {
        boolean valid = false;
        int dislikes = 0;
        do {
            try {
                dislikes = parseInt(JOptionPane.showInputDialog(null,
                        "Please Enter Dislike Count", "Add New Song", JOptionPane.PLAIN_MESSAGE));
                valid = true;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input Must Be An Integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (valid == false);

        return dislikes;
    }

    private String getFormatedDate() {
        boolean valid = false;
        int month = 0;
        int year = 0;
        do {
            try {
                month = parseInt(JOptionPane.showInputDialog(null,
                        "Please Enter Upload Month (mm)", "Add New Song", JOptionPane.PLAIN_MESSAGE));
                year = parseInt(JOptionPane.showInputDialog(null,
                        "Please Enter Upload Year (yyyy)", "Add New Song", JOptionPane.PLAIN_MESSAGE));

                valid = isValid(month,year);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Month and Year Must Be Integers",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (valid == false);
        return Integer.toString(month) + "/" + Integer.toString(year);
    }

    private boolean isValid(int month, int year) {
        boolean valid = false;
        try {
            valid = validDate(month, year);
        } catch (MonthOutOfRangeException mor) {
            JOptionPane.showMessageDialog(null, mor.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NegativeYearException nde) {
            JOptionPane.showMessageDialog(null, nde.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateFormatException dfe) {
            JOptionPane.showMessageDialog(null, dfe.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return valid;
    }

    //THROWS:
    //- MonthOutOfRangeException: if the month entered does not exist, that is, not >= 01 && <= 12
    //- NegativeYearException: if the year entered is negative
    //- DateFormatException: if either the month does not contain more than 2 digits (mm) or the year does
    //  not contain 4 digits (yyyy)
    //EFFECTS: returns true if a valid date, that is, obeys the required format
    // else throw the relevant exception
    public boolean validDate(int month, int year) throws MonthOutOfRangeException, NegativeYearException,
            DateFormatException {
        if (year < 0) {
            throw new NegativeYearException("The year entered cannot be negative");
        } else if (String.valueOf(year).length() != 4 || String.valueOf(month).length() > 2) {
            throw new DateFormatException("Your date is not the right format");
        } else if (month < 1 || month > 12) {
            throw new MonthOutOfRangeException("The month entered must be between 01-12");
        } else {
            return true;
        }
    }

}

