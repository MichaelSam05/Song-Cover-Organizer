package ui;

import model.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//Represents the favourite song button and the action that occurs when clicked
public class FavouriteSongAction extends AbstractAction {
    private VideoDatabaseState state;

    //EFFECTS: constructs the favourite song button
    public FavouriteSongAction(VideoDatabaseState state) {
        super("Favourite A Video");
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: when the button is clicked, sets the isFavourite field to true given that the song was found in the list
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();
        if (videos == null) {
            JOptionPane.showMessageDialog(null, "There Are No Videos In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String keyword = JOptionPane.showInputDialog(null, "Please Enter Video Name",
                    "Favourite A Video",
                    JOptionPane.PLAIN_MESSAGE);
            Video favVideo = state.sd.searchVideo(keyword);

            if (favVideo == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + keyword
                        + " in the video list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                state.sd.favouriteVideo(favVideo);
                JOptionPane.showMessageDialog(null,   keyword
                        + " was successfully favourited", "Favourite A Video", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
