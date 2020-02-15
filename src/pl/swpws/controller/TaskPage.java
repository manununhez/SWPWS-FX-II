package pl.swpws.controller;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.swpws.controller.InstructionTasks.*;
import pl.swpws.model.ApplianceAttribute;
import pl.swpws.model.ApplianceAttribute.EnergyClass;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskPage {
    private final static int FIRST_TASK_TOTAL_ITERATION = 6;//start counting from 1 to 4 -- 4iterations
    private final static int FIRST_TASK_EXAMPLE_TOTAL_ITERATION = 4;//should be 60

    private static Stage mStage;
    private static BorderPane mParent;
    private static int firstTaskExampleTotalIteration = 0;//start counting from 1 to 4 -- 4iterations
    private static int firstTaskTotalIteration = 0;


    public void setStage(Stage stage) {
        mStage = stage;
    }

    public void setParent(BorderPane borderPane) {
        mParent = borderPane;
    }

    public enum TaskPageType {
        TASK, INSTRUCTION
    }

    /**
     * Holds the various scenes to switch between
     */
    private static final Map<SceneName, Node> scenes = new HashMap<>();

//    /**
//     * Returns a Map of the scenes by {@link SceneName}
//     */
//    public Map<SceneName, Node> getScenes() {
//        return scenes;
//    }

    private final List<ApplianceAttribute> applianceAttributeList = List.of(
            new ApplianceAttribute(1000, 4, EnergyClass.APLUS2, 60, 65, false),
            new ApplianceAttribute(1000, 8, EnergyClass.APLUS, 70, 65, false),
            new ApplianceAttribute(1000, 4, EnergyClass.APLUS, 60, 45, true)
    );


    public void initScenes() {
        scenes.put(SceneName.USER_FORM, new UserForm(mStage, mParent, SceneName.USER_FORM).getNodeScene());

        scenes.put(SceneName.FIRST_INSTR, new FirstInstruction(mStage, mParent, SceneName.FIRST_INSTR).getNodeScene());
        scenes.put(SceneName.SECOND_INSTR, new SecondInstruction(mStage, mParent, SceneName.SECOND_INSTR).getNodeScene());
        scenes.put(SceneName.THIRD_INSTR, new ThirdInstruction(mStage, mParent, SceneName.THIRD_INSTR).getNodeScene());
        scenes.put(SceneName.FOURTH_INSTR, new FourthInstruction(mStage, mParent, SceneName.FOURTH_INSTR).getNodeScene());
        scenes.put(SceneName.FIFTH_INSTR, new FifthInstruction(mStage, mParent, SceneName.FIFTH_INSTR).getNodeScene());

        scenes.put(SceneName.FIRST_TASK_EXAMPLE, new FirstTask(mStage, mParent, SceneName.FIRST_TASK_EXAMPLE, applianceAttributeList).getNodeScene());//4 times

        scenes.put(SceneName.SIXTH_INSTR, new SixthInstruction(mStage, mParent, SceneName.SIXTH_INSTR).getNodeScene());

        scenes.put(SceneName.FIRST_TASK, new FirstTask(mStage, mParent, SceneName.FIRST_TASK, applianceAttributeList).getNodeScene());//60 times

        scenes.put(SceneName.SEVENTH_INSTR, new SeventhInstruction(mStage, mParent, SceneName.SEVENTH_INSTR).getNodeScene());

        scenes.put(SceneName.SECOND_TASK, new SecondTask(mStage, mParent, SceneName.SECOND_TASK).getNodeScene());
        scenes.put(SceneName.FINAL_TASK, new FinalTask(mStage, mParent, SceneName.FINAL_TASK).getNodeScene());

        scenes.put(SceneName.FINAL_INSTR, new FinalInstruction(mStage, mParent, SceneName.FINAL_INSTR).getNodeScene());

    }

    private static void goToPage(SceneName sceneName, String title, TaskPageType taskPageType) {
        //Set first scene/page -- UserForm
        mParent.setCenter(scenes.get(sceneName));
        mStage.setTitle(title);

        if (taskPageType == TaskPageType.TASK)
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        else
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void goToPage(SceneName sceneName) {

        switch (sceneName) {
            case MAIN:
                goToPage(SceneName.USER_FORM, UserForm.MAIN_TITLE, TaskPage.TaskPageType.TASK);
                break;
            case USER_FORM:
                goToPage(SceneName.FIRST_INSTR, FirstInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FIRST_INSTR:
                goToPage(SceneName.SECOND_INSTR, SecondInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case SECOND_INSTR:
                goToPage(SceneName.THIRD_INSTR, ThirdInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case THIRD_INSTR:
                goToPage(SceneName.FOURTH_INSTR, FourthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FOURTH_INSTR:
                goToPage(SceneName.FIFTH_INSTR, FifthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FIFTH_INSTR:
                firstTaskExampleTotalIteration++;
                goToPage(SceneName.FIRST_TASK_EXAMPLE, FirstTask.MAIN_TITLE+ " " + firstTaskExampleTotalIteration + "/4", TaskPageType.INSTRUCTION);
                break;
            case FIRST_TASK_EXAMPLE:
                if (firstTaskExampleTotalIteration == FIRST_TASK_EXAMPLE_TOTAL_ITERATION)
                    goToPage(SceneName.SIXTH_INSTR, SixthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskExampleTotalIteration++;
                    goToPage(SceneName.FIRST_TASK_EXAMPLE, FirstTask.MAIN_TITLE+ " " + firstTaskExampleTotalIteration + "/4", TaskPageType.INSTRUCTION);
                }
                break;
            case SIXTH_INSTR:
                firstTaskTotalIteration++;
                goToPage(SceneName.FIRST_TASK, FirstTask.MAIN_TITLE + " " + firstTaskTotalIteration + "/6", TaskPageType.TASK);
                break;
            case FIRST_TASK:
                if (firstTaskTotalIteration == FIRST_TASK_TOTAL_ITERATION)//should be 60
                    goToPage(SceneName.SEVENTH_INSTR, SeventhInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskTotalIteration++;
                    goToPage(SceneName.FIRST_TASK, FirstTask.MAIN_TITLE + " " + firstTaskTotalIteration + "/6", TaskPageType.TASK);
                }
                break;
            case SEVENTH_INSTR:
                goToPage(SceneName.SECOND_TASK, SecondTask.MAIN_TITLE, TaskPageType.TASK);
                break;
            case SECOND_TASK:
                goToPage(SceneName.FINAL_TASK, FinalTask.MAIN_TITLE, TaskPageType.TASK);
                break;
            case FINAL_TASK:
                goToPage(SceneName.FINAL_INSTR, FinalInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
        }
    }

}
