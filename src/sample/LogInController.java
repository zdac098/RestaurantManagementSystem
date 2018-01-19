package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.awt.event.ActionEvent;

public class LogInController {
    public Label signIn;

    public void sayLogin(javafx.event.ActionEvent actionEvent) {
        signIn.setText("Signing in");
        signIn.setTextFill(Color.GREEN);
    }
}
