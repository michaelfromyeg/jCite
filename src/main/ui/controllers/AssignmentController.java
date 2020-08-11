package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Paper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentController extends HomeController implements Initializable {

    @FXML
    private TableView<Paper> tblAssignments;

    @FXML
    private TableColumn<Paper, String> nameColumn;

    @FXML
    private Text lblTitle;

    @FXML
    private Text lblCourse;

    @FXML
    private Text lblCitations;

    protected Paper selectedPaper;

    //EFFECTS: setup table
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        selectedPaper = null;

        setTable();

        showPaperDetails(null);

        tblAssignments.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPaperDetails(newValue));
    }

    //EFFECTS: reset table items
    private void setTable() {
        tblAssignments.getItems().clear();
        for (Paper p : main.getUser().getPapers()) {
            tblAssignments.getItems().add(p);
        }
    }

    //EFFECTS: show selected paper information on main screen
    private void showPaperDetails(Paper paper) {
        if (paper != null) {
            selectedPaper = paper;
            lblTitle.setText(paper.getTitle());
            lblCourse.setText(paper.getCourse());
            lblCitations.setText(paper.getCitations().toString());
        } else {
            lblTitle.setText("");
            lblCourse.setText("");
            lblCitations.setText("");
        }
    }

    //EFFECTS: create a new paper
    @FXML
    private void createAssignment() {
        Paper tempPaper = new Paper("","");
        boolean okClicked = super.showDialog(tempPaper, "../view/AssignmentEditDialog.fxml");
        if (okClicked) {
            setTable();
        }
    }

    //EFFECTS: edit an existing assignment through a dialog box
    @FXML
    private void editAssignment() {
        Paper selectedPaper = tblAssignments.getSelectionModel().getSelectedItem();
        Paper oldPaper = selectedPaper;
        if (selectedPaper != null) {
            boolean okClicked = super.showDialog(selectedPaper, "../view/AssignmentEditDialog.fxml");
            if (okClicked) {
                showPaperDetails(selectedPaper);
                main.getUser().removePaper(oldPaper);
                setTable();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Paper Selected");
            alert.setContentText("Please select a paper in the table.");

            alert.showAndWait();
        }
    }

    //EFFECTS: add a citation to the selected paper
    @FXML
    private void onAddCitation() {
        Paper selectedPaper = tblAssignments.getSelectionModel().getSelectedItem();
        if (selectedPaper != null) {
            boolean okClicked = super.showCitationDialog(selectedPaper,"../view/AddCitationDialog.fxml");
            if (okClicked) {
                showPaperDetails(selectedPaper);
                setTable();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Paper Selected");
            alert.setContentText("Please select a paper in the table.");
            alert.showAndWait();
        }
    }

    //EFFECTS: generate export for citation (see model for Paper)
    @FXML
    private void generateExport() {
        try {
            selectedPaper.generateCitations();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Paper Selected");
            alert.setContentText("Please select a paper in the table.");
            alert.showAndWait();
        } catch (IOException e) {
            //TODO: add error handling to GUI
        }
    }

}
