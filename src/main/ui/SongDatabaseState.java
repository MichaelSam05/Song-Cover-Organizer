package ui;

import model.SongDatabase;

//Represents the current state of the SongDatabase that allows the entire UI to know about the different updates
//made by the user
public class SongDatabaseState {
    protected SongDatabase sd;
    protected static final String NAME = "My SongDatabase";

    //EFFECTS: constructs a SongDatabaseState as well as instantiate a new SongDatabes
    public SongDatabaseState() {
        sd = new SongDatabase(NAME);
    }
}
