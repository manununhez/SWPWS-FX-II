package pl.swpws.controller;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.ApplianceAttribute.AttributeIndex;
import pl.swpws.model.ApplianceAttribute.AttributesID;
import pl.swpws.model.ApplianceAttribute.AttributesMeasurementUnit;
import pl.swpws.model.QuestionFinalTask;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

import java.util.ArrayList;
import java.util.List;

import static pl.swpws.model.ApplianceAttribute.APPLIANCE_ATTRIBUTES_COUNT;
import static pl.swpws.model.ApplianceAttribute.AttributesName.*;

public class FinalTask extends RootPage {
    public static final String MAIN_TITLE = "final task";
    private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Dodatkowo prosimy o wskazanie akceptowalnego " +
            "poziomu każdej właściwości pralki, którą kupowałaby Pani dla siebie.\n" +
            "Przy każdej właściwości prosimy o wybranie wartości, którą uznałaby Pani za wystarczającą " +
            "przy zakupie nowej pralki.";
    private static final String MAIN_PAGE_INSTRUCTION_MALE = "Dodatkowo prosimy o wskazanie akceptowalnego " +
            "poziomu każdej właściwości pralki, którą kupowałby Pan dla siebie.\n" +
            "Przy każdej właściwości prosimy o wybranie wartości, którą uznałby Pan za wystarczającą " +
            "przy zakupie nowej pralki.";
    private static final String GRID_CSS_PATH = "pl/swpws/common/grid/grid-with-borders.css";
    private static final String EMPTY_CELL = "-";
    private static final String TABLE_TITLE2 = "Preferencje (po prawej lepsze)";
    private static final String TABLE_TITLE3 = "Wybrane";
    private static final String TABLE_TITLE1 = "";
    private static final String FONT_TYPE = "Tahoma";
    private static final String GRID_STYLE = "grid2";
    private static final String GRID_CELL_TITLE_STYLE = "cell-title";
    private static final String GRID_CELL_STYLE = "cell2";
    private static final double MAIN_PAGE_INSTRUCTION_TEXT_SIZE = 30.0;
    private static final double TABLE_TITLE_TEXT_SIZE = 25.0;
    private static final double PARAM_TEXT_SIZE = 20.0;
    private static final double TABLE_CELL_VALUE_TEXT_SIZE = 15.0;

    private final Integer[] selectedValues = new Integer[APPLIANCE_ATTRIBUTES_COUNT];

    private List<List<String>> applianceAttributesNameMap;


    public FinalTask(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
        super(stage, parent, sceneName, repository);

        applianceAttributesNameMap = mRepository.getApplianceAttributesNameMap();

        initializeArray();
    }

    private void initializeArray() {
        for (int i = 0; i < APPLIANCE_ATTRIBUTES_COUNT; i++) {
            selectedValues[i] = -1;
        }
    }

