package pl.swpws.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import pl.swpws.Main;

public class MainPage {
    private Main mMain;

    public void setMainApp(Main main) {
        mMain = main;
    }

    public void handleFirstTask(ActionEvent actionEvent) {
        mMain.goToFirstTask();
    }

    public void handleCloseMenuItem(ActionEvent actionEvent) {
        mMain.getMainStage().close();
    }

    public void handleAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SWPS University App");
        alert.setHeaderText("About");
        alert.setContentText("Developed by: Manuel Nunez\n" +
                            "Website: https://manununhez.github.io/\n" +
                            "Email:manuel.nunhez90@gmail.com");

        alert.showAndWait();
    }
}
