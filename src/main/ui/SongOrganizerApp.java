package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Song;
import model.SongDatabase;

import static java.lang.Integer.*;
// Represents a song organizer application where the user is prompted to select a choice from the main menu and
// commands are executed based on the user's choice

public class SongOrganizerApp {

    private Scanner input;
    private static final String QUIT = "quit";
    private SongDatabase songDatabase;

    //EFFECTS: constructs a song database for the user and runs the app
    public SongOrganizerApp() {
        songDatabase = new SongDatabase();
        runOrganizer();
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu and terminates when the user is ready to close the application
    public void runOrganizer() {

        boolean keepGoing = true;
        String choice;
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("Welcome\n");
        while (keepGoing) {
            displayMainMenu();
            choice = input.nextLine();

            if (choice.equals(QUIT)) {
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
        System.out.println("add -> Add Song");
        System.out.println("delete -> Delete Song");
        System.out.println("generate -> Generate A List Of All Songs");
        System.out.println("search -> Search For A Song");
        System.out.println("calc -> Calculate Averages");
        System.out.println("filter -> Filter Songs By Instrument");
        System.out.println(QUIT + " -> Exit");
        System.out.println("Please Enter A Digit Of The Available Options");
    }

    //MODIFIES: this
    //EFFECTS: calls the appropriate methods based on the user's choice
    private void handleInput(String choice) {
        if (choice.equals("add")) {
            doAddSong();
        } else if (choice.equals("delete")) {
            doDeleteSong();
        } else if (choice.equals("generate")) {
            doGenerateList();
        } else if (choice.equals("search")) {
            doSearchSong();
        } else if (choice.equals("calc")) {
            doGenerateAvg();
        } else if (choice.equals("filter")) {
            doFilterSongs();
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
        String date;
        int views;
        int likes;
        int dislikes;

        System.out.println("Enter Song Name");
        songName = input.nextLine().toLowerCase();
        System.out.println("Enter Artist Name");
        artistName = input.nextLine().toLowerCase();
        System.out.println("Enter Featured Instrument");
        instrument = input.nextLine().toLowerCase();
        date = getFormattedDate();
        System.out.println("Enter Number of Views");
        views = parseInt(input.nextLine());
        System.out.println("Enter Number of Likes");
        likes = parseInt(input.nextLine());
        System.out.println("Enter Number of Dislikes");
        dislikes = parseInt(input.nextLine());
        Song song = new Song(songName, artistName, instrument, date, views, likes, dislikes);
        songDatabase.addSong(song);
    }

    //EFFECTS: prompts the user to enter a valid date and returns the formatted date
    private String getFormattedDate() {

        String month = "0"; //force entry into the loop
        String year = "0"; //force entry into the loop
        while (!validDate(month, year)) {
            System.out.println("Enter Upload Month (mm)");
            month = input.nextLine();
            System.out.println("Enter Upload Year (yyyy)");
            year = input.nextLine();
        }

        return month + "/" + year; //concatenates and returns the string in valid format
    }

    //MODIFIES: this
    //EFFECTS: deletes a song for the song database
    private void doDeleteSong() {
        String songName;
        List<Song> songs = songDatabase.getSongs();
        if (songs == null) {
            System.out.println("No songs in the organizer");
        } else {
            System.out.println("Enter song name you want to delete");
            songName = input.nextLine().toLowerCase();

            if (songDatabase.searchSong(songName) == null) {
                System.out.println("Song does not exist");
            } else {
                songDatabase.deleteSong(songName);
                System.out.println("Successful deletion");
            }
        }

    }

    //EFFECTS: Displays the song that the user has searched for if it is found within the list of songs
    //else informs the user that the song was not found
    private void doSearchSong() {
        String songName;
        List<Song> songs = songDatabase.getSongs();
        if (songs == null) {
            System.out.println("No songs in the organizer");
        } else {
            System.out.println("Enter song name");
            songName = input.nextLine().toLowerCase();

            Song mySong = songDatabase.searchSong(songName);

            if (mySong == null) {
                System.out.println("Song not found");
            } else {
                List<Song> tempList = new ArrayList<>();
                tempList.add(mySong);
                print(tempList);
            }
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
        if (songs == null) {
            System.out.println("There are no songs in the organizer");
        } else {
            print(songs);
        }
    }

    //EFFECTS: displays a list of songs that satisfies the instrument the user specifies
    void doFilterSongs() {
        String instrument;
        List<Song> songs = songDatabase.getSongs();
        if (songs == null) {
            System.out.println("There are no songs in the organizer");
        } else {
            System.out.println("Enter instrument");
            instrument = input.nextLine().toLowerCase();

            List<Song> filteredSongs = songDatabase.filterSong(instrument);
            if (filteredSongs.isEmpty()) {
                System.out.println("Could not find " + instrument + " in the organizer");
            } else {
                print(filteredSongs);
            }
        }
    }

    //EFFECTS: prints a list of songs for the user
    void print(List<Song> songs) {
        System.out.println("Song Name | Artist Name | Instrument | Date | Views | Likes | Dislikes");
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
