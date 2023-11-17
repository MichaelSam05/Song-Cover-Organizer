package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DeleteSongAction extends AbstractAction {

    private SongDatabase sd;
    private SongDatabaseState state;

    public DeleteSongAction(/*SongDatabase sd*/SongDatabaseState state) {
        super("Delete Song");
        //this.sd = sd;
        this.state = state;
    }

    //EFFECTS: Deletes the song specified by the user
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();

        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                    "Delete Song",
                    JOptionPane.PLAIN_MESSAGE);
            Song deleteSong = sd.searchSong(songName);

            if (deleteSong == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + songName
                                + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                songs.remove(deleteSong);
                JOptionPane.showMessageDialog(null,   songName
                        + " was successfully deleted", "Delete Song", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
