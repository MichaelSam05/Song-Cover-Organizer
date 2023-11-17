package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FavouriteSongAction extends AbstractAction {
    private SongDatabase sd;

    public FavouriteSongAction(SongDatabase sd) {
        super("Favourite A Song");
        this.sd = sd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String songName = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                    "Favourite A Song",
                    JOptionPane.PLAIN_MESSAGE);
            Song favSong = sd.searchSong(songName);

            if (favSong == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + songName
                        + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                favSong.setFavourite();
                JOptionPane.showMessageDialog(null,   songName
                        + " was successfully favourited", "Favourite A Song", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
