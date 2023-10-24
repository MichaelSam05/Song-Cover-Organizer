package persistence;

import model.Song;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    //Test if the fields input by the user is the same as the fields stored in the file when read
    protected void compareSong(String songName,String artistName,String instrument,
                               String date,int views,int likes,int dislikes,boolean isFavourite, Song song) {
        assertEquals(songName,song.getSongName());
        assertEquals(artistName,song.getArtistName());
        assertEquals(instrument,song.getInstrument());
        assertEquals(date,song.getDate());
        assertEquals(views,song.getViews());
        assertEquals(likes,song.getLikes());
        assertEquals(dislikes,song.getDislikes());
        assertEquals(isFavourite,song.getFavourite());
    }
}
