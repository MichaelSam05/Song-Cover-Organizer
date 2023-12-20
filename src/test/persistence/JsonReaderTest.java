package persistence;
import model.Video;
import model.VideoDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonReaderTest extends JsonTest {

    //Test the JsonReader in the case where it tries to read from a file that does not exist
    @Test
    void testReaderFileNotFound() {
        JsonReader reader = new JsonReader("./data/fileNotFound.json");
        try {
            VideoDatabase videoDatabaseTest = reader.read();
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
            VideoDatabase videoDatabaseTest = reader.read();
            assertEquals("My Video Database", videoDatabaseTest.getName());
            assertNull(videoDatabaseTest.getVideos());
        } catch (IOException e) {
            fail("Couldn't read from file");

        }
    }

    //Test the JsonReader in the case where the file it is reading from has data in it
    @Test
    void testReaderGeneralUseSongDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUseSongDatabase.json");
        try {
            VideoDatabase videoDatabaseTest = reader.read();
            assertEquals("My VideoDatabase", videoDatabaseTest.getName());
            List<Video> videos = videoDatabaseTest.getVideos();
            assertEquals(2, videos.size());
            compareVideo("name1",  "01/2000",
                    2,2,2,false, videos.get(0));
            compareVideo("name2","01/2000",
                    1,1,1,true, videos.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

