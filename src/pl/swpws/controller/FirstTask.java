package pl.swpws.controller;

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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.swpws.common.rating.RatingPlus;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.Attribute;
import pl.swpws.model.QuestionFirstTask;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

import java.util.HashMap;
import java.util.List;

public class FirstTask extends RootPage {

    public static final String MAIN_TITLE = "first task";
    private static final String GRID_CSS_PATH = "pl/swpws/common/grid/grid-with-borders.css";
    private static final String APPLIANCE_DEFAULT_NAME = "Pralka ";
    private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Wybierz najlepszą pralkę, naciskając jeden z klawiszy" +
            " na klawiaturze: 1, 2 lub 3.";
    private static final String MAIN_PAGE_INSTRUCTION_MALE = "Wybierz najlepszą pralkę, naciskając jeden z klawiszy" +
            " na klawiaturze: 1, 2 lub 3.";
    private static final String TABLE_TITLE1 = "właściwość";
    private static final String TABLE_TITLE2 = "ważność";
    private static final String GRID_STYLE_CELL_TITLE = "cell-title";
    private static final String GRID_STYLE_CELL = "cell";
    private static final String GRID_STYLE = "grid";
    private static final String FONT_TYPE = "Tahoma";

    private static final double TABLE_TITLE_TEXT_SIZE = 25.0;
    private static final double MAIN_PAGE_INSTRUCTION_TEXT_SIZE = 40.0;
    private static final double PARAM_TEXT_SIZE = 20.0;
    public static final int COLUMN_1 = 1;
    public static final int COLUMN_2 = 2;
    public static final int COLUMN_3 = 3;
    public static final String VALIDATION_ALERT_MESSAGE = "Please select an option!";

    private final ToggleGroup toggleGroup = new ToggleGroup();

    private HashMap<String, RadioButton> radioButtonHashMap = new HashMap<>();
    private List<Attribute> mAttributeList;
    private int mQuestionNumber;

    public FirstTask(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
        super(stage, parent, sceneName, repository);
    }

    public void setQuestionNumber(int questionNumber) {
        mQuestionNumber = questionNumber;
        mAttributeList = mRepository.getAttributeList(mSceneName, questionNumber);
    }

