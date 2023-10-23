package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SongDatabaseTest {
    private Song s1;
    private Song s2;
    private Song s3;

    private SongDatabase songDatabaseTest;

    @BeforeEach
    public void runBefore() {
        s1 = new Song("Glimpse of US", "Joji","Guitar","08/2022",
                394998,21248,25);
        s2 = new Song("Love Nwantiti","CKay","Guitar","10/2021",
                1935946, 85182,392);
        s3 = new Song("Lovely", "Billie Eilish", "Violin","02/2019",
                23134836,761003,3885);

        songDatabaseTest = new SongDatabase();
    }

    //Test the case where there are no songs in the list
    @Test
    public void emptyListTest() {
        List<Song> songs = songDatabaseTest.getSongs();
        assertNull(songs);
    }

    //test addSong method by adding one song to the list of songs
    @Test
    public void addSongTest() {
        songDatabaseTest.addSong(s1);
        List<Song> songs = songDatabaseTest.getSongs();
        assertEquals(s1,songs.get(0));
    }

    //test addSong method by adding more than one song
    @Test
    public void addManySongsTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        List<Song> songs = songDatabaseTest.getSongs();
        assertEquals(s1,songs.get(0));
        assertEquals(s2,songs.get(1));
    }

    //test deleteSong method when the user tries to delete a song but the list of songs is empty
    //in this case, the list of songs remains unchanged, that is, empty
    @Test
    public void deleteSongButEmptyTest() {
        List<Song> songs = songDatabaseTest.getSongs();
        assertNull(songs);
        songDatabaseTest.deleteSong("Never Gonna Give You Up");
        assertNull(songs);
    }

    //test deleteSong method by deleting 1 song
    @Test
    public void deleteOneSongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        List<Song> songs = songDatabaseTest.getSongs();
        assertTrue(songs.contains(s2));
        songDatabaseTest.deleteSong(s2.getSongName());
        assertFalse(songs.contains(s2));
    }

    //test the case where the user searches for a song but there is no songs in the list of songs
    @Test
    public void searchSongButEmptyTest() {
        List<Song> songs = songDatabaseTest.getSongs();
        assertNull(songs);
        Song tempSong = songDatabaseTest.searchSong("Never Gonna Give You Up");
        assertNull(tempSong);

    }

    //test the searchSong method but it cannot find the song that is in the list of song
    @Test
    public void searchSongNotFoundTest() {
        songDatabaseTest.addSong(s1);
        Song tempSong = songDatabaseTest.searchSong("Never Gonna Give You Up");
        assertNull(tempSong);
    }

    //test searchSong method where it searches for a song and finds it
    //but it is the only song in the list.
    @Test
    public void searchSongFoundButOneLongTest() {
        songDatabaseTest.addSong(s1);
        Song tempSong = songDatabaseTest.searchSong("Glimpse of US");
        assertEquals(tempSong,s1);
    }

    //test the searchSong method where the song is found
    //the list is more than one long.
    @Test
    public void searchSongFoundButMoreThanOneLongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        Song tempSong = songDatabaseTest.searchSong("Love Nwantiti");
        assertEquals(tempSong,s2);
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when no songs are in the list
    @Test
    public void calcAvgsWithoutSongsTest() {
        List<Song> songs = songDatabaseTest.getSongs();
        assertNull(songs);
        assertEquals(0,songDatabaseTest.calcAvgViews());
        assertEquals(0,songDatabaseTest.calcAvgLikes());
        assertEquals(0,songDatabaseTest.calcAvgDislikes());
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when songs are in the list
    @Test
    public void calcAgsWithSongsTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        List<Song> songs = songDatabaseTest.getSongs();
        assertFalse(songs == null);
        assertEquals(8488593,songDatabaseTest.calcAvgViews());
        assertEquals(289144,songDatabaseTest.calcAvgLikes());
        assertEquals(1434,songDatabaseTest.calcAvgDislikes());
    }

    //test the filterSong method in the case where the list of songs is empty
    @Test
    public void filterSongEmptyTest() {
        List<Song> filteredSongs = songDatabaseTest.filterSong("Guitar");
        assertTrue(filteredSongs.isEmpty());
    }

    //test the filterSong method in the case where the list of songs is not empty
    //but it could not find any song of the specified featured instrument
    @Test
    public void filterSongOneLongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        List<Song> filteredSongs = songDatabaseTest.filterSong("Piano");
        assertTrue(filteredSongs.isEmpty());
    }

    //test the filterSong method in the case where the list of songs is not empty
    //and returns a list of the songs that have the featured instrument
    @Test
    public void filterSongTwoLongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        List<Song> filteredSongs = songDatabaseTest.filterSong("Guitar");
        assertFalse(filteredSongs.isEmpty());
        assertEquals(2,filteredSongs.size());
        assertTrue(filteredSongs.contains(s1));
        assertTrue(filteredSongs.contains(s2));
    }

    //Test the favouritesong method in the case where the user favourites the song
    @Test
    public void favouriteSongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.favouriteSong(s1);
        assertTrue(s1.getFavourite());
    }

    //test the unFavourite method where the user has a song favourited then unfavourited
    @Test
    public void unfavouriteSongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.favouriteSong(s1);
        assertTrue(s1.getFavourite());
        songDatabaseTest.unFavouriteSong(s1);
        assertFalse(s1.getFavourite());
    }
}
