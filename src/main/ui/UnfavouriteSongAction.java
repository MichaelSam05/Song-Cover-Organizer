package ui;

import model.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//represents the unfavourite button for the user to unfavourite a song
public class UnfavouriteSongAction extends AbstractAction {

    private VideoDatabaseState state;

    //EFFECTS: constructs the unfavourite song button
    public UnfavouriteSongAction(VideoDatabaseState state) {
        super("Unfavourite A Video");
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: unfavaourites the song specified by the user given that the song was found in the song database
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();
        if (videos == null) {
            JOptionPane.showMessageDialog(null, "There Are No Videos In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String keyword = JOptionPane.showInputDialog(null, "Please Enter Video Name",
                    "Unfavourite A Video",
                    JOptionPane.PLAIN_MESSAGE);
            Video favVideo = state.sd.searchVideo(keyword);

            if (favVideo == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + keyword
                        + " in the video list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                state.sd.unFavouriteVideo(favVideo);
                JOptionPane.showMessageDialog(null,   keyword
                        + " was successfully favourited", "Unfavourite A Video", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
