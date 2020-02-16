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
import pl.swpws.model.DataGenerator;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.Map;

public class TaskPage {
    private final static int FIRST_TASK_TOTAL_ITERATION = 6;//start counting from 1 to 4 -- 4iterations
    private final static int FIRST_TASK_EXAMPLE_TOTAL_ITERATION = 4;//TODO should be 60
    private final static Map<SceneName, Node> scenes = new HashMap<>(); //Holds the various scenes to switch between

    private static int firstTaskExampleTotalIteration = 0;
    private static int firstTaskTotalIteration = 0;
    private static Stage mStage;
    private static BorderPane mParent;

    public TaskPage(Stage stage, BorderPane parent) {
        mStage = stage;
        mParent = parent;
    }

    public void initScenes() {
        scenes.put(SceneName.USER_FORM, new UserForm(mStage, mParent, SceneName.USER_FORM).getNodeScene());

        scenes.put(SceneName.FIRST_INSTR, new FirstInstruction(mStage, mParent, SceneName.FIRST_INSTR).getNodeScene());
        scenes.put(SceneName.SECOND_INSTR, new SecondInstruction(mStage, mParent, SceneName.SECOND_INSTR).getNodeScene());
        scenes.put(SceneName.THIRD_INSTR, new ThirdInstruction(mStage, mParent, SceneName.THIRD_INSTR).getNodeScene());
        scenes.put(SceneName.FOURTH_INSTR, new FourthInstruction(mStage, mParent, SceneName.FOURTH_INSTR).getNodeScene());
        scenes.put(SceneName.FIFTH_INSTR, new FifthInstruction(mStage, mParent, SceneName.FIFTH_INSTR).getNodeScene());

        scenes.put(SceneName.FIRST_TASK_EXAMPLE, new FirstTask(mStage, mParent, SceneName.FIRST_TASK_EXAMPLE,
                DataGenerator.getApplianceAttributeListExample()).getNodeScene());//4 times

        scenes.put(SceneName.SIXTH_INSTR, new SixthInstruction(mStage, mParent, SceneName.SIXTH_INSTR).getNodeScene());

        scenes.put(SceneName.FIRST_TASK, new FirstTask(mStage, mParent, SceneName.FIRST_TASK,
                DataGenerator.getApplianceAttributeList()).getNodeScene());//60 times

        scenes.put(SceneName.SEVENTH_INSTR, new SeventhInstruction(mStage, mParent, SceneName.SEVENTH_INSTR).getNodeScene());

        scenes.put(SceneName.SECOND_TASK, new SecondTask(mStage, mParent, SceneName.SECOND_TASK).getNodeScene());
        scenes.put(SceneName.FINAL_TASK, new FinalTask(mStage, mParent, SceneName.FINAL_TASK).getNodeScene());

        scenes.put(SceneName.FINAL_INSTR, new FinalInstruction(mStage, mParent, SceneName.FINAL_INSTR).getNodeScene());

    }

    private static void navigateTo(SceneName sceneName, String title, TaskPageType taskPageType) {
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

    public static void navigateTo(SceneName sceneName) {

        switch (sceneName) {
            case MAIN:
                navigateTo(SceneName.USER_FORM, UserForm.MAIN_TITLE, TaskPage.TaskPageType.TASK);
                break;
            case USER_FORM:
                navigateTo(SceneName.FIRST_INSTR, FirstInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FIRST_INSTR:
                navigateTo(SceneName.SECOND_INSTR, SecondInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case SECOND_INSTR:
                navigateTo(SceneName.THIRD_INSTR, ThirdInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case THIRD_INSTR:
                navigateTo(SceneName.FOURTH_INSTR, FourthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FOURTH_INSTR:
                navigateTo(SceneName.FIFTH_INSTR, FifthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FIFTH_INSTR:
                firstTaskExampleTotalIteration++;
                navigateTo(SceneName.FIRST_TASK_EXAMPLE, FirstTask.MAIN_TITLE + " " +
                        firstTaskExampleTotalIteration + "/" + FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
                break;
            case FIRST_TASK_EXAMPLE:
                if (firstTaskExampleTotalIteration == FIRST_TASK_EXAMPLE_TOTAL_ITERATION)
                    navigateTo(SceneName.SIXTH_INSTR, SixthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskExampleTotalIteration++;
                    navigateTo(SceneName.FIRST_TASK_EXAMPLE, FirstTask.MAIN_TITLE + " " +
                            firstTaskExampleTotalIteration + "/" + FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
                }
                break;
            case SIXTH_INSTR:
                firstTaskTotalIteration++;
                navigateTo(SceneName.FIRST_TASK, FirstTask.MAIN_TITLE + " " +
                        firstTaskTotalIteration + "/" + FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
                break;
            case FIRST_TASK:
                if (firstTaskTotalIteration == FIRST_TASK_TOTAL_ITERATION)//should be 60
                    navigateTo(SceneName.SEVENTH_INSTR, SeventhInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskTotalIteration++;
                    navigateTo(SceneName.FIRST_TASK, FirstTask.MAIN_TITLE + " " +
                            firstTaskTotalIteration + "/" + FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
                }
                break;
            case SEVENTH_INSTR:
                navigateTo(SceneName.SECOND_TASK, SecondTask.MAIN_TITLE, TaskPageType.TASK);
                break;
            case SECOND_TASK:
                navigateTo(SceneName.FINAL_TASK, FinalTask.MAIN_TITLE, TaskPageType.TASK);
                break;
            case FINAL_TASK:
                navigateTo(SceneName.FINAL_INSTR, FinalInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
        }
    }

    public void setFirstPage() {
        TaskPage.navigateTo(SceneName.MAIN);
    }

    private enum TaskPageType {
        TASK, INSTRUCTION
    }

}
