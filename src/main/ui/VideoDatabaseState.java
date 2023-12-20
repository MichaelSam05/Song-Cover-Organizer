package ui;

import model.VideoDatabase;

//Represents the current state of the VideoDatabase that allows the entire UI to know about the different updates
//made by the user
public class VideoDatabaseState {
    protected VideoDatabase sd;
    protected static final String NAME = "My VideoDatabase";

    //EFFECTS: constructs a VideoDatabaseState as well as instantiate a new VideoDatabase
    public VideoDatabaseState() {
        sd = new VideoDatabase(NAME);
    }
}
