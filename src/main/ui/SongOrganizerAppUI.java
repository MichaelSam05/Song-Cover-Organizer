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
    private SongOrganizerApp soa;

    private SongDatabase sd;

    private final static int WIDTH = 400;

    private final static int HEIGHT = 400;


    public SongOrganizerAppUI() {
        try {
            soa = new SongOrganizerApp();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        sd = new SongDatabase("My Song Database");
        setTitle("My Song Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        addMenuBottons();
    }

    private void addMenuBottons() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.blue);
        menuPanel.setBounds(100, 0, 200, 400);

        JButton addButton = new JButton(new AddSongAction());
        JButton deleteButton = new JButton("Remove Song");

        menuPanel.add(addButton);
        menuPanel.add(deleteButton);
        add(menuPanel);
    }

}

