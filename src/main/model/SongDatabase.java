package model;

import java.util.ArrayList;
import java.util.List;

public class SongDatabase {
    private List<Song> songs = new ArrayList<>();

    //EFFECTS: constructs a new song database
    public SongDatabase() {
    }

    //MODIFIES: this
    //EFFECTS: adds a song to the list
    public void addSong(Song song) {
        songs.add(song);
    }

    public void deleteSong(String song) {
        Song result = searchSong(song);
        if (!(result == null)) {
            songs.remove(result);
        }

    }

    public Song searchSong(String song) {
        for (Song next : songs) {
            if (next.getSongName().equals(song)) {
                return next;
            }
        }
        return null;
    }

    //REQUIRES: num <= the size of the list
    //EFFECTS: returns the first num elements in the list
    public List<Song> getNumSongs(int num) {
//        if (songs.isEmpty()) {
        return null;
//        } else {
//            List<Song> songList = new ArrayList<>();
//            for (int i = 0; i < num; i++) {
//                songList.add(songs.get(i));
//            }
//        }
    }
}
