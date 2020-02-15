package pl.swpws.controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.swpws.common.rating.RatingPlus;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.Map;

import static pl.swpws.model.ApplianceAttribute.*;
import static pl.swpws.model.ApplianceAttribute.AttributesName.*;

public class SecondTask implements EventHandler<KeyEvent> {
    public static final String MAIN_TITLE = "second task";
    private static final String MAIN_PAGE_INSTRUCTION = "Jesteśmy ciekawi na co zwróciłaby Pani uwagę przy zakupie pralki.\n\n"
            + "Prosimy o ocenienie ważności właściwości pralek posługując się plusami. Im" +
            "większa liczba plusów, tym ważniejsza jest dla Pani dana właściwość. Może" +
            "Pani przyznać tę samą liczbę plusów kilku właściwościom. Każda właściwość" +
            "musi mieć co najmniej jeden plus i żadna właściwość nie może mieć więcej niż" +
            "sześć plusów.\n"
            + "Nie ma tu dobrych ani złych odpowiedzi, proszę się kierować własnymi" +
            "preferencjami.";
    private final Stage mStage;
    private final BorderPane mParent;
    private SceneName mSceneName;
    private final HashMap<AttributesName, RatingPlus> ratingPlusHashMap = new HashMap<>();
    private Label labelAlert;

    public SecondTask(Stage stage, BorderPane parent, SceneName sceneName) {
        mStage = stage;
        mParent = parent;
        mSceneName = sceneName;
    }

    public Node getNodeScene() {

        Label labelMainTitle = new Label(MAIN_PAGE_INSTRUCTION);
        labelMainTitle.setFont(new Font(30.0));
        labelMainTitle.setWrapText(true);

        labelAlert = new Label();
        labelAlert.setTextFill(Color.RED);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelMainTitle);
        vBox.getChildren().add(labelAlert);
        vBox.getChildren().add(getGridPaneDescription());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(80, 125, 80, 125));


        vBox.setOnKeyReleased(this);
        vBox.setOnKeyPressed(this);

        return vBox;
    }

    private GridPane getGridPaneDescription() {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(getParamLabel(SPIN_SPEED.label), 1, 0);
        gridPane.add(getRating(SPIN_SPEED), 2, 0);

        gridPane.add(getParamLabel(DRUM_CAPACITY.label), 1, 1);
        gridPane.add(getRating(DRUM_CAPACITY), 2, 1);

        gridPane.add(getParamLabel(ENERGY_CLASS.label), 1, 2);
        gridPane.add(getRating(ENERGY_CLASS), 2, 2);

        gridPane.add(getParamLabel(NOISE_LEVEL.label), 1, 3);
        gridPane.add(getRating(NOISE_LEVEL), 2, 3);

        gridPane.add(getParamLabel(WATER_CONSUMPTION.label), 1, 4);
        gridPane.add(getRating(WATER_CONSUMPTION), 2, 4);

        gridPane.add(getParamLabel(FAST_PROGRAM.label), 1, 5);
        gridPane.add(getRating(FAST_PROGRAM), 2, 5);

        return gridPane;
    }

    private RatingPlus getRating(AttributesName attributesName) {
        RatingPlus ratingPlus = new RatingPlus(6);
        ratingPlus.setPadding(new Insets(10, 0, 10, 0));
        ratingPlus.setRating(0.0);
//        ratingPlus.setRating(2.0); //TODO debugging only

        ratingPlusHashMap.put(attributesName, ratingPlus);

        return ratingPlus;
    }

    private Label getParamLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font(20.0));
        label.setPadding(new Insets(5, 0, 5, 0));
        return label;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
            int b = 0;

            //loop through values of selected rating per attribute
            for (Map.Entry<AttributesName, RatingPlus> entry : ratingPlusHashMap.entrySet()) {
                RatingPlus ratingPlus = entry.getValue();
                if (ratingPlus.getRating() <= 0.0) {
                    b++;
                }
            }

            if (b > 0)
                labelAlert.setText("Please select all the attributes rating! Total left:(" + b + ")");
            else {
                labelAlert.setText("");
                goToNextPage();
            }
        }
    }

    private void goToNextPage() {
        TaskPage.goToPage(mSceneName);
    }
}
