package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class AlarmClock {

    @FXML
    public TextArea timeText;
    @FXML
    public TextArea dateText;
    @FXML
    public ChoiceBox hourSelector;
    @FXML
    public ChoiceBox minuteSelector;
    @FXML
    public Label showLabel;
    @FXML
    public TextField noticeTextField;

    private int hourSet;
    private int minuteSet;
    private String notice;

    public void initialize() {
        clock();
        showLabel.setText("Alarm not set.");
        for(int i = 0;i < 60;i++){
            minuteSelector.getItems().addAll(i);
            if(i < 24) {
                hourSelector.getItems().addAll(i);
            }
        }
    }

    public void clock() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                LocalDateTime ldt = LocalDateTime.now();
                timeText.setText(String.valueOf(ldt.getHour()) + ":" + String.valueOf(ldt.getMinute()) + ":" + String.valueOf(ldt.getSecond()));
                dateText.setText(String.valueOf(ldt.getDayOfMonth()) + " " + String.valueOf(ldt.getMonth()) + " " + String.valueOf(ldt.getYear()));
                if (hourSet == ldt.getHour() && minuteSet == ldt.getMinute()) {
                    Platform.runLater(() -> {
                        showLabel.setText("Alarm not set.");
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Alert.fxml"));
                            Stage stage = new Stage();
                            stage.setScene(new Scene(fxmlLoader.load()));
                            stage.setTitle("Wake up!");
                            stage.setResizable(false);
                            AlertController alertController = fxmlLoader.getController();
                            alertController.noticeLabel.setText(notice);
                            stage.show();
                        } catch (Exception ex) {
                            System.out.println("Can't load new window");
                        }
                    });
                    hourSet = -1;
                    minuteSet = -1;
                }
            }
        }).start();
    }

    public void handleSetButtonOnClick(ActionEvent actionEvent) {
        hourSet = Integer.valueOf((Integer) hourSelector.getValue());
        minuteSet = Integer.valueOf((Integer) minuteSelector.getValue());
        notice = noticeTextField.getText();
        showLabel.setText("Alarm set  " + hourSet + " : " + minuteSet + " O'clock");
        hourSelector.setValue(null);
        minuteSelector.setValue(null);
        noticeTextField.setText(null);
    }

}
