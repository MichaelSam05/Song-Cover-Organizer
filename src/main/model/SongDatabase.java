package model;

import java.util.ArrayList;
import java.util.List;

public class SongDatabase {
    private List<Song> songs;

    //EFFECTS: constructs a new song database
    public SongDatabase() {
        songs = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a song to the list of songs
    public void addSong(Song song) {
        songs.add(song);
    }

    //MODIFIES: this
    //EFFECTS: removes a song from the list of songs
    public void deleteSong(String song) {
        Song result = searchSong(song);
        if (!(result == null)) {
            songs.remove(result);
        }
    }

    //EFFECTS: Locates a song based on the name of the song and returns that song if found
    // else return null
    public Song searchSong(String song) {
        for (Song next : songs) {
            if (next.getSongName().equals(song)) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: return the list of songs
    public List<Song> getSongs() {
        if (songs.isEmpty()) {
            return null;
        } else {
            return songs;
        }
    }

    //EFFECTS: calculates and returns the average views of each song
    public int calcAvgViews() {
        int sum = 0;
        if (songs.isEmpty()) {
            return 0;
        } else {
            for (Song next : songs) {
                sum = sum + next.getViews();
            }
            return sum / songs.size();
        }
    }

    //EFFECTS: calculates and returns the average likes of each song
    public int calcAvgLikes() {
        int sum = 0;
        if (songs.isEmpty()) {
            return 0;
        } else {
            for (Song next : songs) {
                sum = sum + next.getLikes();
            }
            return sum / songs.size();
        }
    }

    //EFFECTS: calculates and returns the average dislikes of each song
    public int calcAvgDislikes() {
        int sum = 0;
        if (songs.isEmpty()) {
            return 0;
        } else {
            for (Song next : songs) {
                sum = sum + next.getDislikes();
            }
            return sum / songs.size();
        }
    }
}

