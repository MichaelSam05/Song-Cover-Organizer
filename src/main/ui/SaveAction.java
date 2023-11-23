package ui;

import model.SongDatabase;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

//represents save button for the user to save their data
public class SaveAction extends AbstractAction {
    private JsonWriter jsonWriter;
    private String location;
    private SongDatabaseState state;


    //EFFECTS: constructs the save button
    public SaveAction(SongDatabaseState state, JsonWriter jsonWriter, String location) {
        super("Save Data");
        this.jsonWriter = jsonWriter;
        this.location = location;
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: saves the user's data when the save button is clicked and displays the appropriate message
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(state.sd);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Success, saved to " + location, "Save Data", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file:" + location, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
