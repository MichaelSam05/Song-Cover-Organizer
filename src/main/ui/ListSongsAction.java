package ui;

import model.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;


//represents what happens when the user wants to view a list of all their songs
public class ListSongsAction extends AbstractAction {

    private JPanel tablePanel;
    private static final int NUM_COLS = 8;
    private SongDatabaseState state;

    //EFFECTS: constructs the list songs button
    public ListSongsAction(/*SongDatabase sd*/SongDatabaseState state, JPanel tablePanel) {
        super("List All Songs");
        this.tablePanel = tablePanel;
        this.state = state;
    }


    //MODIFIES: this
    //EFFECTS: when this button is pressed a JTable is placed on the display JPanel to show the list of songs to the
    //user
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
            tablePanel.removeAll();
            tablePanel.revalidate();
            tablePanel.repaint();
        } else {
            String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                    "Favorite"};
            Object[][] data = getData(songs);
            tablePanel.removeAll();
            DefaultTableModel model = new DefaultTableModel(data, colNames);
            JTable songTable = new JTable(model);
            songTable.getColumnModel().getColumn(7).setCellRenderer(new FavouriteRenderer());
            songTable.setRowHeight(25);
            JScrollPane songPane = new JScrollPane(songTable);
            tablePanel.add(songPane);
            tablePanel.revalidate();
            tablePanel.repaint();
        }

    }


    //EFFECTS: gets all the songs stored in the database and puts them into a 2D Object Array
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
}
