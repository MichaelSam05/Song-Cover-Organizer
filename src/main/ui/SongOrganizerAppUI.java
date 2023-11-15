package ui;

import model.Song;
import model.SongDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SongOrganizerAppUI extends JFrame {

    public SongDatabase sd;

    private final static int WIDTH = 1000;

    private final static int HEIGHT = 800;



    public SongOrganizerAppUI() {

        sd = new SongDatabase("My Song Database");

        setTitle("My Song Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setResizable(false);
        addMenuBottons();
        setVisible(true);
    }

    private void addMenuBottons() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.blue);
        menuPanel.setBounds(0, 0, 200, 800);
        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setBounds(10,10,190,40);
        JButton addButton = new JButton(new AddSongAction(sd));
        addButton.setBounds(10,40,180,40);
        JButton deleteButton = new JButton(new DeleteSongAction(sd));
        deleteButton.setBounds(10,90,180,40);
        JButton listButton = new JButton(new ListSongsAction(sd));
        listButton.setBounds(10,140,180,40);

        menuPanel.add(menuLabel);
        menuPanel.add(addButton);
        menuPanel.add(deleteButton);
        menuPanel.add(listButton);
        add(menuPanel);
    }

}

