package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

//Represents a song database where songs can be added to, deleted from, searched, filtered and averages
// can be calculated
public class SongDatabase implements Writable {
    private List<Song> songs;
    private String name;

    //EFFECTS: constructs a new song database
    public SongDatabase(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: adds a song to the list of songs
    public void addSong(Song song) {
        EventLog.getInstance().logEvent(new Event("Added Song:" + song));
        songs.add(song);
    }

    //MODIFIES: this
    //EFFECTS: removes a song from the list of songs
    public void deleteSong(String song) {
        Song result = searchSong(song);
        if (!(result == null)) {
            EventLog.getInstance().logEvent(new Event("Deleted Song Named:" + song));
            songs.remove(result);
        }
    }

    //EFFECTS: Locates a song based on the name of the song and returns that song if found
    // else return null
    public Song searchSong(String song) {
        for (Song next : songs) {
            if (next.getSongName().equals(song)) {
                EventLog.getInstance().logEvent(new Event("Song Named: " + song + "Was Found"));
                return next;
            }
        }
        EventLog.getInstance().logEvent(new Event("Song Named: " + song + "Was Not Found"));
        return null;
    }

    //EFFECTS: return the list of songs
    public List<Song> getSongs() {
        if (songs.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Get"));
            return null;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Get"));
            return songs;
        }
    }

    //EFFECTS: calculates and returns the average views of each song as an integer
    public int calcAvgViews() {
        int sum = 0;
        if (songs.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Views"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Views"));
            for (Song next : songs) {
                sum = sum + next.getViews();
            }
            return sum / songs.size();
        }
    }

    //EFFECTS: calculates and returns the average likes of each song as an integer
    public int calcAvgLikes() {
        int sum = 0;
        if (songs.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Likes"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Likes"));
            for (Song next : songs) {
                sum = sum + next.getLikes();
            }
            return sum / songs.size();
        }
    }

    //EFFECTS: calculates and returns the average dislikes of each song as an integer
    public int calcAvgDislikes() {
        int sum = 0;
        if (songs.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Dislikes"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Dislikes"));
            for (Song next : songs) {
                sum = sum + next.getDislikes();
            }
            return sum / songs.size();
        }
    }


    //EFFECTS: return a list of songs that have the featured instrument
    //else return an empty list if no song has that featured instrument
    public List<Song> filterSong(String instrument) {
        List<Song> filterSong = new ArrayList<>();
        for (Song next : songs) {
            if (next.getInstrument().equals(instrument)) {
                EventLog.getInstance().logEvent(new Event(next + " Was Added To Filtered List"));
                filterSong.add(next);
            }
        }
        return filterSong;
    }

    //MODIFIES: this
    //EFFECTS: return the sorted list of songs in descending order based on the number of views
    public List<Song> sortSongs() {
        songs.sort(new ViewsComparator());
        EventLog.getInstance().logEvent(new Event("All Songs Were Sorted"));
        return songs;
    }

    //MODIFIES: song
    //EFFECTS: favourates the song specified by the user
    public void favouriteSong(Song song) {
        EventLog.getInstance().logEvent(new Event("Favouriting Song Named" + song));
        song.setFavourite();
    }

    //MODIFIES: song
    //EFFECTS: unfavourates the song specified by the user
    public void unFavouriteSong(Song song) {
        EventLog.getInstance().logEvent(new Event("Unfavouriting Song Named" + song));
        song.resetFavourite();
    }


    //EFFECTS: converts the songDatabase into a JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Songs", songsToJson());
        return json;
    }

    // EFFECTS: returns the songs in this songDatabase as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song next : songs) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }

}

