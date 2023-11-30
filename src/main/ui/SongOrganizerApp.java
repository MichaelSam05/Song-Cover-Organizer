package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Event;
import model.EventLog;
import model.Song;
import model.SongDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.exceptions.DateFormatException;
import ui.exceptions.MonthOutOfRangeException;
import ui.exceptions.NegativeYearException;

import static java.lang.Integer.*;
// Represents a song organizer application where the user is prompted to select a choice from the main menu and
// commands are executed based on the user's choice

public class SongOrganizerApp {

    private static final String JSON_STORE = "./data/songOrganizer.json";
    private Scanner input;
    private static final String QUIT = "quit";
    private SongDatabase songDatabase;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;



    //EFFECTS: constructs a song database for the user and runs the app
    public SongOrganizerApp() throws FileNotFoundException {
        songDatabase = new SongDatabase("My Song Database");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runOrganizer();
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu and terminates when the user is ready to close the application
    public void runOrganizer() {

        boolean keepGoing = true;
        String choice;

        System.out.println("Welcome\n");
        while (keepGoing) {
            displayMainMenu();
            choice = input.nextLine();

            if (choice.equals(QUIT)) {
                saveQuit();
                keepGoing = false;
                System.out.println("Terminating...");
                //Iterator<Event> events = EventLog.getInstance().iterator();

                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.getDescription());
                }
            } else {
                handleInput(choice);
            }

        }
    }

    //EFFECTS: gives the user the choice to safely save the changes they make to the songDatabase in case they
    //accidentally forgot to save and terminate the application
    private void saveQuit() {
        String choice;
        System.out.println("Do you want to save changes?");
        choice = yesOrNoMenu();
        if (choice.equals("y")) {
            doSaveData();
        }
    }

    //EFFECTS: displays a yes or no menu to the user
    private String yesOrNoMenu() {
        String choice = null;
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("y -> yes");
            System.out.println("n -> no");
            choice = input.nextLine();

            if (choice.equals("y") || choice.equals("n")) {
                keepGoing = false;
            }

        }
        return choice;

    }

    //EFFECTS: Displays the main menu
    public void displayMainMenu() {
        System.out.println("Displaying Main Menu...");
        System.out.println("-----------------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------------");
        System.out.println("add -> Add Song");
        System.out.println("delete -> Delete Song");
        System.out.println("list -> See A List Of All Songs");
        System.out.println("search -> Search For A Song");
        System.out.println("calc -> Calculate Averages");
        System.out.println("filter -> Filter Songs By Instrument");
        System.out.println("sort -> Sort Song");
        System.out.println("fav -> Favourite A Song");
        System.out.println("unfav -> Unfavourite A Song");
        System.out.println("save -> Save Data");
        System.out.println("load -> Load Data");
        System.out.println(QUIT + " -> Exit");
        System.out.println("Please Enter One Of The Available Options");
    }

    //MODIFIES: this
    //EFFECTS: calls the appropriate methods based on the user's choice
    private void handleInput(String choice) {
        if (choice.equals("add")) {
            doAddSong();
        } else if (choice.equals("delete")) {
            doDeleteSong();
        } else if (choice.equals("list")) {
            doGenerateList();
        } else if (choice.equals("search")) {
            doSearchSong();
        } else if (choice.equals("calc")) {
            doGenerateAvg();
        } else if (choice.equals("filter")) {
            doFilterSongs();
        } else if (choice.equals("fav")) {
            doFavoriteSong();
        } else if (choice.equals("unfav")) {
            doUnFavoriteSong();
        } else if (choice.equals("save")) {
            doSaveData();
        } else if (choice.equals("load")) {
            doLoadData();
        } else if (choice.equals("sort")) {
            doSortSongs();
        } else {
            System.out.println("Invalid Choice");
        }
    }

    //MODIFIES: this
    //EFFECTS: sorts the song databased in descending order
    private void doSortSongs() {
        songDatabase.sortSongs();
    }

    //MODIFIES: this
    //EFFECTS: loads the user's songDatabase from a file
    private void doLoadData() {
        try {
            songDatabase = jsonReader.read();
            System.out.println("Loaded data form" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load data from " + JSON_STORE);
        }
    }

    //EFFECTS: save the user's songDatabase to a file
    private void doSaveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(songDatabase);
            jsonWriter.close();
            System.out.println("Success, saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file:" + JSON_STORE);
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
        Song song = new Song(songName, artistName, instrument, date, views, likes, dislikes, false);
        songDatabase.addSong(song);
    }

    //EFFECTS: prompts the user to enter a valid date and returns the formatted date
    private String getFormattedDate() {
        int month = 0;
        int year = 0;
        boolean valid = false;
        do {
            try {
                System.out.println("Enter Upload Month (mm)");
                month = parseInt(input.nextLine());
                System.out.println("Enter Upload Year (yyyy)");
                year = parseInt(input.nextLine());

            } catch (NumberFormatException nfe) {
                System.out.println("Please enter integers for the month and year");
            }

            try {
                valid = validDate(month, year);
            } catch (MonthOutOfRangeException mor) {
                System.out.println(mor.getMessage());
            } catch (NegativeYearException nye) {
                System.out.println(nye.getMessage());
            } catch (DateFormatException dfe) {
                System.out.println(dfe.getMessage());
            }

        } while (valid == false);
        return Integer.toString(month) + "/" + Integer.toString(year);
    }

    //MODIFIES: this
    //EFFECTS: deletes a song for the song database
    private void doDeleteSong() {
        String songName;
        String choice;
        List<Song> songs = songDatabase.getSongs();
        if (songs == null) {
            System.out.println("No songs in the organizer");
        } else {
            System.out.println("Enter song name you want to delete");
            songName = input.nextLine().toLowerCase();

            if (songDatabase.searchSong(songName) == null) {
                System.out.println("Song does not exist");
            } else {
                System.out.println("Are You Sure?");
                choice = yesOrNoMenu();
                if (choice.equals("y")) {
                    songDatabase.deleteSong(songName);
                    System.out.println("Successful deletion");
                } else {
                    System.out.println("Returning to main menu");
                }

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
    public void doGenerateList() {
        List<Song> songs = songDatabase.getSongs();
        if (songs == null) {
            System.out.println("There are no songs in the organizer");
        } else {
            print(songs);
        }
    }

    //EFFECTS: displays a list of songs that satisfies the instrument the user specifies
    private void doFilterSongs() {
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

    //MODIFIES: this
    //EFFECTS: favorites the song the user specifies
    private void doFavoriteSong() {
        String songName;
        doGenerateList();
        System.out.println("Enter Song Name You Wish To Favorite");
        songName = input.nextLine().toLowerCase();

        Song mySong = songDatabase.searchSong(songName);

        if (mySong == null) {
            System.out.println("Song not found");
        } else {
            songDatabase.favouriteSong(mySong);
            System.out.println("Success");
        }
    }

    //MODIFIES: this
    //EFFECTS: unfavorites the song the user specifies
    private void doUnFavoriteSong() {
        String songName;
        doGenerateList();
        System.out.println("Enter Song Name You Wish To Unfavorite");
        songName = input.nextLine().toLowerCase();
        Song mySong = songDatabase.searchSong(songName);

        if (mySong == null) {
            System.out.println("Song not found");
        } else {
            songDatabase.unFavouriteSong(mySong);
            System.out.println("Success");
        }
    }

    //EFFECTS: prints a list of songs for the user
    private void print(List<Song> songs) {
        System.out.println("Song Name | Artist Name | Instrument | Date | Views | Likes | Dislikes | isFavourite?");
        for (Song next : songs) {
            System.out.println(next.getSongName() + " | " + next.getArtistName() + " | "
                    + next.getInstrument() + " | " + next.getDate() + " | " + next.getViews() + " | "
                    + next.getLikes() + " | " + next.getDislikes() + " | " + next.getFavourite());
        }
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
