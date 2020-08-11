package ui.controllers;

import ui.exceptions.UserNameTakenException;
import javafx.event.ActionEvent;
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

public class RegisterController extends Controller implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private Button btnSignup;

    //EFFECTS: checks username is not taken, and creates new user
    public void onSignupClicked(ActionEvent event) {
        try {
            String entry = username.getText();

            boolean nameTaken = Load.usernameTaken(entry);
            if (nameTaken) {
                throw new UserNameTakenException();
            }
            User user = new User(entry);
            main.setUser(user);
            views.add("../view/Home.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Home.fxml"));
            Stage stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (UserNameTakenException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Username Taken");
            alert.setHeaderText("Username Taken");
            alert.setContentText("That username is taken.");
            alert.showAndWait();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    //EFFECTS: switches the login screen
    @FXML
    private void loginClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            Stage stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
