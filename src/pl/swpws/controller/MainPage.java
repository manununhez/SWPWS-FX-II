package pl.swpws.controller;

import javafx.event.ActionEvent;
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
}
