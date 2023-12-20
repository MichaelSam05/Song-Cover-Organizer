package persistence;

import model.Video;
import model.VideoDatabase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//"Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git"
// Represents a reader that reads songDatabase from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads songDatabase from file and returns it;
    // throws IOException if an error occurs reading data from file
    public VideoDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseVideoDatabase(jsonObject);
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
    private VideoDatabase parseVideoDatabase(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        VideoDatabase vd = new VideoDatabase(name);
        addVideos(vd, jsonObject);
        return vd;
    }

    // MODIFIES: sd
    // EFFECTS: parses songs from JSON object and adds them to songDatabase
    private void addVideos(VideoDatabase vd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Videos");
        for (Object json : jsonArray) {
            JSONObject nextVideo = (JSONObject) json;
            addVideo(vd, nextVideo);
        }
    }

    // MODIFIES: sd
    // EFFECTS: parses song from JSON object and adds it to songDatabase
    private void addVideo(VideoDatabase sd, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String date = jsonObject.getString("Date");
        int views = jsonObject.getInt("Views");
        int likes = jsonObject.getInt("Likes");
        int dislikes = jsonObject.getInt("Dislikes");
        boolean isFavourite = jsonObject.getBoolean("IsFavourite");

        Video video = new Video(title, date, views, likes, dislikes, isFavourite);
        sd.addVideo(video);
    }
}



