package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//represents the unfavourite button for the user to unfavourite a song
public class UnfavouriteSongAction extends AbstractAction {

    private SongDatabase sd;
    private SongDatabaseState state;

    //EFFECTS: constructs the unfavourite song button
    public UnfavouriteSongAction(SongDatabaseState state) {
        super("Unfavourite Button");
        this.sd = sd;
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: unfavaourites the song specified by the user given that the song was found in the song database
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                    "Unfavourite A Song",
                    JOptionPane.PLAIN_MESSAGE);
            Song favSong = state.sd.searchSong(songName);

            if (favSong == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + songName
                        + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                favSong.resetFavourite();
                JOptionPane.showMessageDialog(null,   songName
                        + " was successfully favourited", "Unfavourite A Song", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
