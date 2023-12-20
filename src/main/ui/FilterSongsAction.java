package ui;

import model.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//Represents the filter songs button and the action that occurs when clicked
public class FilterSongsAction extends AbstractAction {
    private JPanel tablePanel;

    private static final int NUM_COLS = 8;
    private VideoDatabaseState state;

    //EFFECTS: constructs the filter songs button
    public FilterSongsAction(VideoDatabaseState state, JPanel tablePanel) {
        super("Filter Videos");
        this.tablePanel = tablePanel;
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: when the button is clicked, filters the songs list to only show songs that have the instrument field the
    //same as the user specified assuming that that instrument is in the list
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();
        if (videos == null) {
            JOptionPane.showMessageDialog(null, "There Are No Videos In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            List<Video> filtered = state.sd.filterVideos();
            String[] colNames = {"Video Title", "Views", "Likes", "Dislikes", "Date",
                    "Favorited"};
            ListSongsAction listAction = new ListSongsAction();
            Object[][] data = listAction.getData(filtered);

            JTable songTable = new JTable(data, colNames);
            songTable.getColumnModel().getColumn(5).setCellRenderer(new FavouriteRenderer());
            songTable.setRowHeight(50);
            JScrollPane songPane = new JScrollPane(songTable);
            updateFame(songPane);
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
