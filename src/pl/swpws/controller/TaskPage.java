package pl.swpws.controller;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.swpws.controller.InstructionTasks.*;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.Map;

public class TaskPage {
    public static final String NEXT_SCREEN_SIGN = "Naciśnij spację, aby przejść dalej.";
    public static final String FINAL_SCREEN_SIGN = "Naciśnij klawisz ESC, aby zakończyć.";
    private static int FIRST_TASK_TOTAL_ITERATION;
    private static int FIRST_TASK_EXAMPLE_TOTAL_ITERATION;//4
    private final static Map<SceneName, RootPage> scenes = new HashMap<>(); //Holds the various scenes to switch between

    private static int firstTaskExampleTotalIteration;
    private static int firstTaskTotalIteration;
    private static Stage mStage;
    private static BorderPane mParent;
    private static Repository mRepository;

    public TaskPage(Stage stage, BorderPane parent, Repository repository) {
        mStage = stage;
        mParent = parent;
        mRepository = repository;

        firstTaskExampleTotalIteration = 0; //init values after every experiment iteration
        firstTaskTotalIteration = 0;//init values after every experiment iteration

        FIRST_TASK_TOTAL_ITERATION = mRepository.getFirstTaskCountIteration();
        FIRST_TASK_EXAMPLE_TOTAL_ITERATION = mRepository.getFirstTaskExampleCountIteration();

    }

    public void initScenes() {
        scenes.put(SceneName.USER_FORM, new UserForm(mStage, mParent, SceneName.USER_FORM, mRepository));
        scenes.put(SceneName.FIRST_TASK_EXAMPLE, new FirstTask(mStage, mParent, SceneName.FIRST_TASK_EXAMPLE, mRepository));
        scenes.put(SceneName.FIRST_TASK, new FirstTask(mStage, mParent, SceneName.FIRST_TASK, mRepository));
        scenes.put(SceneName.SECOND_TASK, new SecondTask(mStage, mParent, SceneName.SECOND_TASK, mRepository));
        scenes.put(SceneName.FINAL_TASK, new FinalTask(mStage, mParent, SceneName.FINAL_TASK, mRepository));

        scenes.put(SceneName.FIRST_INSTR, new FirstInstruction(mStage, mParent, SceneName.FIRST_INSTR, mRepository));
        scenes.put(SceneName.SECOND_INSTR, new SecondInstruction(mStage, mParent, SceneName.SECOND_INSTR, mRepository));
        scenes.put(SceneName.THIRD_INSTR, new ThirdInstruction(mStage, mParent, SceneName.THIRD_INSTR, mRepository));
//        scenes.put(SceneName.FOURTH_INSTR, new FourthInstruction(mStage, mParent, SceneName.FOURTH_INSTR, mRepository));
        scenes.put(SceneName.FIFTH_INSTR, new FifthInstruction(mStage, mParent, SceneName.FIFTH_INSTR, mRepository));
        scenes.put(SceneName.SIXTH_INSTR, new SixthInstruction(mStage, mParent, SceneName.SIXTH_INSTR, mRepository));
        scenes.put(SceneName.SEVENTH_INSTR, new SeventhInstruction(mStage, mParent, SceneName.SEVENTH_INSTR, mRepository));


        scenes.put(SceneName.FINAL_INSTR, new FinalInstruction(mStage, mParent, SceneName.FINAL_INSTR, mRepository));

    }