    @Override
    public Node getNodeScene() {

        mParent.getStylesheets().add(GRID_CSS_PATH);

        Label labelMainTitle = new Label(getMainInstruction());
        labelMainTitle.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, MAIN_PAGE_INSTRUCTION_TEXT_SIZE));
        labelMainTitle.setWrapText(true);


        GridPane gridMainPane = new GridPane();
        gridMainPane.setAlignment(Pos.CENTER);
        gridMainPane.getStyleClass().add(GRID_STYLE);

        ColumnConstraints col30 = new ColumnConstraints();
        col30.setPercentWidth(30);
        col30.setFillWidth(true);
        col30.setHgrow(Priority.ALWAYS);

        ColumnConstraints col20 = new ColumnConstraints();
        col20.setPercentWidth(20);
        col20.setFillWidth(true);
        col20.setHgrow(Priority.ALWAYS);

        ColumnConstraints col50 = new ColumnConstraints();
        col50.setPercentWidth(50);
        col50.setFillWidth(true);
        col50.setHgrow(Priority.ALWAYS);


        gridMainPane.getColumnConstraints().addAll(col30, col50, col20);

        int rowIndex = 0;

        gridMainPane.add(getTableTitleLabel(TABLE_TITLE1), 0, rowIndex);
        gridMainPane.add(getTableTitleLabel(TABLE_TITLE2), 1, rowIndex);
        gridMainPane.add(getTableTitleLabel(TABLE_TITLE3), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(SPIN_SPEED.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.SPIN_SPEED,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.SPIN_SPEED)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(DRUM_CAPACITY.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.DRUM_CAPACITY,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.DRUM_CAPACITY)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(ENERGY_CLASS.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.ENERGY_CLASS,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.ENERGY_CLASS)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(NOISE_LEVEL.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.NOISE_LEVEL,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.NOISE_LEVEL)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(WATER_CONSUMPTION.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.WATER_CONSUMPTION,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.WATER_CONSUMPTION)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(FAST_PROGRAM.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesID.FAST_PROGRAM,
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(AttributesID.FAST_PROGRAM)), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(EMPTY_CELL), 2, rowIndex);


        VBox vBox = new VBox();
        vBox.setSpacing(40.0);
        vBox.getChildren().add(labelMainTitle);
        vBox.getChildren().add(gridMainPane);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(40, 125, 40, 125));

        vBox.setFocusTraversable(true);//To detect keyEvents!
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);

        return vBox;
    }

    private String getMainInstruction() {
        User user = mRepository.getUser();
        if (user.getSex().equals(UserForm.SEX_FEMALE_LABEL))
            return MAIN_PAGE_INSTRUCTION_FEMALE;
        else
            return MAIN_PAGE_INSTRUCTION_MALE;
    }

    private Label getTableTitleLabel(String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, TABLE_TITLE_TEXT_SIZE));
        titleLabel.setPadding(new Insets(5, 5, 5, 5));
        titleLabel.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        titleLabel.getStyleClass().add(GRID_CELL_TITLE_STYLE);
        titleLabel.setAlignment(Pos.CENTER);
        return titleLabel;
    }

    private Label getParamLabel(String name) {
        Label label = new Label(name);
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, PARAM_TEXT_SIZE));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        label.getStyleClass().add(GRID_CELL_STYLE);

        return label;
    }

    private Label getParamLabelCentered(String name) {
        Label label = new Label(name);
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, PARAM_TEXT_SIZE));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        label.getStyleClass().add(GRID_CELL_STYLE);

        return label;
    }

    private GridPane gridPanePerAttribute(int rowIndex, AttributesID attributesID, List<String> list,
                                          GridPane gridMainPane) {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(40.0);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add(GRID_CELL_STYLE);

        //Cell resizing constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS); // allow column to grow
        cc.setFillWidth(true); // ask nodes to fill space for column


        for (int i = 0; i < list.size(); i++) { //create cells/columns
            gridPane.add(createCell(rowIndex, attributesID, list.get(i), gridMainPane, gridPane), i, 0);
            gridPane.getColumnConstraints().add(cc); //allows cell resizing, column constraint

        }

        return gridPane;
    }

    private StackPane createCell(int gridRootRowIndex, AttributesID attributesID,
                                 String text, GridPane gridRoot, GridPane gridParent) {

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, TABLE_CELL_VALUE_TEXT_SIZE));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        label.setAlignment(Pos.CENTER);

        StackPane cell = new StackPane();
        cell.setId(text);
        cell.setOnMouseClicked(mouseEvent -> {
            //Toggle group animation
            for (int gridParentRowIndex = 0; gridParentRowIndex < gridParent.getColumnCount(); gridParentRowIndex++) {
                if (gridParent.getChildren().get(gridParentRowIndex) instanceof StackPane) {
                    StackPane stackPane = (StackPane) gridParent.getChildren().get(gridParentRowIndex);

                    if (stackPane.getChildren().get(0) instanceof Label) {
                        Label labelTmp = (Label) stackPane.getChildren().get(0);

                        if (!stackPane.getId().equals(cell.getId())) { //we reset the background color of all columns, except the current cell
                            labelTmp.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                        } else { //we set the current cell background color active
                            selectedValues[AttributeIndex.getAttributeIndex(attributesID)] = gridParentRowIndex;//we keep track of the selected value
                            labelTmp.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                            updateGridRootSelectedColumn(attributesID, gridRoot, gridParentRowIndex, gridRootRowIndex);
                        }
                    }
                }

            }
        });

        cell.getChildren().add(label);


        return cell;
    }

    private void updateGridRootSelectedColumn(AttributesID attributesID, GridPane gridRoot,
                                              int gridParentRowIndex, int gridRootRowIndex) {

        Label label = (Label) getNodeByRowColumnIndex(gridRootRowIndex, 2, gridRoot); //column two is always the "Wybrane" column
        String prefixText = "";

        //According to specs: Fast program does not have max/min, and max values are
        // only water consume and noise level
        if (!attributesID.equals(AttributesID.FAST_PROGRAM)) {
            if (attributesID.equals(AttributesID.WATER_CONSUMPTION) ||
                    attributesID.equals(AttributesID.NOISE_LEVEL))
                prefixText = "Max";
            else
                prefixText = "Min";
        }

        String sufixText = AttributesMeasurementUnit.getAttributeMeasurementUnit(attributesID);

        label.setText(prefixText + " " +
                applianceAttributesNameMap.get(AttributeIndex.getAttributeIndex(attributesID)).get(gridParentRowIndex)
                + sufixText);

    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
            //we first check if all rows have selected values
            if (isFormValid()) {
                //save value
                saveTask();

                //go to next page.
                goToNextPage();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Please select all attribute values!").show();
            }

        }
    }

    private void saveTask() {
        List<QuestionFinalTask> finalTaskList = new ArrayList<>();
        User user = mRepository.getUser();
        //TODO debug only
        System.out.println("Final Task");
        for (int i = 0; i < applianceAttributesNameMap.size(); i++) {
            System.out.println(AttributesID.getAttributeID(i) + " : " + applianceAttributesNameMap.get(i).get(selectedValues[i]));

            finalTaskList.add(new QuestionFinalTask(user.getId(), AttributesID.getAttributeID(i), applianceAttributesNameMap.get(i).get(selectedValues[i])));
        }

        mRepository.saveFinalTask(finalTaskList);
    }

    private boolean isFormValid() {
        for (int value : selectedValues)
            if (value < 0) return false;

        return true;
    }
}
