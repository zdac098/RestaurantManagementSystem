package frames;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML public Button signInButton;
    @FXML private TextField userBox;
    @FXML private PasswordField pwBox;
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        connection = DBConnection.get();
    }

    @FXML
    void staffLogin(ActionEvent event) {
        String username = userBox.getText().toString();
        String password = pwBox.getText().toString();
        String query = "SELECT * FROM login_information WHERE username = ? AND password - ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if(rs.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("StaffMenu.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Staff_Menu");
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information dialog");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}