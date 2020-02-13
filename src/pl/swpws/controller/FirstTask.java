package pl.swpws.controller;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.swpws.common.rating.RatingPlus;
import pl.swpws.model.ApplianceAttribute;
import pl.swpws.model.ApplianceAttribute.AttributeImportanceLevel;
import pl.swpws.model.SceneName;

import java.util.HashMap;
import java.util.List;

import static pl.swpws.model.ApplianceAttribute.AttributesName.*;

public class FirstTask implements EventHandler<KeyEvent> {

    public static final String MAIN_TITLE = "first task";
    private static final String GRID_CSS_PATH = "pl/swpws/common/grid/grid-with-borders.css";
    private static final String APPLIANCE_DEFAULT_NAME = "Pralka ";
    private static final String MAIN_PAGE_INSTRUCTION = "Wybierz najlepszą pralkę, naciskając jeden zklawiszy" +
            " na klawiaturze: 1,2 lub 3.";
    private final Stage mStage;
    private final BorderPane mParent;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final HashMap<String, RadioButton> radioButtonHashMap = new HashMap<>();
    private List<ApplianceAttribute> mAttributeList;

    public FirstTask(Stage stage, BorderPane parent) {
        mStage = stage;
        mParent = parent;
    }

    public Node getNodeScene(List<ApplianceAttribute> attributeList) {
        mAttributeList = attributeList;

        mParent.getStylesheets().add(GRID_CSS_PATH);


        Label labelMainTitle = new Label(MAIN_PAGE_INSTRUCTION);
        labelMainTitle.setFont(new Font(40.0));
        labelMainTitle.setWrapText(true);

        //**********
        GridPane gridPaneTask = getGridPaneAttrValues(); //Grid with appliance attributes values

        //Loading data into the grid
        for (int i = 0; i < attributeList.size(); i++) {

            int keyIndex = i + 1;
            RadioButton radioButton = getRadioButton(keyIndex);
            radioButtonHashMap.put(String.valueOf(keyIndex), radioButton);

            gridPaneTask.add(getTableTitleLabelWithStyle(radioButton, APPLIANCE_DEFAULT_NAME + keyIndex), keyIndex, 0);
            gridPaneTask.add(getParamLabelWithStyle(String.valueOf(attributeList.get(i).getMaxSpinSpeed())),
                    keyIndex, 1);
            gridPaneTask.add(getParamLabelWithStyle(String.valueOf(attributeList.get(i).getDrumCapacity())),
                    keyIndex, 2);
            gridPaneTask.add(getParamLabelWithStyle(String.valueOf(attributeList.get(i).getEnergyClass())),
                    keyIndex, 3);
            gridPaneTask.add(getParamLabelWithStyle(String.valueOf(attributeList.get(i).getNoiseLevel())),
                    keyIndex, 4);
            gridPaneTask.add(getParamLabelWithStyle(String.valueOf(attributeList.get(i).getWaterConsumption())),
                    keyIndex, 5);
            gridPaneTask.add(getParamLabelWithStyle(attributeList.get(i).isFastProgramTranslated()),
                    keyIndex, 6);
        }

        //**********
        HBox hBox = new HBox();
        hBox.getChildren().add(getGridPaneDescription()); //Grid with appliance attributes description
        hBox.getChildren().add(gridPaneTask);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(80.0);

        VBox vBox = new VBox();
        vBox.setSpacing(50.0);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(labelMainTitle);
        vBox.getChildren().add(hBox);
        vBox.addEventFilter(MouseEvent.ANY, MouseEvent::consume);//block mouseEvents, only keyboard allow
        vBox.setPadding(new Insets(80, 125, 80, 125));

        return vBox;
    }

    private GridPane getGridPaneAttrValues() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.getStyleClass().add("grid");

        ColumnConstraints column = new ColumnConstraints();
        column.setHalignment(HPos.CENTER); //To center column data
        column.setFillWidth(true);
        column.setHgrow(Priority.ALWAYS);

