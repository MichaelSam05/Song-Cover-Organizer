package ui;

import model.Video;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SortAction extends AbstractAction {

    private VideoDatabaseState state;
    private JPanel tablePanel;


    public SortAction(VideoDatabaseState state, JPanel panel) {
        super("Sort Songs");
        this.state = state;
        this.tablePanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();
        List<Video> sorted = new ArrayList<>();
        if (videos == null) {
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

            String[] colNames = {"Video Title", "Views", "Likes", "Dislikes", "Date", "Favorite"};
            ListSongsAction listAction = new ListSongsAction();
            Object[][] data = listAction.getData(sorted);
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
}