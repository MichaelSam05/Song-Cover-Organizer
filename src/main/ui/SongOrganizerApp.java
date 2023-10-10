package ui;

import java.util.List;
import java.util.Scanner;

import model.Song;
import model.SongDatabase;

import static java.lang.Integer.*;

public class SongOrganizerApp {

    private Scanner input;
    private static final int QUIT = 6;
    private SongDatabase songDatabase = new SongDatabase();

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

            if (choice == QUIT) {
                keepGoing = false;
                System.out.println("Terminating...");
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
        System.out.println("3 -> Generate A List Of All Songs");
        System.out.println("4 -> Search For A Song");
        System.out.println("5 -> Calculate Averages");
        System.out.println(QUIT + " -> Exit");
        System.out.println("Please Enter A Digit Of The Available Options");
    }

    //REQUIRES: choice>=1 && choice<=6
    //EFFECTS: calls the appropriate methods based on the user's choice
    private void handleInput(int choice) {
        if (choice == 1) {
            doAddSong();
        } else if (choice == 2) {
            doDeleteSong();
        } else if (choice == 3) {
            doGenerateList();
        } else if (choice == 4) {
            doSearchSong();
        } else if (choice == 5) {
            doGenerateAvg();
        } else {
            System.out.println("Invalid Choice");
        }
    }

    //MODIFIES: this
    //EFFECTS: add new song to the song database
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

    //EFFECTS: Displays the song that the user has searched for if it is found within the list of songs
    //else informs the user that the song was not found
    private void doSearchSong() {
        String songName;
        System.out.println("Enter song name");
        songName = input.nextLine();
        Song mySong = songDatabase.searchSong(songName);

        if (mySong == null) {
            System.out.println("Song not found");
        } else {
            System.out.println("Song found");
            System.out.println("Song Name:" + mySong.getSongName());
            System.out.println("Artist Name:" + mySong.getArtistName());
            System.out.println("Featured Instrument:" + mySong.getInstrument());
            System.out.println("Views:" + mySong.getViews());
            System.out.println("Likes:" + mySong.getLikes());
            System.out.println("Dislikes:" + mySong.getDislikes());
            System.out.println("Upload Date:" + mySong.getDate());
        }

    }

    //EFFECTS: Displays the average views, likes and dislikes for the songs within the list of songs
    private void doGenerateAvg() {
        System.out.println("Calculating Averages");
        int avgViews = songDatabase.calcAvgViews();
        int avgLikes = songDatabase.calcAvgLikes();
        int avgDislikes = songDatabase.calcAvgDislikes();

        System.out.println("Average Views:" + avgViews);
        System.out.println("Average Likes:" + avgLikes);
        System.out.println("Average Dislikes:" + avgDislikes);
    }

    //EFFECT: Displays a list of all the songs in the list of songs
    private void doGenerateList() {
        List<Song> songs = songDatabase.getSongs();
        for (Song next : songs) {
            System.out.println(next.getSongName() + " | " + next.getArtistName() + " | "
                    + next.getInstrument() + " | " + next.getDate() + " | " + next.getViews() + " | "
                    + next.getLikes() + " | " + next.getDislikes() + " | " + next.getViews());
        }
    }

    //EFFECTS: returns true if a valid date, that is, obeys the required format
    // else return false
    private boolean validDate(String month, String year) {
        int intMonth = parseInt(month);
        int length = year.length();
        return (intMonth >= 1 && intMonth <= 12) && (length == 4);
    }
}
