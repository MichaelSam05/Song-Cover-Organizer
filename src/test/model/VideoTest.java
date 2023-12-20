package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {
    private Video s1;

    
    @BeforeEach
    public void runBefore() {
        s1 = new Video("Glimpse of US", "08/2022",
                394998,21248,25, false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Glimpse of US",s1.getTitle());
        assertEquals("08/2022",s1.getDate());
        assertEquals(394998,s1.getViews());
        assertEquals(21248,s1.getLikes());
        assertEquals(25,s1.getDislikes());
        assertFalse(s1.getFavourite());
    }
}