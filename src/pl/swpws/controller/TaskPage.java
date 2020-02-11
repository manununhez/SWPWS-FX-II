package pl.swpws.controller;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.swpws.model.ApplianceAttribute;
import pl.swpws.model.ApplianceAttribute.EnergyClass;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.List;
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

    private List<ApplianceAttribute> applianceAttributeList = List.of(
            new ApplianceAttribute(1000, 4, EnergyClass.APLUS2, 60, 65, false),
            new ApplianceAttribute(1000, 8, EnergyClass.APLUS, 70, 65, false),
            new ApplianceAttribute(1000, 4, EnergyClass.APLUS, 60, 45, true)
    );

    public void initScenes(Stage stage, BorderPane parent) {

        scenes.put(SceneName.USER_FORM, new UserForm(stage, parent).getNodeScene());
        //This first task will be generated 60 times
        scenes.put(SceneName.FIRST_TASK, new FirstTask(stage, parent).getNodeScene(applianceAttributeList));
        scenes.put(SceneName.SECOND_TASK, new SecondTask(stage, parent).getNodeScene());

        //Set first scene/page -- UserForm
        parent.setCenter(getScenes().get(SceneName.USER_FORM));
        stage.setTitle(UserForm.MAIN_TITLE);

    }


}
