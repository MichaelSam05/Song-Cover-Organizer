package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//Represents the filter songs button and the action that occurs when clicked
public class FilterSongsAction extends AbstractAction {
    private JPanel tablePanel;

    private static final int NUM_COLS = 8;
    private SongDatabaseState state;

    //EFFECTS: constructs the filter songs button
    public FilterSongsAction(SongDatabaseState state, JPanel tablePanel) {
        super("Filter Songs");
        this.tablePanel = tablePanel;
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: when the button is clicked, filters the songs list to only show songs that have the instrument field the
    //same as the user specified assuming that that instrument is in the list
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
                ListSongsAction listAction = new ListSongsAction();
                Object[][] data = listAction.getData(filtered);

                JTable songTable = new JTable(data, colNames);
                songTable.getColumnModel().getColumn(7).setCellRenderer(new FavouriteRenderer());
                songTable.setRowHeight(50);
                JScrollPane songPane = new JScrollPane(songTable);
                updateFame(songPane);
            }

        }
    }

    //MODIFIES: this
    //EFFECTS: updates the frame to show the JTable
    private void updateFame(JScrollPane songPane) {
        tablePanel.removeAll();
        tablePanel.add(songPane);
        tablePanel.revalidate();
        tablePanel.repaint();
    }


}
