package ui;

import model.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

//Represents the delete button as well as the action that occurs when clicked
public class DeleteVideoAction extends AbstractAction {
    private VideoDatabaseState state;

    //EFFECTS: constructs the delete song button
    public DeleteVideoAction(VideoDatabaseState state) {
        super("Delete Video");
        this.state = state;
    }

    //MODIFIES: this
    //EFFECTS: deletes the song specified by the user
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Video> videos = state.sd.getVideos();

        if (videos == null) {
            JOptionPane.showMessageDialog(null, "There Are No Songs In The List", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String keyword = JOptionPane.showInputDialog(null, "Please Enter Song Name",
                    "Delete Video",
                    JOptionPane.PLAIN_MESSAGE);
            Video deleteVideo = state.sd.searchVideo(keyword);

            if (deleteVideo == null) {
                JOptionPane.showMessageDialog(null, "Cannot find " + keyword
                                + " in the song list", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                state.sd.deleteVideo(deleteVideo);
                JOptionPane.showMessageDialog(null,   keyword
                        + " was successfully deleted", "Delete Video", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
