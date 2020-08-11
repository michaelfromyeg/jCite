package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Paper;

import java.net.URL;
import java.util.ResourceBundle;


public class AssignmentEditDialogController extends AssignmentController {

    @FXML
    private TextField fldCourse;

    @FXML
    private TextField fldTitle;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Paper dialogPaper;

    //EFFECTS: initialize stage
    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }

    //EFFECTS: set dialog stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //EFFECTS: set selected paper, assign text fields to paper text
    public void setPaper(Paper paper) {
        this.selectedPaper = paper;

        fldCourse.setText(paper.getCourse());
        fldTitle.setText(paper.getTitle());
    }

    //EFFECTS: return true is ok button is clicked
    public boolean isOkClicked() {
        return okClicked;
    }

    //EFFECTS: add paper to user on submit
    @FXML
    private void onSubmit() {
        if (isInputValid()) {
            String course  = fldCourse.getText();
            String title = fldTitle.getText();

            dialogPaper = new Paper(course, title);

            main.getUser().addPaper(dialogPaper);

            okClicked = true;
            dialogStage.close();
        }
    }

    //EFFECTS: close the dialog stage on select
    @FXML
    private void onCancel() {
        dialogStage.close();
    }

    //EFFECTS: check all fields have text; else alert user
    private boolean isInputValid() {
        String errorMessage = "";

        if (fldTitle.getText() == null || fldTitle.getText().length() == 0) {
            errorMessage += "Invalid title!\n";
        }
        if (fldCourse.getText() == null || fldCourse.getText().length() == 0) {
            errorMessage += "Invalid course!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
