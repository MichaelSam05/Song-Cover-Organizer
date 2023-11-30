package ui;

import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;

//Represents the GUI for this application that the user interacts with
public class SongOrganizerAppUI extends JFrame implements WindowListener {

    private SongDatabaseState state;

    private static final  int FRAME_WIDTH = 1000;

    private static final  int FRAME_HEIGHT = 700;

    private static final  Color FRAME_COLOR = Color.darkGray;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/songOrganizer.json";


    //EFFECTS: constructs the application GUI to be displayed to the user
    public SongOrganizerAppUI() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        state = new SongDatabaseState();
        setTitle(state.NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        setResizable(false);
        constructGUI();
        setVisible(true);
        addWindowListener(this);
    }

    //MODIFIES: this
    //EFFECTS: establishes the layout of the GUI
    private void constructGUI() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(FRAME_COLOR);
        menuPanel.setBounds(0, 0, 200, FRAME_HEIGHT);
        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setBounds(10,10,190,40);
        menuPanel.add(menuLabel);

        JPanel displayPanel = initializePanel();
        createButtons(menuPanel,displayPanel);

        add(displayPanel);
        add(menuPanel);
    }

    //MODIFIES: this, menuPanel, displayPanel
    //EFFECTS: creates the button options available to the user
    private void createButtons(JPanel menuPanel, JPanel displayPanel) {
        JButton addButton = new JButton(new AddSongAction(state));
        addButton.setBounds(10,40,180,40);
        JButton deleteButton = new JButton(new DeleteSongAction(state));
        deleteButton.setBounds(10,90,180,40);
        JButton listButton = new JButton(new ListSongsAction(state,displayPanel));
        listButton.setBounds(10,140,180,40);
        JButton filterButton = new JButton(new FilterSongsAction(state,displayPanel));
        filterButton.setBounds(10,190,180,40);
        JButton favButton = new JButton(new FavouriteSongAction(state));
        favButton.setBounds(10,240,180,40);
        JButton unfavButton = new JButton(new UnfavouriteSongAction(state));
        unfavButton.setBounds(10,290,180,40);
        JButton saveButton = new JButton(new SaveAction(state,jsonWriter,JSON_STORE));
        saveButton.setBounds(10,340,180,40);
        JButton loadButton = new JButton(new LoadAction(state,jsonReader,JSON_STORE));
        loadButton.setBounds(10,390,180,40);

        addButtons(addButton,deleteButton,listButton,filterButton,favButton,unfavButton,saveButton,loadButton,
                menuPanel);
    }

    //MODIFIES: menuPanel
    //EFFECTS: adds the created buttons to the menuPanel
    private void addButtons(JButton addButton, JButton deleteButton, JButton listButton, JButton filterButton,
                            JButton favButton, JButton unfavButton, JButton saveButton, JButton loadButton,
                            JPanel menuPanel) {

        menuPanel.add(addButton);
        menuPanel.add(deleteButton);
        menuPanel.add(listButton);
        menuPanel.add(filterButton);
        menuPanel.add(favButton);
        menuPanel.add(unfavButton);
        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
    }

    //EFFECTS: initializes the displayPanel for displaying relevant output to the user
    private JPanel initializePanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBounds(201,0,800, FRAME_HEIGHT);
        displayPanel.setBackground(FRAME_COLOR);
        return displayPanel;

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Events Logged...");
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.getDate());
            System.out.println(next.getDescription());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }


}



