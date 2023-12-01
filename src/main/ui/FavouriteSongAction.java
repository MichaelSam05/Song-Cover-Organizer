package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//Represents the favourite song button and the action that occurs when clicked
public class FavouriteSongAction extends AbstractAction {
    private SongDatabaseState state;

    //EFFECTS: constructs the favourite song button
    public FavouriteSongAction(/*SongDatabase sd*/ SongDatabaseState state) {
        super("Favourite A Song");
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: when the button is clicked, sets the isFavourite field to true given that the song was found in the list
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                    "Favourite A Song",
                    JOptionPane.PLAIN_MESSAGE);
            Song favSong = state.sd.searchSong(songName);

            if (favSong == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + songName
                        + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                state.sd.favouriteSong(favSong);
                JOptionPane.showMessageDialog(null,   songName
                        + " was successfully favourited", "Favourite A Song", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
