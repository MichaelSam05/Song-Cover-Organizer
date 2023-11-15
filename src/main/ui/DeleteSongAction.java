package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteSongAction extends AbstractAction {

    private SongDatabase sd;
    public DeleteSongAction(SongDatabase sd) {
        super("Delete Song");
        this.sd = sd;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //stub
    }
}
