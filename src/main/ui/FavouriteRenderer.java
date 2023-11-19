package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.Image.*;

//Represents an icon renderer for the isFavourite field of a song in the JTable
public class FavouriteRenderer extends DefaultTableCellRenderer {
    private static final String FAV_IMG = "./data/favourited.png";
    private static final String UNFAV_IMG = "./data/unfavourited.png";

    //EFFECTS: constructs the renderer for the isFavourite field in the JTable
    public FavouriteRenderer() {
        super();
    }

    //EFFECTS: scales and sets the appropriate heart image icon based on if a given song has been favourited by the user
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        //Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ImageIcon favourite = new ImageIcon(
                new ImageIcon(FAV_IMG).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon unfavourite = new ImageIcon(
                new ImageIcon(UNFAV_IMG).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        String isFavourite = value.toString();
        if (isFavourite.equals("false")) {
            JLabel unfavLabel = new JLabel();
            unfavLabel.setIcon(unfavourite);
            unfavLabel.setVerticalAlignment(CENTER);
            unfavLabel.setHorizontalAlignment(CENTER);
            return unfavLabel;
        } else {
            JLabel favLabel = new JLabel();
            favLabel.setIcon(favourite);
            favLabel.setVerticalAlignment(CENTER);
            favLabel.setHorizontalAlignment(CENTER);
            return favLabel;
        }

    }

}
