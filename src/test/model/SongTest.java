package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    private Song s1;

    
    @BeforeEach
    void runBefore() {
        s1 = new Song("Glimpse of US", "Joji","Guitar","08/2022",
                394998,21248,25);
    }

    @Test
    void testConstructor() {
        assertEquals("Glimpse of US",s1.getSongName());
        assertEquals("Joji",s1.getArtistName());
        assertEquals("Guitar",s1.getInstrument());
        assertEquals("08/2022",s1.getDate());
        assertEquals(394998,s1.getViews());
        assertEquals(21248,s1.getLikes());
        assertEquals(25,s1.getDislikes());
        assertFalse(s1.getFavourite());
    }
}