    private static void navigateTo(SceneName sceneName, String title, TaskPageType taskPageType) {
        mStage.setTitle(title);

        //Set first scene/page -- UserForm
        mParent.setCenter(scenes.get(sceneName).getNodeScene());

        if (sceneName == SceneName.FINAL_INSTR)
            mParent.setBottom(getRectangleNextScreenSign(FINAL_SCREEN_SIGN));
        else
            mParent.setBottom(getRectangleNextScreenSign(NEXT_SCREEN_SIGN));


        if (taskPageType == TaskPageType.TASK)
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        else
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    private static void navigateToFirstTask(SceneName sceneName, int iteration, int totalIteration, TaskPageType taskPageType) {

        String title = FirstTask.MAIN_TITLE + " " + iteration + "/" + totalIteration;

        mStage.setTitle(title);

        FirstTask firstTask = (FirstTask) scenes.get(sceneName);
        firstTask.setQuestionNumber(iteration);
        mParent.setCenter(firstTask.getNodeScene());

        mParent.setBottom(getRectangleNextScreenSign(NEXT_SCREEN_SIGN));

        if (taskPageType == TaskPageType.TASK)
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        else
            //BackgroundColor
            mParent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    private static StackPane getRectangleNextScreenSign(String title) {
        Text text = new Text(title);
        text.setFont(new Font(20.0));
        StackPane stack = new StackPane();
        Rectangle rectangle2D = new Rectangle(70, 70, 500, 50);
        rectangle2D.setArcHeight(10);
        rectangle2D.setArcWidth(10);
        rectangle2D.setFill(Color.FORESTGREEN);
        stack.getChildren().addAll(rectangle2D, text);

        stack.setPadding(new Insets(10, 0, 10, 0));
        return stack;
    }

    //Completed version
    public static void navigateTo(SceneName sceneName) {

        switch (sceneName) {
            case MAIN:
                navigateTo(SceneName.USER_FORM, UserForm.MAIN_TITLE, TaskPageType.TASK);
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
                navigateTo(SceneName.FIFTH_INSTR, FifthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                break;
            case FIFTH_INSTR:
                firstTaskExampleTotalIteration++;
                navigateToFirstTask(SceneName.FIRST_TASK_EXAMPLE, firstTaskExampleTotalIteration, FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
                break;
            case FIRST_TASK_EXAMPLE:
                if (firstTaskExampleTotalIteration == FIRST_TASK_EXAMPLE_TOTAL_ITERATION)
                    navigateTo(SceneName.SIXTH_INSTR, SixthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskExampleTotalIteration++;
                    navigateToFirstTask(SceneName.FIRST_TASK_EXAMPLE, firstTaskExampleTotalIteration, FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
                }
                break;
            case SIXTH_INSTR:
                firstTaskTotalIteration++;
                navigateToFirstTask(SceneName.FIRST_TASK, firstTaskTotalIteration, FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
                break;
            case FIRST_TASK:
                if (firstTaskTotalIteration == FIRST_TASK_TOTAL_ITERATION)//should be 60
                    navigateTo(SceneName.SEVENTH_INSTR, SeventhInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
                else {
                    firstTaskTotalIteration++;
                    navigateToFirstTask(SceneName.FIRST_TASK, firstTaskTotalIteration, FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
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

//    //FirstTask only version
//    public static void navigateTo(SceneName sceneName) {
//
//        switch (sceneName) {
//            case MAIN:
//                navigateTo(SceneName.USER_FORM, UserForm.MAIN_TITLE, TaskPageType.TASK);
//                break;
//            case USER_FORM:
//                navigateTo(SceneName.FIRST_INSTR, FirstInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//            case FIRST_INSTR:
//                navigateTo(SceneName.SECOND_INSTR, SecondInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//            case SECOND_INSTR:
//                navigateTo(SceneName.THIRD_INSTR, ThirdInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//            case THIRD_INSTR:
//                navigateTo(SceneName.FIFTH_INSTR, FifthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//            case FIFTH_INSTR:
//                firstTaskExampleTotalIteration++;
//                navigateToFirstTaskExample(SceneName.FIRST_TASK_EXAMPLE, firstTaskExampleTotalIteration, FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
//                break;
//            case FIRST_TASK_EXAMPLE:
//                if (firstTaskExampleTotalIteration == FIRST_TASK_EXAMPLE_TOTAL_ITERATION)
//                    navigateTo(SceneName.SIXTH_INSTR, SixthInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                else {
//                    firstTaskExampleTotalIteration++;
//                    navigateToFirstTaskExample(SceneName.FIRST_TASK_EXAMPLE, firstTaskExampleTotalIteration, FIRST_TASK_EXAMPLE_TOTAL_ITERATION, TaskPageType.INSTRUCTION);
//                }
//                break;
//            case SIXTH_INSTR:
//                firstTaskTotalIteration++;
//                navigateToFirstTaskExample(SceneName.FIRST_TASK, firstTaskTotalIteration, FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
//                break;
//            case FIRST_TASK:
//                if (firstTaskTotalIteration == FIRST_TASK_TOTAL_ITERATION)//should be 60
//                    navigateTo(SceneName.FINAL_INSTR, FinalInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                else {
//                    firstTaskTotalIteration++;
//                    navigateToFirstTaskExample(SceneName.FIRST_TASK, firstTaskTotalIteration, FIRST_TASK_TOTAL_ITERATION, TaskPageType.TASK);
//                }
//                break;
//        }
//    }

//    //Second and Third version
//    public static void navigateTo(SceneName sceneName) {
//
//        switch (sceneName) {
//            case MAIN:
//                navigateTo(SceneName.USER_FORM, UserForm.MAIN_TITLE, TaskPageType.TASK);
//                break;
//            case USER_FORM:
//                navigateTo(SceneName.SEVENTH_INSTR, SeventhInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//            case SEVENTH_INSTR:
//                navigateTo(SceneName.SECOND_TASK, SecondTask.MAIN_TITLE, TaskPageType.TASK);
//                break;
//            case SECOND_TASK:
//                navigateTo(SceneName.FINAL_TASK, FinalTask.MAIN_TITLE, TaskPageType.TASK);
//                break;
//            case FINAL_TASK:
//                navigateTo(SceneName.FINAL_INSTR, FinalInstruction.MAIN_TITLE, TaskPageType.INSTRUCTION);
//                break;
//        }
//    }

    public void setFirstPage() {
        TaskPage.navigateTo(SceneName.MAIN);
    }

    private enum TaskPageType {
        TASK, INSTRUCTION
    }

}
