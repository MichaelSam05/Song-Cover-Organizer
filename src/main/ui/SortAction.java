package ui;

import model.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SortAction extends AbstractAction {

    private SongDatabaseState state;
    private JPanel tablePanel;


    public SortAction(SongDatabaseState state, JPanel panel) {
        super("Sort Songs");
        this.state = state;
        this.tablePanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = state.sd.getSongs();
        List<Song> sorted = new ArrayList<>();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
            tablePanel.removeAll();
            tablePanel.revalidate();
            tablePanel.repaint();
        } else {
            String[] sortBy = {"views","likes","dislikes"};

            Object m = JOptionPane.showInputDialog(null,
                    "Select Upload Month", "Add New Song", JOptionPane.PLAIN_MESSAGE,null,sortBy,
                    sortBy[0]);
            String choice = m.toString();

            if (choice.equals("views")) {
                sorted = state.sd.sortByViews();
            } else if (choice.equals("likes")) {
                sorted = state.sd.sortByLikes();
            } else {
                sorted = state.sd.sortByDislikes();
            }

            String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                    "Favorite"};
            ListSongsAction listAction = new ListSongsAction();
            Object[][] data = listAction.getData(sorted);
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
}