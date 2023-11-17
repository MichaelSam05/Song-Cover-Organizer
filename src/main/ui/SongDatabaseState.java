package ui;

import model.SongDatabase;

public class SongDatabaseState {
    protected SongDatabase sd;
    protected String name;

    public SongDatabaseState() {
        sd = new SongDatabase("My SongDatabase");
    }
}
