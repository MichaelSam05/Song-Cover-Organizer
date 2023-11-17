package ui;

import model.SongDatabase;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class SaveAction extends AbstractAction {

    private SongDatabase sd;
    private JsonWriter jsonWriter;

    private String location;


    public SaveAction(SongDatabase sd, JsonWriter jsonWriter, String location) {
        super("Save Data");
        this.sd = sd;
        this.jsonWriter = jsonWriter;
        this.location = location;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(sd);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Success, saved to " + location, "Save Data", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file:" + location, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
