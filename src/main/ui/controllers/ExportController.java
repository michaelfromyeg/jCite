package ui.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import persistence.Load;

import java.net.URL;
import java.util.ResourceBundle;

public class ExportController extends HomeController implements Initializable {

    @FXML
    private TableView<String> tblExports;

    @FXML
    private TableColumn<String, String> fileColumn;

    @FXML
    private Button btnPreview;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    protected static String selectedFileName;

    //EFFECTS: initialize stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        fileColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));

        for (String s : Load.getExportsList()) {
            tblExports.getItems().add(s);
        }

        tblExports.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectNewFile(newValue));
    }

    //EFFECTS: select a file from table
    private void selectNewFile(String newFileName) {
        selectedFileName = newFileName;
    }

    //EFFECTS: open edit export screen
    @FXML
    private void previewExport() {
        super.loadStage("../view/EditExport.fxml", btnPreview);
    }

    //EFFECTS: currently disabled
    @FXML
    private void editExport() {
        super.loadStage("../view/EditExport.fxml", btnEdit);
    }

    //EFFECTS: currently disabled
    @FXML
    private void deleteExport() {
        // System.out.println(selectedFileName);
        //TODO: implement (not currently supported in model)
    }

}
