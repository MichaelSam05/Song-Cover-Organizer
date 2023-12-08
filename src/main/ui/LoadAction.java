package ui;

import model.SongDatabase;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

//represents what happens when the user clicks the load button
public class LoadAction extends AbstractAction  {

    private JsonReader jsonReader;
    private String location;
    private SongDatabaseState state;


    //EFFECTS: construct the load button
    public LoadAction(SongDatabaseState state, JsonReader jsonReader, String location) {
        super("Load Data");
        this.jsonReader = jsonReader;
        this.location = location;
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: reads the previously saved data into the song database and displays the appropriate message depending on
    //if it was successful
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            state.sd = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded data form " + location, "Load Data", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,
                    "Unable to load data from " + location, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
