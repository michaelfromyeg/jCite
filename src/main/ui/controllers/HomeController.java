package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistence.Save;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {


    @FXML
    private Button btnAssignments;

    @FXML
    private Button btnExports;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignout;

    @FXML
    private Text path;

    //EFFECTS: initializes stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        path.setText(getPath());
    }

    //EFFECTS: load exports screen
    @FXML
    private void viewExports() {
        super.loadStage("../view/Exports.fxml", btnExports);
    }

    //EFFECTS: load assignments screen
    @FXML
    private void manageAssignments() {
        super.loadStage("../view/Assignments.fxml", btnAssignments);
    }

    //EFFECTS: load settings screen
    @FXML
    private void settings() {
        super.loadStage("../view/Settings.fxml", btnSettings);
    }

    //EFFECTS: auto-saves user and returns to login screen
    @FXML
    private void signOutClicked() {
        Save.saveUser(main.getUser());
        main.setUser(null);
        views.clear();
        super.loadStage("../view/Login.fxml", btnSettings);
    }

    //EFFECTS: moves user back on screen
    @FXML
    private void back() {
        this.views.pop(); // Remove this page
        super.loadStage(this.views.pop(), btnBack); // Pop the page being returned to but it'll be re-added
    }

    //EFFECTS: cleans up path text with regex
    private String getPath() {
        return this.views.toString().replaceAll("\\.\\.\\/view\\/|\\.fxml", "");
    }

    //EFFECTS: currently disabled
    @FXML
    private void onTutorialClicked() {
        //TODO: record tutorial video
    }
}
