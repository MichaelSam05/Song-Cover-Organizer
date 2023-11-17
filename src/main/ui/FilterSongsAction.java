package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.util.List;

public class FilterSongsAction extends AbstractAction {
    //private SongDatabase sd;
    private JFrame window;
    private JPanel tablePanel;

    private static final int NUM_COLS = 8;
    private SongDatabaseState state;

    public FilterSongsAction(/*SongDatabase sd*/SongDatabaseState state, JPanel tablePanel) {
        super("Filter Songs");
        //this.sd = sd;
        this.tablePanel = tablePanel;
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String instrument = JOptionPane.showInputDialog(null,
                    "Please Enter The Instrument You Wish to Filter", "Filter Songs",
                    JOptionPane.PLAIN_MESSAGE);
            List<Song> filtered = state.sd.filterSong(instrument);
            if (filtered == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + instrument
                        + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                        "Favorite"};
                Object[][] data = getData(filtered);
                JTable songTable = new JTable(data, colNames);
                //initializeColumns(songTable);
                JScrollPane songPane = new JScrollPane(songTable);
                tablePanel.removeAll();
                tablePanel.add(songPane);
                tablePanel.revalidate();
                tablePanel.repaint();
            }

        }
    }

//    private void initializeColumns(JTable songTable) {
//        TableColumn column = null;
//        for (int i = 0; i < NUM_COLS; i++) {
//            column = songTable.getColumnModel().getColumn(i);
//            if (i <= 2) {
//                column.setPreferredWidth(100);
//            } else {
//                column.setPreferredWidth(50);
//            }
//        }
//    }

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
