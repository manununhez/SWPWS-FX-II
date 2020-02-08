package pl.swpws.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pl.swpws.Main;

import java.io.IOException;

public class SecondTask {
    private static final String VIEW_FINAL_TASK_FXML = "view/FinalTask.fxml";
    private BorderPane mParent;

    public void handleFinalTask(ActionEvent actionEvent) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(VIEW_FINAL_TASK_FXML));
            AnchorPane page = loader.load();

            mParent.setCenter(page);

            // Set the participant into the controller.
            FinalTask controller = loader.getController();
            controller.setParent(mParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParent(BorderPane parent) {
        mParent = parent;
    }
}
