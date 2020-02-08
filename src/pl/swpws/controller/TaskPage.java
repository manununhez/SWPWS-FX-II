package pl.swpws.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pl.swpws.Main;

import java.io.IOException;

public class TaskPage {
    private static final String VIEW_FIRST_TASK_FXML = "view/FirstTask.fxml";

    public void setScene(BorderPane parent) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(VIEW_FIRST_TASK_FXML));
            AnchorPane page = loader.load();

            parent.setCenter(page);


            // Set the participant into the controller.
            FirstTask controller = loader.getController();
            controller.setParent(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
