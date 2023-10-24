package persistence;

import model.Song;
import model.SongDatabase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads songDatabase from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads songDatabe from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SongDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSongDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses songDatabase from JSON object and returns it
    private SongDatabase parseSongDatabase(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        SongDatabase sd = new SongDatabase(name);
        addSongs(sd, jsonObject);
        return sd;
    }

    // MODIFIES: sd
    // EFFECTS: parses songs from JSON object and adds them to songDatabase
    private void addSongs(SongDatabase sd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addsong(sd, nextSong);
        }
    }

    // MODIFIES: sd
    // EFFECTS: parses song from JSON object and adds it to songDatabase
    private void addsong(SongDatabase sd, JSONObject jsonObject) {
        String songName = jsonObject.getString("Song Name");
        String artistName = jsonObject.getString("Artist Name");
        String instrument = jsonObject.getString("Instrument");
        String date = jsonObject.getString("Date");
        int views = jsonObject.getInt("Views");
        int likes = jsonObject.getInt("Likes");
        int dislikes = jsonObject.getInt("Dislikes");
        boolean isFavourite = jsonObject.getBoolean("IsFavourite");

        Song song = new Song(songName, artistName, instrument, date, views, likes, dislikes, isFavourite);
        sd.addSong(song);
    }
}



