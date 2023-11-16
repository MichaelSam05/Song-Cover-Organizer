package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FilterSongsAction extends AbstractAction {
    private SongDatabase sd;
    private JFrame window;

    private static final int NUM_COLS = 8;

    public FilterSongsAction(SongDatabase sd) {
        super("Filter Songs");
        this.sd = sd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String instrument = JOptionPane.showInputDialog(null,
                    "Please Enter The Instrument You Wish to Filter", "Filter Songs",
                    JOptionPane.PLAIN_MESSAGE);
            List<Song> filtered = sd.filterSong(instrument);
            if (filtered == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + instrument
                        + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                        "Favorite"};
                Object[][] data = getData(filtered);
                initializeWindow();
                JTable songTable = new JTable(data, colNames);
                JScrollPane songPane = new JScrollPane(songTable);

                window.add(songPane);
            }

        }
    }

    private Object[][] getData(List<Song> songs) {
        Object[][] data = new Object[songs.size()][NUM_COLS];
        int i;
        for (i = 0; i < songs.size(); i++) {
            data[i][0] = songs.get(i).getSongName();
            data[i][1] = songs.get(i).getArtistName();
            data[i][2] = songs.get(i).getInstrument();
            data[i][3] = songs.get(i).getViews();
            data[i][4] = songs.get(i).getLikes();
            data[i][5] = songs.get(i).getDislikes();
            data[i][6] = songs.get(i).getDate();
            data[i][7] = songs.get(i).getFavourite();
        }

        return data;
    }

    public void initializeWindow() {
        window = new JFrame();
        window.setSize(500, 500);
        window.setVisible(true);
    }
}
