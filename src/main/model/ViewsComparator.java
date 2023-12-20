package model;

import java.util.Comparator;

//Represents a view comparator for comparing views
public class ViewsComparator implements Comparator<Video> {

    //EFFECTS: creates a new view comparator
    public ViewsComparator() {
    }


    //EFFECTS: compares two different songs based on the number of views it received and returns the appropriate int
    //- return 1: if the song1 has less views than song2
    //- return -1; if song1 has more views than song2
    //-return 0; if song1 and song2 have the same number of views
    @Override
    public int compare(Video video1, Video video2) {
        if (video1.getViews() < video2.getViews()) {
            return 1;
        } else if (video1.getViews() > video2.getViews()) {
            return -1;
        } else {
            return 0;
        }
    }
}
