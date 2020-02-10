package pl.swpws.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swpws.model.User;

public class UserForm implements EventHandler<KeyEvent> {

    public static final int PARAM_TEXT_SIZE = 20;
    public static final Font ATTRI_FONT_PROP = Font.font("Tahoma", FontWeight.NORMAL, PARAM_TEXT_SIZE);
    public static final String NUMER_LABEL = "Numer:";
    public static final String WIEK_LABEL = "Wiek:";
    public static final String ZAWOD_LABEL = "Zawod:";
    public static final String PŁEĆ_LABEL = "Płeć:";
    public static final String EDUKACJI_LABEL = "Lata formalnej edukacji:";
    public static final String KOBIETA_LABEL = "Kobieta";
    public static final String MĘŻCZYZNA_LABEL = "Mężczyzna";
    public static final String MAIN_TITLE = "Twoje dane";
    private Stage mStage;
    private TextField numberTxt;
    private TextField ageTxt;
    private TextField professionTxt;
    private TextField educationTxt;
    private Label numberAlert;
    private Label ageAlert;
    private Label professionAlert;
    private Label educationAlert;
    private ToggleGroup sexToggleGroup;

    public UserForm(Stage stage) {

        mStage = stage;
    }


    private Label getLabelAlert() {
        Label label = new Label();
        label.setTextFill(Color.RED);

        return label;
    }

    private Label getLabelForm(String name) {
        Label label = new Label(name);
        label.setFont(ATTRI_FONT_PROP);
        return label;
    }

    public VBox getNodeScene() {
        System.out.println(getClass().getName());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(20);
        //grid.setPadding(new Insets(0, 125, 0, 125)); //margin settings

        //#######
        Text scenetitle = new Text(MAIN_TITLE);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        //grid.add(scenetitle, 0, 0, 2, 1);

        //#######
        numberAlert = getLabelAlert();
        grid.add(numberAlert, 2, 1);

        grid.add(getLabelForm(NUMER_LABEL), 0, 1);

        numberTxt = new TextField();
        numberTxt.setPromptText("hint number");
        numberTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                numberTxt.setText(oldValue);
            } else {
                numberTxt.setText(newValue);
                //isInputValid();//TODO complete
            }
        });
        grid.add(numberTxt, 1, 1);

        //#######
        grid.add(getLabelForm(WIEK_LABEL), 0, 2);

        ageTxt = new TextField();
        ageTxt.setPromptText("hint age");
        ageTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                ageTxt.setText(oldValue);
            } else {
                ageTxt.setText(newValue);
                //isInputValid();//TODO complete
            }
        });
        grid.add(ageTxt, 1, 2);

        ageAlert = getLabelAlert();
        grid.add(ageAlert, 2, 2);

        //#######
        grid.add(getLabelForm(ZAWOD_LABEL), 0, 3);

        professionTxt = new TextField();
        professionTxt.setPromptText("hint profession");
        grid.add(professionTxt, 1, 3);

        professionAlert = getLabelAlert();
        grid.add(professionAlert, 2, 3);

        //#######
        RadioButton rbFemale = new RadioButton(KOBIETA_LABEL);
        RadioButton rbMale = new RadioButton(MĘŻCZYZNA_LABEL);
        HBox hbSex = new HBox();
        sexToggleGroup = new ToggleGroup();
        rbFemale.setToggleGroup(sexToggleGroup);
        rbMale.setToggleGroup(sexToggleGroup);
        rbFemale.setSelected(true);
        //hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbSex.getChildren().add(rbFemale);
        hbSex.getChildren().add(rbMale);
        hbSex.setSpacing(10);

        grid.add(getLabelForm(PŁEĆ_LABEL), 0, 4);
        grid.add(hbSex, 1, 4);

        //#######
        grid.add(getLabelForm(EDUKACJI_LABEL), 0, 5);

        educationTxt = new TextField();
        educationTxt.setPromptText("hint education");
        educationTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}")) {
                educationTxt.setText(oldValue);
            } else {
                educationTxt.setText(newValue);
                //isInputValid();//TODO complete
            }
        });
        grid.add(educationTxt, 1, 5);

        educationAlert = getLabelAlert();
        grid.add(educationAlert, 2, 5);

        Button requestBT = new Button("Request Focus");
        requestBT.setOnAction(actionEvent -> mStage.requestFocus());
        grid.add(requestBT, 2, 6);

        //#######

        VBox vBox = new VBox();
        vBox.setSpacing(30);
        vBox.getChildren().add(scenetitle);
        vBox.getChildren().add(grid);
        vBox.setPadding(new Insets(100, 125, 0, 125));

        //Set keyboard listener
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);

        return vBox;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.SPACE) {
            //keyBoardEvent();
            formValidate();
            System.out.println("Key Pressed: " + event.getCode());

        }

        if (event.getCode() == KeyCode.ESCAPE) {
            System.out.println("Key Pressed: " + event.getCode());
            mStage.close();
        }

    }


    public boolean formValidate() {
        RadioButton selectedSexRadioButton = (RadioButton) sexToggleGroup.getSelectedToggle();

        String numberField = numberTxt.getText().trim();
        String ageField = ageTxt.getText().trim();
        String professionField = professionTxt.getText().trim();
        String educationField = educationTxt.getText().trim();

        if (!numberField.isEmpty() && !ageField.isEmpty() &&
                !professionField.isEmpty() && !educationField.isEmpty()) {
            User user = new User(Integer.parseInt(numberTxt.getText()),
                    Integer.parseInt(ageTxt.getText()),
                    selectedSexRadioButton.getText(),
                    professionTxt.getText(),
                    Integer.parseInt(educationTxt.getText()));

            System.out.println(user.toString());

            //Clear alerts
            numberAlert.setText("");
            ageAlert.setText("");
            professionAlert.setText("");
            educationAlert.setText("");

            new Alert(Alert.AlertType.INFORMATION, "Valid").show();
            return true;
        } else {
            //set alerts text
            if (numberField.isEmpty()) numberAlert.setText("Uncompleted number field!");
            else numberAlert.setText("");

            if (ageField.isEmpty()) ageAlert.setText("Uncompleted age field!");
            else ageAlert.setText("");

            if (professionField.isEmpty()) professionAlert.setText("Uncompleted profession field!");
            else professionAlert.setText("");

            if (educationField.isEmpty()) educationAlert.setText("Uncompleted education field!");
            else educationAlert.setText("");
        }

        return true;
    }

}