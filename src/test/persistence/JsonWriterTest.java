package persistence;

import org.junit.jupiter.api.Test;
import model.Song;
import model.SongDatabase;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    //Test the JsonWriter in the case where the destination file or input file has an illegal name
    @Test
    public void testWriterIllegalFileNameException() {
        try {
            SongDatabase songDatabaseTest = new SongDatabase("Test");
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
            SongDatabase songDatabaseTest = new SongDatabase("Test");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySongDatabase.json");
            writer.open();
            writer.write(songDatabaseTest);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptySongDatabase.json");
            songDatabaseTest = reader.read();
            assertEquals("Test",songDatabaseTest.getName());
            assertNull(songDatabaseTest.getSongs());
        } catch (IOException e) {
            fail("IOException was not expected");
        }
    }

    //Test the JsonWriter in the case where the file has data written to it
    @Test
    public void testWriterGeneralUseSongDatabase() {
        Song s1 = new Song("Glimpse of US", "Joji","Guitar","08/2022",
                394998,21248,25, false);
        Song s2 = new Song("Love Nwantiti","CKay","Guitar","10/2021",
                1935946, 85182,392,true);
        try {
            SongDatabase songDatabaseTest = new SongDatabase("Test");
            songDatabaseTest.addSong(s1);
            songDatabaseTest.addSong(s2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUseSongDatabase.json");
            writer.open();
            writer.write(songDatabaseTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUseSongDatabase.json");
            songDatabaseTest = reader.read();
            List<Song> songs = songDatabaseTest.getSongs();
            compareSong(s1.getSongName(),s1.getArtistName(),s1.getInstrument(),s1.getDate(),s1.getViews(),
                    s1.getLikes(),s1.getDislikes(),s1.getFavourite(),songs.get(0));
            compareSong(s2.getSongName(),s2.getArtistName(),s2.getInstrument(),s2.getDate(),s2.getViews(),
                    s2.getLikes(),s2.getDislikes(),s2.getFavourite(),songs.get(1));

        } catch (IOException e) {
            fail("IOException was not expected");
        }

    }

}
