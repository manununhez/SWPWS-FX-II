package pl.swpws.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
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
import pl.swpws.model.ApplianceAttribute.AttributesMeasurementUnit;
import pl.swpws.model.ApplianceAttribute.AttributesName;
import pl.swpws.model.ApplianceAttribute.EnergyClass;
import pl.swpws.model.ApplianceAttribute.FastProgram;

import java.util.HashMap;
import java.util.List;

public class FinalTask implements EventHandler<KeyEvent> {
    public static final String MAIN_TITLE = "final task";
    private static final String MAIN_PAGE_INSTRUCTION = "Dodatkowo prosimy o wskazanie akceptowalnego " +
            "poziomu każdej właściwości pralki, którą kupowałaby Pani dla siebie.\n" +
            "Przy każdej właściwości prosimy o wybranie wartości, którą uznałaby Pani za wystarczającą " +
            "przy zakupie nowej pralki.";
    private static final String GRID_CSS_PATH = "pl/swpws/common/grid/grid-with-borders.css";

    private HashMap<AttributesName, List<String>> attributesNameListHashMap = new HashMap<>();
    private HashMap<AttributesName, BooleanProperty[]> attributesBooleanHashMap = new HashMap<>();
    private BorderPane mParent;


    public FinalTask(Stage stage, BorderPane parent) {
        mParent = parent;

        getData();
    }

    private void getData() {
        attributesNameListHashMap.put(AttributesName.SPIN_SPEED,
                List.of("800", "1000", "1200", "1400", "1600"));
        attributesNameListHashMap.put(AttributesName.DRUM_CAPACITY,
                List.of("4", "5", "6", "7", "8", "9", "10"));
        attributesNameListHashMap.put(AttributesName.ENERGY_CLASS,
                List.of(EnergyClass.A.label,
                        EnergyClass.APLUS.label,
                        EnergyClass.APLUS2.label,
                        EnergyClass.APLUS3.label));
        attributesNameListHashMap.put(AttributesName.NOISE_LEVEL,
                List.of("70", "65", "60", "55", "50", "45", "40"));
        attributesNameListHashMap.put(AttributesName.WATER_CONSUMPTION,
                List.of("70", "60", "50", "40", "30"));
        attributesNameListHashMap.put(AttributesName.FAST_PROGRAM,
                List.of(FastProgram.YES.label, FastProgram.NO.label));
    }

