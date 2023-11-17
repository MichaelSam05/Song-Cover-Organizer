package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;


//represents what happens when the user wants to view a list of all their songs
public class ListSongsAction extends AbstractAction {

    private SongDatabase sd;
    private JFrame window;

    private JFrame frame;

    private JPanel tablePanel;

    private static final int NUM_COLS = 8;

    private static final String FAV_IMG = "./data/favourited.png";

    private static final String UNFAV_IMG = "./data/unfavourited.png";


    private Container mainFrame;


    public ListSongsAction(SongDatabase sd, JPanel tablePanel,JFrame frame) {
        super("List All Songs");
        this.sd = sd;
        this.frame = frame;
        this.tablePanel = tablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Song> songs = sd.getSongs();
        if (songs == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            //initializeWindow();
            String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                    "Favorite"};
            Object[][] data = getData(songs);
            tablePanel.removeAll();
            DefaultTableModel model = new DefaultTableModel(data,colNames);
            JTable songTable = new JTable(model);
            songTable.getColumnModel().getColumn(7).setCellRenderer(new FavouriteRenderer());
            //songTable.getColumnModel().getColumn(7).setCellEditor(new FavouriteEditior());
            songTable.setRowHeight(50);
            JScrollPane songPane = new JScrollPane(songTable);
            tablePanel.add(songPane);
            tablePanel.revalidate();
            tablePanel.repaint();
//            window.add(songPane);
//        tablePanel.add(songPane);
//        mainFrame = frame.getContentPane();
//        mainFrame.add(tablePanel);
//        mainFrame.revalidate();
//        mainFrame.repaint();
//        songPane.setVisible(true);
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
            //ImageIcon image = decideImage(songs.get(i).getFavourite());
            //JLabel lable = new JLabel();
            data[i][7] = songs.get(i).getFavourite();
        }

        return data;
    }

    private ImageIcon decideImage(Boolean isFavourite) {
        ImageIcon favourite = new ImageIcon(FAV_IMG);
        ImageIcon unfavourite = new ImageIcon(UNFAV_IMG);
        if (isFavourite) {
            return favourite;
        }
        return unfavourite;
    }


    //EFFECTS: creates a new panel for displaying the table
    private void initializeWindow() {
//        window = new JFrame();
//        window.setSize(500,500);
//        window.setVisible(true);

        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());
        tablePanel.setBounds(201, 0, 800, 800);
        tablePanel.setBackground(Color.GREEN);
//        cp = frame.getContentPane();
//        cp.add(tablePanel);
//        cp.revalidate();
//        cp.setVisible(true);
    }


}
