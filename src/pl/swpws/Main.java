package pl.swpws;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.swpws.controller.MainPage;
import pl.swpws.controller.TaskPage;

import java.io.IOException;

public class Main extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/MainPage.fxml";
    private static final String VIEW_TASK_PAGE_FXML = "view/TaskPage.fxml";
    private static final String VIEW_FIRST_TASK_PAGE_FXML = "view/SecondTask.fxml";

    private static final String PRIMARY_STAGE_TITLE = "SWSP University App";
    private static final String FIRST_TASK_STAGE_TITLE = "FirstTask";
    private Stage mPrimaryStage;
    private VBox rootLayout;

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage stage) throws Exception {
        mPrimaryStage = stage;
        mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE);

        //you can use underdecorated or transparent.
        mPrimaryStage.initStyle(StageStyle.TRANSPARENT);

        initLayout();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the root layout.
     */
    private void initLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(VIEW_ROOT_LAYOUT_FXML));
            rootLayout = loader.load();

            //grab your root here
            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            //move around here
            rootLayout.setOnMouseDragged(event -> {
                mPrimaryStage.setX(event.getScreenX() - xOffset);
                mPrimaryStage.setY(event.getScreenY() - yOffset);
            });

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mPrimaryStage.setScene(scene);
            mPrimaryStage.show();

            //Give the controller access to the main app.
            MainPage controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getMainStage() {
        return mPrimaryStage;
    }


    public void goToTaskPage() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(VIEW_TASK_PAGE_FXML));
//            AnchorPane borderPane = loader.load();
            BorderPane borderPane = loader.load();

            // Create the dialog Stage.
            Stage mSecondaryStage = new Stage();
            mSecondaryStage.setTitle(FIRST_TASK_STAGE_TITLE);
            mSecondaryStage.initModality(Modality.WINDOW_MODAL);
            mSecondaryStage.initOwner(mPrimaryStage);
            mSecondaryStage.setMaximized(true);

            //new scene
            Scene scene = new Scene(borderPane);
            mSecondaryStage.setScene(scene);

            // Set the participant into the controller.
            TaskPage controller = loader.getController();
//            TestingFirstTask controller = loader.getController();
            //controller.setStage(mPrimaryStage);
            controller.initScenes(mSecondaryStage, borderPane);

            // Show the dialog and wait until the user closes it
            mSecondaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
