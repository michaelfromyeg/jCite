package ui.controllers;

import ui.exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import persistence.Load;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable  {

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    //EFFECTS: check if user exists, and log them in to main screen
    @FXML
    private void onLoginClicked() {
        try {
            String entry = username.getText();
            User user = Load.loadUser(entry);
            if (user == null) {
                throw new UserNotFoundException();
            }
            main.setUser(user);
            views.add("../view/Home.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Home.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (UserNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("User Not Found");
            alert.setHeaderText("User Not Found");
            alert.setContentText("Please register an account.");
            alert.showAndWait();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    //EFFECTS: switch to sign up menu
    @FXML
    private void signupClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Register.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: initialize stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
