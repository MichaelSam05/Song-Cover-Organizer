package model;

import java.util.Comparator;

public class DislikesComparator implements Comparator<Video> {
    public DislikesComparator() {
    }


    //EFFECTS: compares two different songs based on the number of likes it received and returns the appropriate int
    //- return 1: if the song1 has less dislikes than song2
    //- return -1; if song1 has more dislikes than song2
    //-return 0; if song1 and song2 have the same number of dislikes
    @Override
    public int compare(Video video1, Video video2) {
        if (video1.getDislikes() < video2.getDislikes()) {
            return 1;
        } else if (video1.getDislikes() > video2.getDislikes()) {
            return -1;
        } else {
            return 0;
        }
    }
}

