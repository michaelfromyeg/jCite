package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import persistence.Load;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class EditExportController extends ExportController {

    @FXML
    private HTMLEditor htmlEditor;

    //EFFECTS: initalizes stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // super.initialize(location, resources);

        String text = getFileText();
        htmlEditor.setHtmlText(text);
    }

    //EFFECTS: return HTML string from file
    private static String getFileText() {
        StringBuilder contentBuilder = new StringBuilder();
        String export;

        try {
            export = Load.getExportFromString(selectedFileName);
        } catch (FileNotFoundException e) {
            // TODO: Add error handling to main screen
            return "";
        }

        try (Stream<String> stream = Files.lines(Paths.get(export), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            return "";
        }

        return contentBuilder.toString();
    }
}
