package persistence;

import model.Video;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    //Test if the fields input by the user is the same as the fields stored in the file when read
    protected void compareVideo(String videoTitle, String date, int views, int likes, int dislikes, boolean isFavourite,
                                Video video) {
        assertEquals(videoTitle, video.getTitle());
        assertEquals(date, video.getDate());
        assertEquals(views, video.getViews());
        assertEquals(likes, video.getLikes());
        assertEquals(dislikes, video.getDislikes());
        assertEquals(isFavourite, video.getFavourite());
    }
}
