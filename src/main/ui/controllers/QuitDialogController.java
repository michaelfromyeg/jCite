package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistence.Save;

import java.net.URL;
import java.util.ResourceBundle;


public class QuitDialogController extends Controller implements Initializable {

    @FXML
    private Text lblLastSaved;

    @FXML
    private Button btnSave;

    private Stage dialogStage;
    private boolean okClicked = false;

    //EFFECTS: initializes stage, sets last saved text
    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        String lastSaved;
        try {
            lastSaved = Save.getFileInformation(main.getUser());
        } catch (NullPointerException e) {
            lastSaved = "";
        }
        lblLastSaved.setText(lastSaved);

        if (main.getUser() == null) {
            btnSave.setDisable(true);
        }
    }

    //EFFECTS: sets dialog stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //EFFECTS: returns true if ok is clicked
    public boolean isOkClicked() {
        return okClicked;
    }

    //EFFECTS: handles quit being clicked
    @FXML
    private void onQuit() {
        okClicked = true;
        dialogStage.close();
    }

    //EFFECTS: handles cancel being clicked
    @FXML
    private void onCancel() {
        okClicked = false;
        dialogStage.close();
    }

    //EFFECTS: handles save button being clicked; saves user and displays updated last saved text
    @FXML
    private void onSaveClicked() {
        Save.saveUser(main.getUser());
        String lastSaved;
        try {
            lastSaved = Save.getFileInformation(main.getUser());
        } catch (NullPointerException e) {
            lastSaved = "";
        }
        lblLastSaved.setText(lastSaved);
    }
}
