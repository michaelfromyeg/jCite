package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Citation;
import model.Paper;
import model.styles.MLA;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCitationDialogController extends AssignmentController implements Initializable {

    @FXML
    private ChoiceBox selStyle;

    @FXML
    private TextField fldAuthor;

    @FXML
    private TextField fldTitle;

    @FXML
    private TextField fldLocation;

    @FXML
    private TextField fldPublisher;

    @FXML
    private TextField fldDate;

    private Stage dialogStage;
    private Citation dialogCitation;
    private boolean okClicked = false;
    private Paper dialogPaper;

    //EFFECTS: add items to select menu
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selStyle.getItems().add("MLA");
        selStyle.getItems().add("APA");
        selStyle.getItems().add("Chicago");
        selStyle.getItems().add("IEEE");
    }

    //EFFECTS: set main dialog stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //EFFECTS: return if ok button is clicked
    public boolean isOkClicked() {
        return okClicked;
    }

    //EFFECTS: add dialog on user submit and check for form validation
    @FXML
    private void onSubmit() {
        if (isInputValid()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            if (selStyle.getValue().toString().equals("MLA")) {
                dialogCitation = new MLA(selectedPaper.getID());
                dialogCitation.setTitle(fldTitle.getText());
                dialogCitation.setAuthor(fldAuthor.getText());
                try {
                    dialogCitation.setPublicationDate(formatter.parse(fldDate.getText()));
                } catch (Exception e) {
                    dialogCitation.setPublicationDate(new Date());
                }
                dialogCitation.setPublisher(fldPublisher.getText());
                dialogCitation.setLocation(fldLocation.getText());
            } else {
                showNotSupported();
            }

            if (dialogCitation != null) {
                selectedPaper.addCitation(dialogCitation);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    //EFFECTS: alert user citation style is not supported
    private void showNotSupported() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText("That citation style is not yet supported.");
        alert.showAndWait();
    }

    //EFFECTS: close dialog stage on cancel
    @FXML
    private void onCancel() {
        dialogStage.close();
    }

    //EFFECTS: validate form submission
    private boolean isInputValid() {
        String errorMessage = checkValidations();
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

    //EFFECTS: check all fields have text
    private String checkValidations() {
        String errorMessage = "";
        if (fldTitle.getText() == null || fldTitle.getText().length() == 0) {
            errorMessage += "Invalid title!\n";
        }
        if (fldAuthor.getText() == null || fldAuthor.getText().length() == 0) {
            errorMessage += "Invalid course!\n";
        }
        if (fldDate.getText() == null || fldDate.getText().length() == 0) {
            errorMessage += "Invalid course!\n";
        }
        if (fldLocation.getText() == null || fldLocation.getText().length() == 0) {
            errorMessage += "Invalid course!\n";
        }
        if (fldPublisher.getText() == null || fldLocation.getText().length() == 0) {
            errorMessage += "Invalid course!\n";
        }
        return errorMessage;
    }

    //EFFECTS: set the selected paper
    public void setPaper(Paper paper) {
        this.selectedPaper = paper;
    }
}