    @Override
    public Node getNodeScene() {
        mParent.getStylesheets().add(GRID_CSS_PATH);

        Label labelMainTitle = new Label(getMainInstruction());
        labelMainTitle.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, MAIN_PAGE_INSTRUCTION_TEXT_SIZE));
        labelMainTitle.setWrapText(true);

        //**********
        GridPane gridPaneTask = getGridPaneAttrValues(); //Grid with appliance attributes values
        RadioButton radioButton1 = getRadioButton(COLUMN_1);
        RadioButton radioButton2 = getRadioButton(COLUMN_2);
        RadioButton radioButton3 = getRadioButton(COLUMN_3);
        radioButtonHashMap.put(String.valueOf(COLUMN_1), radioButton1);
        radioButtonHashMap.put(String.valueOf(COLUMN_2), radioButton2);
        radioButtonHashMap.put(String.valueOf(COLUMN_3), radioButton3);
        gridPaneTask.add(getTableTitleLabelWithStyle(radioButton1, APPLIANCE_DEFAULT_NAME + 1), COLUMN_1, 0);
        gridPaneTask.add(getTableTitleLabelWithStyle(radioButton2, APPLIANCE_DEFAULT_NAME + 2), COLUMN_2, 0);
        gridPaneTask.add(getTableTitleLabelWithStyle(radioButton3, APPLIANCE_DEFAULT_NAME + 3), COLUMN_3, 0);

        //Loading data into the grid
        for (int i = 0; i < mAttributeList.size(); i++) {
            if (mAttributeList.get(i).boldSelector[COLUMN_1 - 1] == 1)
                gridPaneTask.add(getParamLabelWithStyle(mAttributeList.get(i).values[COLUMN_1 - 1]), COLUMN_1, i + 1);
            else
                gridPaneTask.add(getParamLabelWithBoldStyle(mAttributeList.get(i).values[COLUMN_1 - 1]), COLUMN_1, i + 1);

            if (mAttributeList.get(i).boldSelector[COLUMN_2 - 1] == 1)
                gridPaneTask.add(getParamLabelWithStyle(mAttributeList.get(i).values[COLUMN_2 - 1]), COLUMN_2, i + 1);
            else
                gridPaneTask.add(getParamLabelWithBoldStyle(mAttributeList.get(i).values[COLUMN_2 - 1]), COLUMN_2, i + 1);

            if (mAttributeList.get(i).boldSelector[COLUMN_3 - 1] == 1)
                gridPaneTask.add(getParamLabelWithStyle(mAttributeList.get(i).values[COLUMN_3 - 1]), COLUMN_3, i + 1);
            else
                gridPaneTask.add(getParamLabelWithBoldStyle(mAttributeList.get(i).values[COLUMN_3 - 1]), COLUMN_3, i + 1);
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
        vBox.setPadding(new Insets(40, 80, 40, 80));

        vBox.setFocusTraversable(true);//To detect keyEvents!
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);
        vBox.requestFocus();

        return vBox;
    }

    private String getMainInstruction() {
        User user = mRepository.getUser();
        if (user.getSex().equals(UserForm.SEX_FEMALE_LABEL))
            return MAIN_PAGE_INSTRUCTION_FEMALE;
        else
            return MAIN_PAGE_INSTRUCTION_MALE;
    }

    private GridPane getGridPaneAttrValues() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.getStyleClass().add(GRID_STYLE);

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
        Label gridTitle1 = getTableTitleLabel(TABLE_TITLE1);
        Label gridTitle2 = getTableTitleLabel(TABLE_TITLE2);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);

        gridPane.add(gridTitle1, COLUMN_1, 0);
        gridPane.add(gridTitle2, COLUMN_2, 0);

        for (int i = 0; i < mAttributeList.size(); i++) {
            gridPane.add(getParamLabel(mAttributeList.get(i).attributeName), COLUMN_1, i + 1);
            gridPane.add(getDisabledRating(Repository.ATTRIBUTE_NUMBER - i), COLUMN_2, i + 1);
        }

        return gridPane;
    }

    private RadioButton getRadioButton(int id) {
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setId(String.valueOf(id));

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
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, PARAM_TEXT_SIZE));
        label.setPadding(new Insets(5, 0, 5, 0));
        return label;
    }

    private StackPane getParamLabelWithStyle(String name) {
        Label label = new Label(name);
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, PARAM_TEXT_SIZE));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        stackPane.getStyleClass().add(GRID_STYLE_CELL);

        return stackPane;
    }

    private StackPane getParamLabelWithBoldStyle(String name) {
        Label label = new Label(name);
        label.setFont(Font.font(FONT_TYPE, FontWeight.BOLD, PARAM_TEXT_SIZE));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        stackPane.getStyleClass().add(GRID_STYLE_CELL);

        return stackPane;
    }

    private Label getTableTitleLabel(String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, TABLE_TITLE_TEXT_SIZE));
        titleLabel.setPadding(new Insets(5, 0, 5, 0));
        return titleLabel;
    }

    private StackPane getTableTitleLabelWithStyle(RadioButton radioButton, String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, TABLE_TITLE_TEXT_SIZE));

        HBox hBox = new HBox(radioButton, titleLabel);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5.0);
        hBox.setPadding(new Insets(0, 30, 0, 30));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hBox);
        stackPane.getStyleClass().add(GRID_STYLE_CELL_TITLE);

        return stackPane;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED && keyEvent.getCode().isDigitKey()) {

            RadioButton selectedRadioBtn = radioButtonHashMap.get(keyEvent.getCode().getName()); //we get the correspondent radiobtn
            if (selectedRadioBtn != null) {
                if (!selectedRadioBtn.isSelected()) {
                    selectedRadioBtn.setSelected(true);
                }
            }

        }

        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
            RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (radioButton == null) {
                new Alert(Alert.AlertType.INFORMATION, VALIDATION_ALERT_MESSAGE).show();
            } else {
                int index = Integer.parseInt(radioButton.getId());


                radioButton.setSelected(false); //to clean the selected value after every page iteration (reset radio-buttons)


                //save value
                saveTask(index);


                goToNextPage();

            }
        }
    }

    private void saveTask(int index) {
        //TODO debug only
        System.out.println("First Task");
        System.out.println("Question #" + mQuestionNumber + " - Selected Pralka: " + index);//TODO Debug Only


        //save value
        mRepository.saveFirstTask(new QuestionFirstTask(mRepository.getUser().getId(), mAttributeList.get(index - 1).id, mQuestionNumber, index));

    }

}