    public Node getNodeScene() {
        int rowIndex = 0;

        mParent.getStylesheets().add(GRID_CSS_PATH);

        Label labelMainTitle = new Label(MAIN_PAGE_INSTRUCTION);
        labelMainTitle.setFont(new Font(40.0));
        labelMainTitle.setWrapText(true);


        GridPane gridMainPane = new GridPane();
        gridMainPane.setAlignment(Pos.CENTER);
        gridMainPane.getStyleClass().add("grid2");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        col1.setFillWidth(true);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setFillWidth(true);
        col2.setHgrow(Priority.ALWAYS);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        col3.setFillWidth(true);
        col3.setHgrow(Priority.ALWAYS);

        gridMainPane.getColumnConstraints().addAll(col1, col2, col3);

        gridMainPane.add(getTableTitleLabel(""), 0, rowIndex);
        gridMainPane.add(getTableTitleLabel("Preferencje (po prawej lepsze)"), 1, rowIndex);
        gridMainPane.add(getTableTitleLabel("Wybrane"), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.SPIN_SPEED.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.SPIN_SPEED,
                attributesNameListHashMap.get(AttributesName.SPIN_SPEED), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.SPIN_SPEED)), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.DRUM_CAPACITY.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.DRUM_CAPACITY,
                attributesNameListHashMap.get(AttributesName.DRUM_CAPACITY), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.DRUM_CAPACITY)), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.ENERGY_CLASS.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.ENERGY_CLASS,
                attributesNameListHashMap.get(AttributesName.ENERGY_CLASS), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.ENERGY_CLASS)), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.NOISE_LEVEL.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.NOISE_LEVEL,
                attributesNameListHashMap.get(AttributesName.NOISE_LEVEL), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.NOISE_LEVEL)), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.WATER_CONSUMPTION.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.WATER_CONSUMPTION,
                attributesNameListHashMap.get(AttributesName.WATER_CONSUMPTION), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.WATER_CONSUMPTION)), 2, rowIndex);
        rowIndex++;

        gridMainPane.add(getParamLabel(AttributesName.FAST_PROGRAM.label), 0, rowIndex);
        gridMainPane.add(gridPanePerAttribute(rowIndex, AttributesName.FAST_PROGRAM,
                attributesNameListHashMap.get(AttributesName.FAST_PROGRAM), gridMainPane), 1, rowIndex);
        gridMainPane.add(getParamLabelCentered(getSelectedValue(AttributesName.FAST_PROGRAM)), 2, rowIndex);


        VBox vBox = new VBox();
        vBox.setSpacing(20.0);
        vBox.getChildren().add(labelMainTitle);
        vBox.getChildren().add(gridMainPane);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);

        return vBox;
    }


    private Label getTableTitleLabel(String name) {
        Label titleLabel = new Label(name);
        titleLabel.setFont(new Font(30.0));
        titleLabel.setPadding(new Insets(5, 5, 5, 5));
        titleLabel.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        titleLabel.getStyleClass().add("cell-title");
        titleLabel.setAlignment(Pos.CENTER);
        return titleLabel;
    }

    private Label getParamLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font(20.0));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        label.getStyleClass().add("cell2");

        return label;
    }

    private Label getParamLabelCentered(String name) {
        Label label = new Label(name);
        label.setFont(new Font(20.0));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE); //allows cell resizing
        label.getStyleClass().add("cell2");

        return label;
    }

    private String getSelectedValue(AttributesName attributesName) {
        BooleanProperty[] booleanProperties = attributesBooleanHashMap.get(attributesName);
        for (int i = 0; i < booleanProperties.length; i++) {
            if (booleanProperties[i].get())
                return attributesNameListHashMap.get(attributesName).get(i);
        }

        return "-";
    }

    private GridPane gridPanePerAttribute(int rowIndex, AttributesName attributesName, List<String> list,
                                          GridPane gridMainPane) {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(40.0);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("cell2");

        //Cell resizing constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS); // allow column to grow
        cc.setFillWidth(true); // ask nodes to fill space for column

        BooleanProperty[] booleanProperties = new BooleanProperty[list.size()];

        for (int i = 0; i < list.size(); i++) { //init values
            booleanProperties[i] = new SimpleBooleanProperty();
        }

        for (int i = 0; i < list.size(); i++) { //create cells/columns
            gridPane.add(createCell(rowIndex, attributesName, booleanProperties, list.get(i), gridMainPane, gridPane), i, 0);
            gridPane.getColumnConstraints().add(cc); //allows cell resizing, column constraint

        }

        attributesBooleanHashMap.put(attributesName, booleanProperties);

        return gridPane;
    }

    private StackPane createCell(int gridRootRowIndex, AttributesName attributesName, BooleanProperty[] cellSwitch,
                                 String text, GridPane gridRoot, GridPane gridParent) {

        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(15.0));
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
                            cellSwitch[gridParentRowIndex].set(false);
                            labelTmp.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                        } else { //we set the current cell background color active
                            cellSwitch[gridParentRowIndex].set(true); //we keep track of the selected value
                            labelTmp.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                            updateGridRootSelectedColumn(attributesName, gridRoot, gridParentRowIndex, gridRootRowIndex);
                        }
                    }
                }

            }
        });
        
        cell.getChildren().add(label);

        return cell;
    }

    private void updateGridRootSelectedColumn(AttributesName attributesName, GridPane gridRoot,
                                              int gridParentRowIndex, int gridRootRowIndex) {

        Label label = (Label) getNodeByRowColumnIndex(gridRootRowIndex, 2, gridRoot); //column two is always the "Wybrane" column
        String prefixText = "";
        String sufixText = "";

        //According to specs: Fast program does not have max/min, and max values are
        // only water consume and noise level
        if (!attributesName.equals(AttributesName.FAST_PROGRAM)) {
            if (attributesName.equals(AttributesName.WATER_CONSUMPTION) ||
                    attributesName.equals(AttributesName.NOISE_LEVEL))
                prefixText = "Max";
            else
                prefixText = "Min";
        }

        //According to specs: Fast program and energy class do not have unit of measurement
        switch (attributesName) {
            case SPIN_SPEED:
                sufixText = AttributesMeasurementUnit.SPIN_SPEED.label;
                break;
            case DRUM_CAPACITY:
                sufixText = AttributesMeasurementUnit.DRUM_CAPACITY.label;
                break;
            case WATER_CONSUMPTION:
                sufixText = AttributesMeasurementUnit.WATER_CONSUMPTION.label;
                break;
            case NOISE_LEVEL:
                sufixText = AttributesMeasurementUnit.NOISE_LEVEL.label;
                break;
        }


        label.setText(prefixText + " " +
                attributesNameListHashMap.get(attributesName).get(gridParentRowIndex) +
                " " + sufixText);

    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
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
            if (formValid()) {
                //save selected values and go to next page. Get values from attributesNameListHashMap and
                // the indexes from attributesBooleanHashMap
            }

        }
    }

    private boolean formValid() {
        //if(a)
        return true;
    }
}
