package ui;

import java.io.FileNotFoundException;

//Represents the start of the Sound Organizer App
public class Main {

    //instantiates a new Song Organizer App
    public static void main(String[] args) {
        try {
            new SongOrganizerAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

//        try {
//            new SongOrganizerApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("File Not Found");
//        }
    }
}
