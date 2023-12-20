package persistence;

import model.Video;
import model.VideoDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    //Test the JsonWriter in the case where the destination file or input file has an illegal name
    @Test
    public void testWriterIllegalFileNameException() {
        try {
            VideoDatabase videoDatabaseTest = new VideoDatabase("Test");
            JsonWriter writer = new JsonWriter("./data/\0/songDatabase.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //expected
        }
    }

    //Test the JsonWriter in the case where the file is opened and no songs are written to the file
    @Test
    public void testWriterEmptySongDatabase() {
        try {
            VideoDatabase videoDatabaseTest = new VideoDatabase("Test");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySongDatabase.json");
            writer.open();
            writer.write(videoDatabaseTest);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptySongDatabase.json");
            videoDatabaseTest = reader.read();
            assertEquals("Test", videoDatabaseTest.getName());
            assertNull(videoDatabaseTest.getVideos());
        } catch (IOException e) {
            fail("IOException was not expected");
        }
    }

    //Test the JsonWriter in the case where the file has data written to it
    @Test
    public void testWriterGeneralUseSongDatabase() {
        Video s1 = new Video("Glimpse of US", "08/2022",
                394998,21248,25, false);
        Video s2 = new Video("Love Nwantiti","10/2021",
                1935946, 85182,392,true);
        try {
            VideoDatabase videoDatabaseTest = new VideoDatabase("Test");
            videoDatabaseTest.addVideo(s1);
            videoDatabaseTest.addVideo(s2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUseSongDatabase.json");
            writer.open();
            writer.write(videoDatabaseTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUseSongDatabase.json");
            videoDatabaseTest = reader.read();
            List<Video> videos = videoDatabaseTest.getVideos();
            compareVideo(s1.getTitle(),s1.getDate(),s1.getViews(),
                    s1.getLikes(),s1.getDislikes(),s1.getFavourite(), videos.get(0));
            compareVideo(s2.getTitle(),s2.getDate(),s2.getViews(), s2.getLikes(),s2.getDislikes(),s2.getFavourite(),
                    videos.get(1));

        } catch (IOException e) {
            fail("IOException was not expected");
        }

    }

}
