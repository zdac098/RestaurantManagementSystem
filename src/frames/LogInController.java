package frames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.ConnectionUtil;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogInController {
    @FXML
    private TextField unBox;
    @FXML
    private PasswordField pwBox;

    public Label signIn;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public LogInController() {
        connection = ConnectionUtil.connectDB();
    }
    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        String username = unBox.getText().toString();
        String password = pwBox.getText().toString();
        String query = "SELECT * FROM staff WHERE username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                signIn.setText("Incorrect username and password!");
                signIn.setTextFill(Color.RED);
            } else {
                Node source =(Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("StaffMenu.xml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
