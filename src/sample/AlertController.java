package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    public Label noticeLabel;
    @FXML
    public TextField answerTextField;
    @FXML
    public MediaView media;
    Sound sound = new Sound();

    public void initialize(){
        playSound();
    }

    public void handleStopButtonOnClick(ActionEvent actionEvent) {
        if (answerTextField.getText().equals("45")){
            Stage stage1 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage1.close();
            sound.stopSound();
        }
        else {
            answerTextField.setText(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Answer!!");
            alert.show();
        }
    }

    private void playSound(){
        media = new MediaView(sound.getMediaPlayer());
        sound.play();
    }
}
