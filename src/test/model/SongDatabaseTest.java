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
    void runBefore() {
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
    void emptyListTest() {
        List<Song> songs = songDatabaseTest.getSongs();
        assertNull(songs);
    }

    //test addSong method by adding one song to the list of songs
    @Test
    void addSongTest() {
        songDatabaseTest.addSong(s1);
        List<Song> songs = songDatabaseTest.getSongs();
        assertEquals(s1,songs.get(0));
    }
    //test addSong method by adding more than one song
    @Test
    void addManySongsTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        List<Song> songs = songDatabaseTest.getSongs();
        assertEquals(s1,songs.get(0));
        assertEquals(s2,songs.get(1));
    }

    //test deleteSong method by deleting 1 song
    @Test
    void deleteSongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        List<Song> songs = songDatabaseTest.getSongs();
        assertTrue(songs.contains(s2));
        songDatabaseTest.deleteSong(s2.getSongName());
        assertFalse(songs.contains(s2));
    }
    //test the searchSong method but it cannot find the song that is in the list of song
    @Test
    void searchSongNotFoundTest() {
        songDatabaseTest.addSong(s1);
        Song tempSong = songDatabaseTest.searchSong(s2.getSongName());
        assertNull(tempSong);
    }
    //test searchSong method where it searches for a song and finds it
    //but it is the only song in the list.
    @Test
    void searchSongFoundButOneLongTest() {
        songDatabaseTest.addSong(s1);
        Song tempSong = songDatabaseTest.searchSong(s1.getSongName());
        assertEquals(tempSong,s1);
    }
    //test the searchSong method where the song is found
    //the list is more than one long.
    @Test
    void searchSongFoundButMoreThanOneLongTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        Song tempSong = songDatabaseTest.searchSong(s2.getSongName());
        assertEquals(tempSong,s2);
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when no songs are in the list
    @Test
    void calcAvgsWithoutSongsTest() {
        assertEquals(0,songDatabaseTest.calcAvgViews());
        assertEquals(0,songDatabaseTest.calcAvgLikes());
        assertEquals(0,songDatabaseTest.calcAvgDislikes());
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when songs are in the list
    @Test
    void calcAgsWithSongsTest() {
        songDatabaseTest.addSong(s1);
        songDatabaseTest.addSong(s2);
        songDatabaseTest.addSong(s3);
        assertEquals(8488593,songDatabaseTest.calcAvgViews());
        assertEquals(289144,songDatabaseTest.calcAvgLikes());
        assertEquals(1434,songDatabaseTest.calcAvgDislikes());
    }
}