        //3 columns -> 3 possible options
        gridPane.getColumnConstraints().addAll(column, column, column);

        //7 rows -> 7 appliance attributes
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);

        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints,
                rowConstraints, rowConstraints, rowConstraints, rowConstraints);

        return gridPane;
    }

    private GridPane getGridPaneDescription() {
        Label gridTitle1 = getTableTitleLabel("właściwość");
        Label gridTitle2 = getTableTitleLabel("ważność");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);

        gridPane.add(gridTitle1, 1, 0);
        gridPane.add(gridTitle2, 2, 0);

        gridPane.add(getParamLabel(SPIN_SPEED.label), 1, 1);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.SPIN_SPEED.value), 2, 1);

        gridPane.add(getParamLabel(DRUM_CAPACITY.label), 1, 2);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.DRUM_CAPACITY.value), 2, 2);

        gridPane.add(getParamLabel(ENERGY_CLASS.label), 1, 3);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.ENERGY_CLASS.value), 2, 3);

        gridPane.add(getParamLabel(NOISE_LEVEL.label), 1, 4);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.NOISE_LEVEL.value), 2, 4);

        gridPane.add(getParamLabel(WATER_CONSUMPTION.label), 1, 5);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.WATER_CONSUMPTION.value), 2, 5);

        gridPane.add(getParamLabel(FAST_PROGRAM.label), 1, 6);
        gridPane.add(getDisabledRating(AttributeImportanceLevel.FAST_PROGRAM.value), 2, 6);

        return gridPane;
    }

    private RadioButton getRadioButton(int id) {
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(false);
        radioButton.setId(String.valueOf(id));

        radioButton.setOnKeyPressed(this);
        radioButton.setOnKeyReleased(this);

        return radioButton;
    }

    private RatingPlus getDisabledRating(int value) {
        RatingPlus ratingPlus = new RatingPlus(6);
        ratingPlus.setRating(value);
        ratingPlus.setDisable(true);

        ratingPlus.setPadding(new Insets(10, 0, 10, 0));
        return ratingPlus;
    }

    private Label getParamLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font(20.0));
        label.setPadding(new Insets(5, 0, 5, 0));
        return label;
    }

    private StackPane getParamLabelWithStyle(String name) {
        Label label = new Label(name);
        label.setFont(new Font(20.0));
        //label.setPadding(new Insets(15, 0, 15, 0));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        stackPane.getStyleClass().add("cell");

        return stackPane;
    }

    private Label getTableTitleLabel(String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(new Font(25.0));
        titleLabel.setPadding(new Insets(5, 0, 5, 0));
        return titleLabel;
    }

    private StackPane getTableTitleLabelWithStyle(RadioButton radioButton, String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(new Font(25.0));

        HBox hBox = new HBox(radioButton, titleLabel);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5.0);
        hBox.setPadding(new Insets(0, 30, 0, 30));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hBox);
        stackPane.getStyleClass().add("cell-title");

        return stackPane;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED && keyEvent.getCode().isDigitKey()) {

            RadioButton selectedRadioBtn = radioButtonHashMap.get(keyEvent.getCode().getName()); //we get the correspondent radiobtn
            if (selectedRadioBtn != null) {
                selectedRadioBtn.requestFocus();
                if (!selectedRadioBtn.isSelected()) {
                    selectedRadioBtn.setSelected(true);
                }
            }

        }

        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
            RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (radioButton == null) {
                new Alert(Alert.AlertType.INFORMATION, "Please select an option!").show();
            } else {
                int index = Integer.parseInt(radioButton.getId()) - 1;
                System.out.println(mAttributeList.get(index));

                goToNextPage();

            }
        }
    }

    private void goToNextPage() {
        mParent.setCenter(TaskPage.getScenes().get(SceneName.SECOND_TASK));
        mStage.setTitle(SecondTask.MAIN_TITLE);
    }
}
