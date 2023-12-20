package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

//Represents a song database where songs can be added to, deleted from, searched, filtered and averages
// can be calculated
public class VideoDatabase implements Writable {
    private List<Video> videos;
    private String name;

    //EFFECTS: constructs a new song database
    public VideoDatabase(String name) {
        this.name = name;
        videos = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: adds a song to the list of songs
    public void addVideo(Video video) {
        EventLog.getInstance().logEvent(new Event("Added Song:" + video));
        videos.add(video);
    }

    //MODIFIES: this
    //EFFECTS: removes a song from the list of songs
    public void deleteVideo(Video video) {
        EventLog.getInstance().logEvent(new Event("Deleted Song:" + video));
        videos.remove(video);
    }

    //EFFECTS: Locates a song based on the name of the song and returns that song if found
    // else return null
    public Video searchVideo(String keyword) {
        for (Video next : videos) {
            if (next.getTitle().equals(keyword)) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: return the list of songs
    public List<Video> getVideos() {
        if (videos.isEmpty()) {
            return null;
        } else {
            return videos;
        }
    }

    //EFFECTS: calculates and returns the average views of each song as an integer
    public int calcAvgViews() {
        int sum = 0;
        if (videos.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Views"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Views"));
            for (Video next : videos) {
                sum = sum + next.getViews();
            }
            return sum / videos.size();
        }
    }

    //EFFECTS: calculates and returns the average likes of each song as an integer
    public int calcAvgLikes() {
        int sum = 0;
        if (videos.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Likes"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Likes"));
            for (Video next : videos) {
                sum = sum + next.getLikes();
            }
            return sum / videos.size();
        }
    }

    //EFFECTS: calculates and returns the average dislikes of each song as an integer
    public int calcAvgDislikes() {
        int sum = 0;
        if (videos.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("The List Has No Songs To Calculate The Average Dislikes"));
            return 0;
        } else {
            EventLog.getInstance().logEvent(new Event("The List Has Songs To Calculate The Average Dislikes"));
            for (Video next : videos) {
                sum = sum + next.getDislikes();
            }
            return sum / videos.size();
        }
    }


    //EFFECTS: return a list of songs that have the featured instrument
    //else return an empty list if no song has that featured instrument
    public List<Video> filterVideos() {
        List<Video> filterVideo = new ArrayList<>();
//        for (Video next : videos) {
//            if (next.getInstrument().equals(instrument)) {
//                filterVideo.add(next);
//                EventLog.getInstance().logEvent(
//                        new Event("Song: " + next + " was added to the filtered list"));
//            }
//        }
//        EventLog.getInstance().logEvent(new Event("Displayed Filtered Song List Based On: " + instrument + ":"
//                + filterVideo));
//        return filterVideo;
        for (Video next : videos) {
            if (next.getFavourite()) {
                filterVideo.add(next);
            }
        }
        return filterVideo;
    }

    //MODIFIES: this
    //EFFECTS: return the sorted list of songs in descending order based on the number of views
    public List<Video> sortByViews() {
        videos.sort(new ViewsComparator());
        //EventLog.getInstance().logEvent(new Event("All Songs Were Sorted"));
        return videos;
    }

    //MODIFIES: this
    //EFFECTS: return the sorted list of songs in descending order based on the number of likes
    public List<Video> sortByLikes() {
        videos.sort(new LikesComparator());
        //EventLog.getInstance().logEvent(new Event("All Songs Were Sorted"));
        return videos;
    }

    //MODIFIES: this
    //EFFECTS: return the sorted list of songs in descending order based on the number of likes
    public List<Video> sortByDislikes() {
        videos.sort(new DislikesComparator());
        //EventLog.getInstance().logEvent(new Event("All Songs Were Sorted"));
        return videos;
    }

    //MODIFIES: song
    //EFFECTS: favourates the song specified by the user
    public void favouriteVideo(Video video) {
        EventLog.getInstance().logEvent(new Event("Favouritied Song: " + video));
        video.setFavourite();
    }

    //MODIFIES: song
    //EFFECTS: unfavourates the song specified by the user
    public void unFavouriteVideo(Video video) {
        EventLog.getInstance().logEvent(new Event("Unfavourited Song: " + video));
        video.resetFavourite();
    }


    //EFFECTS: converts the songDatabase into a JSONObject and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Videos", videosToJson());
        return json;
    }

    // EFFECTS: returns the songs in this songDatabase as a JSON array
    private JSONArray videosToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Video next : videos) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }

}

