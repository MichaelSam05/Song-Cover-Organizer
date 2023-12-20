package ui;

import model.Video;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;


//represents what happens when the user wants to view a list of all their songs
public class ListSongsAction extends AbstractAction {

    private JPanel tablePanel;
    private static final int NUM_COLS = 6;
    private VideoDatabaseState state;

    public ListSongsAction() {

    }

    //EFFECTS: constructs the list songs button
    public ListSongsAction(VideoDatabaseState state, JPanel tablePanel) {
        super("List All Songs");
        this.tablePanel = tablePanel;
        this.state = state;
    }


    //MODIFIES: this
    //EFFECTS: when this button is pressed a JTable is placed on the display JPanel to show the list of songs to the
    //user
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();
        if (videos == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
            tablePanel.removeAll();
            tablePanel.revalidate();
            tablePanel.repaint();
        } else {
            String[] colNames = {"Video Title", "Views", "Likes", "Dislikes", "Date",
                    "Favorite"};
            Object[][] data = getData(videos);
            tablePanel.removeAll();
            DefaultTableModel model = new DefaultTableModel(data, colNames);
            JTable songTable = new JTable(model);
            songTable.getColumnModel().getColumn(5).setCellRenderer(new FavouriteRenderer());
            songTable.setRowHeight(25);
            JScrollPane songPane = new JScrollPane(songTable);
            tablePanel.add(songPane);
            tablePanel.revalidate();
            tablePanel.repaint();
        }

    }


    //EFFECTS: gets all the songs stored in the database and puts them into a 2D Object Array
    public Object[][] getData(List<Video> videos) {
        Object[][] data = new Object[videos.size()][NUM_COLS];
        int i;
        for (i = 0; i < videos.size(); i++) {
            data[i][0] = videos.get(i).getTitle();
            data[i][1] = videos.get(i).getViews();
            data[i][2] = videos.get(i).getLikes();
            data[i][3] = videos.get(i).getDislikes();
            data[i][4] = videos.get(i).getDate();
            data[i][5] = videos.get(i).getFavourite();
        }
        return data;
    }
}
