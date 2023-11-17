package ui;

import model.SongDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoadAction extends AbstractAction {

    private SongDatabase sd;
    private JsonReader jsonReader;

    private String location;

    private JFrame frame;
    private JPanel panel;
    private SongDatabaseState state;


    public LoadAction(/*SongDatabase sd*/SongDatabaseState state, JsonReader jsonReader, String location, JFrame frame, JPanel panel) {
        super("Load Data");
        this.sd = sd;
        this.jsonReader = jsonReader;
        this.location = location;
        this.frame = frame;
        this.panel = panel;
        this.state = state;
    }



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
