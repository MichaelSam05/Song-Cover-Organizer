package ui;

import model.SongDatabase;

//Represents the current state of the SongDatabase that allows the entire UI to know about the different updates
// made by the user which t
public class SongDatabaseState {
    protected SongDatabase sd;
    protected static final String NAME = "My SongDatabase";

    //EFFECTS: constructs a SongDatabaseState
    public SongDatabaseState() {
        sd = new SongDatabase(NAME);
    }
}
