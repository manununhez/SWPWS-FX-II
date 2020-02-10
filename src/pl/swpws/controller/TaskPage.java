package pl.swpws.controller;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.Map;

public class TaskPage {
    /**
     * Holds the various scenes to switch between
     */
    private static Map<SceneName, Node> scenes = new HashMap<>();

    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static Map<SceneName, Node> getScenes() {
        return scenes;
    }


    public void initScenes(Stage stage, BorderPane parent) {
        scenes.put(SceneName.USER_FORM, new UserForm(stage).getNodeScene());

        parent.setCenter(getScenes().get(SceneName.USER_FORM));

    }

}
