package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.util.List;


//represents what happens when the user wants to view a list of all their songs
public class ListSongsAction extends AbstractAction {

    private SongDatabase sd;
    private JFrame window;


    private static final int NUM_COLS = 8;

    public ListSongsAction(SongDatabase sd) {
        super("List All Songs");
        this.sd = sd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        initializeWindow();
        String[] colNames = {"Song Name", "Artist Name", "Instrument", "Views", "Likes", "Dislikes", "Date",
                "Favorite"};
        Object[][] data = getData();

        JTable songTable = new JTable(data, colNames);
        JScrollPane songPane = new JScrollPane(songTable);

        window.add(songPane);

    }

    //EFFECTS: gets all the songs stored in the database and puts them into a 2D Object Array
    private Object[][] getData() {
        List<Song> songs = sd.getSongs();
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

    //EFFECTS: creates a new window for displaying the table
    private void initializeWindow() {
        window = new JFrame();
        window.setLayout(new FlowLayout());
        window.setSize(400,400);
        //window.setBounds(201,0,800, 800);
        window.setVisible(true);
    }


}
