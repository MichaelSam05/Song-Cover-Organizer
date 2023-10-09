package model;

// Represents a song having a song name, artist name, views, likes, dislikes, instrument, date (dd/mm/yyyy)
// and isFavorite
public class Song {
    private String songName;
    private String artistName;
    private int views;
    private int likes;
    private int dislikes;
    private String instrument;
    private String date;
    private boolean isFavourite;

    //REQUIRES: date formatted as (mm/yyyy)
    //EFFECTS: constructs a song with isFavorite set to false.
    public Song(String songName, String artistName, String instrument,
                String date, int views, int likes, int dislikes) {
        this.songName = songName;
        this.artistName = artistName;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.instrument = instrument;
        this.date = date;
        this.isFavourite = false;
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

}
