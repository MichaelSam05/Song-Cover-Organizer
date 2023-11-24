package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

import model.Song;

import static java.lang.Integer.parseInt;

import ui.exceptions.DateFormatException;
import ui.exceptions.NegativeYearException;

//Represents the add song button as well as the action that occurs when clicked
public class AddSongAction extends AbstractAction {
    private SongDatabaseState state;


    //EFFECTS: constructs the add song button
    public AddSongAction(SongDatabaseState state) {
        super("Add New Song");
        this.state = state;
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
        state.sd.addSong(song);
    }

    //EFFECTS: returns the number of views for a particular video from the user
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

    //EFFECTS: returns the number of likes for a particular video from the user
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

    //EFFECTS: returns the number of dislikes for a particular video from the user
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

    //EFFECTS: returns the formatted date based on user input
    private String getFormatedDate() {
        boolean valid = false;
        int year = 0;
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};

        Object m = JOptionPane.showInputDialog(null,
                "Select Upload Month", "Add New Song", JOptionPane.PLAIN_MESSAGE,null,months,
                months[0]);
        do {
            try {
                year = parseInt(JOptionPane.showInputDialog(null,
                        "Please Enter Upload Year (yyyy)", "Add New Song", JOptionPane.PLAIN_MESSAGE));

                valid = isValid(year);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Month and Year Must Be Integers",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (valid == false);
        return m.toString() + "/" + Integer.toString(year);
    }

    //EFFECTS: determines if the year the user has inputted is valid
    private boolean isValid(int year) {
        boolean valid = false;
        try {
            valid = validDate(year);
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
    //- NegativeYearException: if the year entered is negative
    //- DateFormatException: if the year entered does not contain 4 digits (yyyy)
    //EFFECTS: returns true if a valid date, that is, obeys the required format
    // else throw the relevant exception
    public boolean validDate(int year) throws NegativeYearException,
            DateFormatException {
        if (year < 0) {
            throw new NegativeYearException("The year entered cannot be negative");
        } else if (String.valueOf(year).length() != 4) {
            throw new DateFormatException("The year must be of the form (yyyy)");
        } else {
            return true;
        }
    }

}

