package pl.swpws.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import pl.swpws.Main;

public class MainPage {
    public static final String DEVELOPER_INFO = "Developed by: Manuel Nunez\n" +
            "Website: https://manununhez.github.io/\n" +
            "Email:manuel.nunhez90@gmail.com";
    public static final String ALERT_DIALOG_ABOUT = "About";

    private Main mMain;

    public void setMainApp(Main main) {
        mMain = main;
    }

    public void handleFirstTask(ActionEvent actionEvent) {
        mMain.goToTaskPage();
    }

    public void handleCloseMenuItem(ActionEvent actionEvent) {
        mMain.getMainStage().close();
    }

    public void handleAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Main.PRIMARY_STAGE_TITLE);
        alert.setHeaderText(ALERT_DIALOG_ABOUT);
        alert.setContentText(DEVELOPER_INFO);

        alert.showAndWait();
    }
}
