package persistence;
import org.junit.jupiter.api.Test;
import model.Song;
import model.SongDatabase;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonReaderTest extends JsonTest {

    //Test the JsonReader in the case where it tries to read from a file that does not exist
    @Test
    void testReaderFileNotFound() {
        JsonReader reader = new JsonReader("./data/fileNotFound.json");
        try {
            SongDatabase songDatabaseTest = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    //Test the JsonReader in the case where the file it is reading from is empty
    @Test
    void testReaderEmptySongDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySongDatabase.json");
        try {
            SongDatabase songDatabaseTest = reader.read();
            assertEquals("My Song Database", songDatabaseTest.getName());
            assertNull(songDatabaseTest.getSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    //Test the JsonReader in the case where the file it is reading from has data in it
    @Test
    void testReaderGeneralUseSongDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUseSongDatabase.json");
        try {
            SongDatabase songDatabaseTest = reader.read();
            assertEquals("My Song Database", songDatabaseTest.getName());
            List<Song> songs = songDatabaseTest.getSongs();
            assertEquals(2, songs.size());
            compareSong("song1", "artist1","guitar", "09/2000",
                    100,100,100,false, songs.get(0));
            compareSong("song2","artist2","piano","10/2022",
                    200,200,200,true, songs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

