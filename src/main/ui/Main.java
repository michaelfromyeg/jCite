package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import ui.controllers.*;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    public BorderPane rootLayout;
    private final String homepage = "../view/Login.fxml"; // Relative to file location of controller

    private Controller controller;

    private User user;

    //EFFECTS: begins the application
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("iCite");
        primaryStage.getIcons().add(new Image("ui/icons/book.png"));
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(e -> {
            boolean okClicked = showExitDialog();
            if (okClicked) {
                System.exit(0);
            } else {
                e.consume();
            }
        });

        initRoot();
        initPage();
    }

    // EFFECTS: initializes the root layout
    private void initRoot() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Root.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: begins the login screen
    private void initPage() {
        controller.loadStage(homepage, rootLayout);
    }

    //EFFECTS: handles the user hitting the OS's exit button
    private boolean showExitDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./view/QuitDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Quit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            QuitDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // GETTERS & SETTERS

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    //EFFECTS: call to main to begin the JavaFX application thread
    public static void main(String[] args) {
        launch(args);
    }
}