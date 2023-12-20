package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class VideoDatabaseTest {
    private Video s1;
    private Video s2;
    private Video s3;

    private Video s4;

    private VideoDatabase videoDatabaseTest;

    @BeforeEach
    public void runBefore() {
        s1 = new Video("Glimpse of US", "08/2022",
                394998,21248,25,false);
        s2 = new Video("Love Nwantiti","10/2021",
                1935946, 85182,392,false);
        s3 = new Video("Lovely", "02/2019",
                23134836,761003,3885,false);

        s4 = new Video("Title", "11/2000",394998,100,
                200,false);

        videoDatabaseTest = new VideoDatabase("Test");
    }

    //Test the case where there are no songs in the list
    @Test
    public void emptyListTest() {
        List<Video> videos = videoDatabaseTest.getVideos();
        assertNull(videos);
    }

    //test addSong method by adding one song to the list of songs
    @Test
    public void addSongTest() {
        videoDatabaseTest.addVideo(s1);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertEquals(s1, videos.get(0));
    }

    //test addSong method by adding more than one song
    @Test
    public void addManySongsTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertEquals(s1, videos.get(0));
        assertEquals(s2, videos.get(1));
    }

    //test deleteSong method when the user tries to delete a song but the list of songs is empty
    //in this case, the list of songs remains unchanged, that is, empty
    @Test
    public void deleteSongButEmptyTest() {
        List<Video> videos = videoDatabaseTest.getVideos();
        assertNull(videos);
        videoDatabaseTest.deleteVideo(s1);
        assertNull(videos);
    }

    //test deleteSong method by deleting 1 song
    @Test
    public void deleteOneSongTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s3);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertTrue(videos.contains(s2));
        videoDatabaseTest.deleteVideo(s2);
        assertFalse(videos.contains(s2));
    }

    //test the case where the user searches for a song but there is no songs in the list of songs
    @Test
    public void searchSongButEmptyTest() {
        List<Video> videos = videoDatabaseTest.getVideos();
        assertNull(videos);
        Video tempVideo = videoDatabaseTest.searchVideo("Never Gonna Give You Up");
        assertNull(tempVideo);

    }

    //test the searchSong method but it cannot find the song that is in the list of song
    @Test
    public void searchSongNotFoundTest() {
        videoDatabaseTest.addVideo(s1);
        Video tempVideo = videoDatabaseTest.searchVideo("Never Gonna Give You Up");
        assertNull(tempVideo);
    }

    //test searchSong method where it searches for a song and finds it
    //but it is the only song in the list.
    @Test
    public void searchSongFoundButOneLongTest() {
        videoDatabaseTest.addVideo(s1);
        Video tempVideo = videoDatabaseTest.searchVideo("Glimpse of US");
        assertEquals(tempVideo,s1);
    }

    //test the searchSong method where the song is found
    //the list is more than one long.
    @Test
    public void searchSongFoundButMoreThanOneLongTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s3);
        Video tempVideo = videoDatabaseTest.searchVideo("Love Nwantiti");
        assertEquals(tempVideo,s2);
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when no songs are in the list
    @Test
    public void calcAvgsWithoutSongsTest() {
        List<Video> videos = videoDatabaseTest.getVideos();
        assertNull(videos);
        assertEquals(0, videoDatabaseTest.calcAvgViews());
        assertEquals(0, videoDatabaseTest.calcAvgLikes());
        assertEquals(0, videoDatabaseTest.calcAvgDislikes());
    }
    //test the calcAvgViews,calcAvgLikes, calcAvgDislikes methods when songs are in the list
    @Test
    public void calcAgsWithSongsTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s3);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertFalse(videos == null);
        assertEquals(8488593, videoDatabaseTest.calcAvgViews());
        assertEquals(289144, videoDatabaseTest.calcAvgLikes());
        assertEquals(1434, videoDatabaseTest.calcAvgDislikes());
    }

    //test the filterSong method in the case where the list of songs is empty
    @Test
    public void filterVideosEmptyTest() {
        List<Video> filteredVideos = videoDatabaseTest.filterVideos();
        assertTrue(filteredVideos.isEmpty());
    }

    //test the filterVideo method in the case where the list of songs is not empty
    //but it could not find any song of the specified featured instrument
    @Test
    public void filterSongOneLongTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s3);
        List<Video> filteredVideos = videoDatabaseTest.filterVideos();
        assertTrue(filteredVideos.isEmpty());
    }

    //test the filterSong method in the case where the list of songs is not empty
    //and returns a list of the songs that have the featured instrument
    @Test
    public void filterSongTwoLongTest() {
        s1.setFavourite();
        s3.setFavourite();
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s3);
        List<Video> filteredVideos = videoDatabaseTest.filterVideos();
        assertFalse(filteredVideos.isEmpty());
        assertEquals(2, filteredVideos.size());
        assertTrue(filteredVideos.contains(s1));
        assertTrue(filteredVideos.contains(s3));
    }

    //Test the favouritesong method in the case where the user favourites the song
    @Test
    public void favouriteSongTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.favouriteVideo(s1);
        assertTrue(s1.getFavourite());
    }

    //test the unFavourite method where the user has a song favourited then unfavourited
    @Test
    public void unfavouriteSongTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.favouriteVideo(s1);
        assertTrue(s1.getFavourite());
        videoDatabaseTest.unFavouriteVideo(s1);
        assertFalse(s1.getFavourite());
    }

    //test the sortSongs method where the list of songs is unsorted
    @Test
    public void sortSongsUnsortedTest(){
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s2);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertEquals(s1, videos.get(0));
        assertEquals(s2, videos.get(1));
        videoDatabaseTest.sortByViews();
        List<Video> sortedVideos = videoDatabaseTest.getVideos();
        assertEquals(s2, sortedVideos.get(0));
        assertEquals(s1, sortedVideos.get(1));
    }

    //test the sortSongs method where the list of songs is already sorted
    @Test
    public void sortSongsSortedTest() {
        videoDatabaseTest.addVideo(s2);
        videoDatabaseTest.addVideo(s1);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertEquals(s2, videos.get(0));
        assertEquals(s1, videos.get(1));
        videoDatabaseTest.sortByViews();
        List<Video> sortedVideos = videoDatabaseTest.getVideos();
        assertEquals(s2, sortedVideos.get(0));
        assertEquals(s1, sortedVideos.get(1));
    }

    //test the sortSongs method for the case where both songs have the same number of views
    @Test
    public void sortSongsSameViewsTest() {
        videoDatabaseTest.addVideo(s1);
        videoDatabaseTest.addVideo(s4);
        List<Video> videos = videoDatabaseTest.getVideos();
        assertEquals(s1, videos.get(0));
        assertEquals(s4, videos.get(1));
        videoDatabaseTest.sortByViews();
        List<Video> sortedVideos = videoDatabaseTest.getVideos();
        assertEquals(s1, sortedVideos.get(0));
        assertEquals(s4, sortedVideos.get(1));
    }
}
