package ui;

import model.Song;
import model.SongDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SongOrganizerAppUI extends JFrame {

    private SongDatabase sd;

    private final static int WIDTH = 1000;

    private final static int HEIGHT = 800;


    private JsonWriter jsonWriter;

    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/songOrganizer.json";

    public SongOrganizerAppUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        sd = new SongDatabase("My Song Database");
        setTitle("My Song Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setResizable(false);
        addMenuBottons();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a menuPanel that contains the button options available to the user
    private void addMenuBottons() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.blue);
        menuPanel.setBounds(0, 0, 200, 800);
        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setBounds(10,10,190,40);

        JPanel displayPanel = initializePanel();
        JButton addButton = new JButton(new AddSongAction(sd));
        addButton.setBounds(10,40,180,40);
        JButton deleteButton = new JButton(new DeleteSongAction(sd));
        deleteButton.setBounds(10,90,180,40);
        JButton listButton = new JButton(new ListSongsAction(sd,displayPanel,this));
        listButton.setBounds(10,140,180,40);
        JButton filterButton = new JButton(new FilterSongsAction(sd,displayPanel));
        filterButton.setBounds(10,190,180,40);
        JButton favButton = new JButton(new FavouriteSongAction(sd));
        favButton.setBounds(10,240,180,40);
        JButton unfavButton = new JButton(new UnfavouriteSongAction(sd));
        unfavButton.setBounds(10,290,180,40);
        JButton saveButton = new JButton(new SaveAction(sd,jsonWriter,JSON_STORE));
        saveButton.setBounds(10,340,180,40);
        JButton loadButton = new JButton(new LoadAction(sd,jsonReader,JSON_STORE,this));
        loadButton.setBounds(10,390,180,40);

        menuPanel.add(menuLabel);
        menuPanel.add(addButton);
        menuPanel.add(deleteButton);
        menuPanel.add(listButton);
        menuPanel.add(filterButton);
        menuPanel.add(favButton);
        menuPanel.add(unfavButton);
        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
        add(displayPanel);
        add(menuPanel);
    }

    //EFFECTS: initial displayPanel for displaying relevant output to the user
    private JPanel initializePanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBounds(201,0,800, 800);
        displayPanel.setBackground(Color.GREEN);
        return displayPanel;
    }

}

