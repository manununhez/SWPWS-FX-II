package pl.swpws.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.swpws.common.rating.RatingPlus;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.QuestionSecondTask;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.swpws.model.ApplianceAttribute.AttributesID;
import static pl.swpws.model.ApplianceAttribute.AttributesName.*;

public class SecondTask extends RootPage {
    public static final String MAIN_TITLE = "second task";
    private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Jesteśmy ciekawi na co zwróciłaby Pani uwagę przy zakupie pralki.\n\n"
            + "Prosimy o ocenienie ważności właściwości pralek posługując się plusami. Im " +
            "większa liczba plusów, tym ważniejsza jest dla Pani dana właściwość. Może " +
            "Pani przyznać tę samą liczbę plusów kilku właściwościom. Każda właściwość " +
            "musi mieć co najmniej jeden plus i żadna właściwość nie może mieć więcej niż " +
            "sześć plusów.\n"
            + "Nie ma tu dobrych ani złych odpowiedzi, proszę się kierować własnymi " +
            "preferencjami.";
    private static final String MAIN_PAGE_INSTRUCTION_MALE = "Jesteśmy ciekawi na co zwróciłaby Pan uwagę przy zakupie pralki.\n\n"
            + "Prosimy o ocenienie ważności właściwości pralek posługując się plusami. Im " +
            "większa liczba plusów, tym ważniejsza jest dla Pana dana właściwość. Może " +
            "Pan przyznać tę samą liczbę plusów kilku właściwościom. Każda właściwość " +
            "musi mieć co najmniej jeden plus i żadna właściwość nie może mieć więcej niż " +
            "sześć plusów.\n"
            + "Nie ma tu dobrych ani złych odpowiedzi, proszę się kierować własnymi " +
            "preferencjami.";
    private static final int MAX_RATING = 6;
    private static final double PARAM_TEXT_SIZE = 20.0;
    private static final double MAIN_PAGE_INSTRUCTION_TEXT_SIZE_SMALL = 30.0;
    private static final String FONT_TYPE = "Tahoma";

    private final HashMap<AttributesID, RatingPlus> ratingPlusHashMap = new HashMap<>();

    private Label labelAlert;

    public SecondTask(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
        super(stage, parent, sceneName, repository);
    }

    @Override
    public Node getNodeScene() {
        Label labelMainTitle = new Label(getMainInstruction());
        labelMainTitle.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, MAIN_PAGE_INSTRUCTION_TEXT_SIZE_SMALL));
        labelMainTitle.setWrapText(true);

        labelAlert = new Label();
        labelAlert.setTextFill(Color.RED);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelMainTitle);
        vBox.getChildren().add(labelAlert);
        vBox.getChildren().add(getGridPaneDescription());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(20, 80, 20, 80));
        vBox.setSpacing(10.0);

        vBox.setOnKeyReleased(this);
        vBox.setOnKeyPressed(this);

        return vBox;
    }

    private String getMainInstruction() {
        User user = mRepository.getUser();
        if (user.getSex().equals(UserForm.SEX_FEMALE_LABEL))
            return MAIN_PAGE_INSTRUCTION_FEMALE;
        else
            return MAIN_PAGE_INSTRUCTION_MALE;
    }

    private GridPane getGridPaneDescription() {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(getParamLabel(SPIN_SPEED.label), 1, 0);
        gridPane.add(getRating(AttributesID.SPIN_SPEED), 2, 0);

        gridPane.add(getParamLabel(DRUM_CAPACITY.label), 1, 1);
        gridPane.add(getRating(AttributesID.DRUM_CAPACITY), 2, 1);

        gridPane.add(getParamLabel(ENERGY_CLASS.label), 1, 2);
        gridPane.add(getRating(AttributesID.ENERGY_CLASS), 2, 2);

        gridPane.add(getParamLabel(NOISE_LEVEL.label), 1, 3);
        gridPane.add(getRating(AttributesID.NOISE_LEVEL), 2, 3);

        gridPane.add(getParamLabel(WATER_CONSUMPTION.label), 1, 4);
        gridPane.add(getRating(AttributesID.WATER_CONSUMPTION), 2, 4);

        gridPane.add(getParamLabel(FAST_PROGRAM.label), 1, 5);
        gridPane.add(getRating(AttributesID.FAST_PROGRAM), 2, 5);

        return gridPane;
    }

    private RatingPlus getRating(AttributesID attributesID) {
        RatingPlus ratingPlus = new RatingPlus(MAX_RATING);
        ratingPlus.setPadding(new Insets(10, 0, 10, 0));
        ratingPlus.setRating(0.0);

        ratingPlusHashMap.put(attributesID, ratingPlus);

        return ratingPlus;
    }

    private Label getParamLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font(PARAM_TEXT_SIZE));
        label.setPadding(new Insets(5, 0, 5, 0));
        return label;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
            int b = 0;

            //loop through values of selected rating per attribute
            for (Map.Entry<AttributesID, RatingPlus> entry : ratingPlusHashMap.entrySet()) {
                RatingPlus ratingPlus = entry.getValue();
                if (ratingPlus.getRating() <= 0.0) {
                    b++;
                }
            }

            if (b > 0)
                labelAlert.setText("Please select all the attributes rating! Total left:(" + b + ")");
            else {
                labelAlert.setText("");

                //save value
                saveTask();

                goToNextPage();

            }
        }
    }

    private void saveTask() {
        List<QuestionSecondTask> secondTaskList = new ArrayList<>();
        User user = mRepository.getUser();
        //TODO debug only
        System.out.println("Second Task");
        for (Map.Entry<AttributesID, RatingPlus> entry : ratingPlusHashMap.entrySet()) {
            RatingPlus ratingPlus = entry.getValue();
            System.out.println(entry.getKey().label + " : " + ratingPlus.getRating());

            secondTaskList.add(new QuestionSecondTask(user.getId(), entry.getKey().label, (int) ratingPlus.getRating()));
        }

        mRepository.saveSecondTask(secondTaskList);
    }

}
