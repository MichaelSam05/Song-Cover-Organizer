package ui;

import java.util.Scanner;

import model.Song;
import model.SongDatabase;

import static java.lang.Integer.*;

public class SongOrganizerApp {

    private Scanner input;
    SongDatabase songDatabase = new SongDatabase();

    public SongOrganizerApp() {
        runOrganizer();
    }

    public void runOrganizer() {

        boolean keepGoing = true;
        int choice;
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("Welcome\n");
        while (keepGoing) {
            displayMainMenu();
            choice = input.nextInt();

            if (choice == 4) {
                keepGoing = false;
            } else {
                handleInput(choice);
            }

        }
    }

    //EFFECTS: Displays the main menu
    public void displayMainMenu() {
        System.out.println("Displaying Main Menu...");
        System.out.println("-----------------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------------");
        System.out.println("1 -> Add Song");
        System.out.println("2 -> Delete Song");
        System.out.println("3 -> Generate A List Of Songs");
        System.out.println("4 -> Exit");
        System.out.println("Please Enter A Digit Of The Available Options");
    }

    //REQUIRES: choice>=1 && choice<=4
    //EFFECTS: calls the appropriate methods based on the user's choice
    private void handleInput(int choice) {
        if (choice == 1) {
            doAddSong();
        } else if (choice == 2) {
            doDeleteSong();
        } else if (choice == 3) {
            doGenerateList();
        } else {
            System.out.println("Invalid Choice");
        }
    }

    //MODIFIES: this
    //EFFECTS: add new song to the song database
    private void doAddSong() {
        String songName;
        String artistName;
        String instrument;
        String month = ""; //used for validDate method
        String year = "";
        String date;
        int views;
        int likes;
        int dislikes;

        input.nextLine();
        System.out.println("Enter Song Name");
        songName = input.nextLine();
        songName = songName.toLowerCase();

        System.out.println("Enter Artist Name");
        artistName = input.nextLine();
        artistName = artistName.toLowerCase();

        System.out.println("Enter Featured Instrument");
        instrument = input.nextLine();
        instrument = instrument.toLowerCase();

        while (!validDate(month, year)) {
            System.out.println("Enter Upload Month (mm)");
            month = input.nextLine();

            System.out.println("Enter Upload Year (yyyy)");
            year = input.nextLine();
        }

        date = month + "/" + year; //concatenates the strings into appropriate format

        System.out.println("Enter Number of Views");
        views = input.nextInt();

        System.out.println("Enter Number of Likes");
        likes = input.nextInt();

        System.out.println("Enter Number of Dislikes");
        dislikes = input.nextInt();

        Song song = new Song(songName, artistName, instrument, date, views, likes, dislikes);

        songDatabase.addSong(song);
    }

    //MODIFIES: this
    //EFFECTS: deletes a song for the song database
    private void doDeleteSong() {
        String songName;
        System.out.println("Enter song name you want to delete");
        songName = input.nextLine();

        if (songDatabase.searchSong(songName) == null) {
            System.out.println("Song does not exist");
        } else {
            songDatabase.deleteSong(songName);
            System.out.println("Successful deletion");
        }
    }

    private void doGenerateList () {
        //stub
    }

    //EFFECTS: returns true if a valid date, that is, obeys the required format
    // else return false
    private boolean validDate(String month, String year) {
        int intMonth = parseInt(month);
        int length = year.length();

        return (intMonth >= 1 && intMonth <= 12) && (length == 4);
    }
}
