package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a song having a song name, artist name, views, likes, dislikes, instrument, date (mm/yyyy)
// and isFavorite
public class Song implements Writable {
    private String songName;
    private String artistName;
    private int views;
    private int likes;
    private int dislikes;
    private String instrument;
    private String date;
    private boolean isFavourite;

    //REQUIRES: a valid date (dd/yyyy), such that, mm >=01 && mm<=12 and yyyy has 4 digits
    //EFFECTS: constructs a song with isFavorite set to false.
    public Song(String songName, String artistName, String instrument,
                String date, int views, int likes, int dislikes, boolean isFavourite) {
        this.songName = songName;
        this.artistName = artistName;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.instrument = instrument;
        this.date = date;
        this.isFavourite = isFavourite;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public int getViews() {
        return this.views;
    }

    public int getLikes() {
        return this.likes;
    }

    public int getDislikes() {
        return this.dislikes;
    }

    public String getInstrument() {
        return this.instrument;
    }

    public String getDate() {
        return this.date;
    }

    public boolean getFavourite() {
        return this.isFavourite;
    }

    public void setFavourite() {
        this.isFavourite = true;
        //EventLog.getInstance().logEvent(new Event("Song Favourited"));
    }

    public void resetFavourite() {
        this.isFavourite = false;
        //EventLog.getInstance().logEvent(new Event("Song Unfavourited"));
    }

    //EFFECTS: converts a song into a JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Song Name", songName);
        json.put("Artist Name",  artistName);
        json.put("Instrument", instrument);
        json.put("Date", date);
        json.put("Views",  views);
        json.put("Likes", likes);
        json.put("Dislikes", dislikes);
        json.put("IsFavourite", isFavourite);
        return json;
    }
}
