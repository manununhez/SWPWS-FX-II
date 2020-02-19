package pl.swpws.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

public class UserForm extends RootPage {

    public static final String MAIN_TITLE = "Twoje dane";
    public static final String SEX_FEMALE_LABEL = "Kobieta";
    public static final String SEX_MALE_LABEL = "Mężczyzna";
    private static final String NUMBER_LABEL = "Numer:";
    private static final String AGE_LABEL = "Wiek:";
    private static final String PROFESSION_LABEL = "Zawód:";
    private static final String GENDER_LABEL = "Płeć:";
    private static final String EDUCATION_LABEL = "Lata formalnej edukacji:";
    private static final String FONT_TYPE = "Tahoma";
    private static final String NUMBER_HINT = "number: E.g., 345";
    private static final String AGE_HINT = "age: E.g., 18";
    private static final String PROFESSION_HINT = "profession: E.g., architect";
    private static final String EDUCATION_HINT = "education: E.g., 25";
    private static final String EMPTY_TEXT = "";
    private static final String NUMBER_ALERT_MESSAGE = "Uncompleted number field!";
    private static final String AGE_ALERT_MESSAGE = "Uncompleted age field!";
    private static final String PROFESSION_ALERT_MESSAGE = "Uncompleted profession field!";
    private static final String EDUCATION_ALERT_MESSAGE = "Uncompleted education field!";
    private static final String REGEX_THREE_DIGITS = "\\d{0,3}";
    private static final String REGEX_TWO_DIGITS = "\\d{0,2}";
    private static final double PARAM_TEXT_SIZE = 20.0;
    private static final double MAIN_PAGE_INSTRUCTION_TEXT_SIZE = 40.0;


    private ToggleGroup sexToggleGroup;
    private TextField numberTxt;
    private TextField ageTxt;
    private TextField professionTxt;
    private TextField educationTxt;
    private Label numberAlert;
    private Label ageAlert;
    private Label professionAlert;
    private Label educationAlert;

    public UserForm(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
        super(stage, parent, sceneName, repository);
    }

    @Override
    public Node getNodeScene() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);

        //#######
        Text sceneTitle = new Text(MAIN_TITLE);
        sceneTitle.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, MAIN_PAGE_INSTRUCTION_TEXT_SIZE));
        sceneTitle.setTextAlignment(TextAlignment.CENTER);

        //#######
        numberAlert = getLabelAlert();
        grid.add(numberAlert, 2, 1);

        grid.add(getLabelForm(NUMBER_LABEL), 0, 1);

        numberTxt = new TextField();
        numberTxt.setPromptText(NUMBER_HINT);
        numberTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_THREE_DIGITS)) {//until three digit numbers only
                numberTxt.setText(oldValue);
            } else {
                numberTxt.setText(newValue);
            }
        });
        grid.add(numberTxt, 1, 1);

        //#######
        grid.add(getLabelForm(AGE_LABEL), 0, 2);

        ageTxt = new TextField();
        ageTxt.setPromptText(AGE_HINT);
        ageTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_THREE_DIGITS)) { //until three digit numbers only
                ageTxt.setText(oldValue);
            } else {
                ageTxt.setText(newValue);
            }
        });
        grid.add(ageTxt, 1, 2);

        ageAlert = getLabelAlert();
        grid.add(ageAlert, 2, 2);

        //#######
        grid.add(getLabelForm(PROFESSION_LABEL), 0, 3);

        professionTxt = new TextField();
        professionTxt.setPromptText(PROFESSION_HINT);
        grid.add(professionTxt, 1, 3);

        professionAlert = getLabelAlert();
        grid.add(professionAlert, 2, 3);

        //#######
        RadioButton rbFemale = new RadioButton(SEX_FEMALE_LABEL);
        RadioButton rbMale = new RadioButton(SEX_MALE_LABEL);
        // The radios should be not focusable (avoid selection by keyboard)
        rbFemale.setFocusTraversable(false);
        rbMale.setFocusTraversable(false);

        sexToggleGroup = new ToggleGroup();
        rbFemale.setToggleGroup(sexToggleGroup);
        rbMale.setToggleGroup(sexToggleGroup);
        rbFemale.setSelected(true);

        HBox hbSex = new HBox();
        hbSex.getChildren().add(rbFemale);
        hbSex.getChildren().add(rbMale);
        hbSex.setSpacing(10);

        grid.add(getLabelForm(GENDER_LABEL), 0, 4);
        grid.add(hbSex, 1, 4);

        //#######
        grid.add(getLabelForm(EDUCATION_LABEL), 0, 5);

        educationTxt = new TextField();
        educationTxt.setPromptText(EDUCATION_HINT);
        educationTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_TWO_DIGITS)) {//until three digit numbers only
                educationTxt.setText(oldValue);
            } else {
                educationTxt.setText(newValue);
            }
        });
        grid.add(educationTxt, 1, 5);

        educationAlert = getLabelAlert();
        grid.add(educationAlert, 2, 5);

        //#######

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(40, 125, 40, 125));

        vBox.getChildren().add(sceneTitle);
        vBox.getChildren().add(grid);

        //Set keyboard listener
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);

        return vBox;
    }


    private Label getLabelAlert() {
        Label label = new Label();
        label.setTextFill(Color.RED);

        return label;
    }

    private Label getLabelForm(String name) {
        Label label = new Label(name);
        label.setFont(Font.font(FONT_TYPE, FontWeight.NORMAL, PARAM_TEXT_SIZE));
        return label;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_RELEASED &&
                (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER)) {

            if (isFormValid()) {
                //save value
                saveUser();
                goToNextPage();
            }

        }
    }

    private void saveUser() {
        RadioButton selectedSexRadioButton = (RadioButton) sexToggleGroup.getSelectedToggle();

        User user = new User(Integer.parseInt(numberTxt.getText()),
                Integer.parseInt(ageTxt.getText()),
                selectedSexRadioButton.getText(),
                professionTxt.getText(),
                Integer.parseInt(educationTxt.getText()));


        //Clear alerts
        numberAlert.setText(EMPTY_TEXT);
        ageAlert.setText(EMPTY_TEXT);
        professionAlert.setText(EMPTY_TEXT);
        educationAlert.setText(EMPTY_TEXT);

        //TODO debug only
        System.out.println("User Form");
        System.out.println(user.toString());


        mRepository.saveUser(user);
    }


    public boolean isFormValid() {

        String numberField = numberTxt.getText().trim();
        String ageField = ageTxt.getText().trim();
        String professionField = professionTxt.getText().trim();
        String educationField = educationTxt.getText().trim();

        if (numberField.isEmpty() || ageField.isEmpty() ||
                professionField.isEmpty() || educationField.isEmpty()) {

            //set alerts text
            if (numberField.isEmpty()) numberAlert.setText(NUMBER_ALERT_MESSAGE);
            else numberAlert.setText(EMPTY_TEXT);

            if (ageField.isEmpty()) ageAlert.setText(AGE_ALERT_MESSAGE);
            else ageAlert.setText(EMPTY_TEXT);

            if (professionField.isEmpty()) professionAlert.setText(PROFESSION_ALERT_MESSAGE);
            else professionAlert.setText(EMPTY_TEXT);

            if (educationField.isEmpty()) educationAlert.setText(EDUCATION_ALERT_MESSAGE);
            else educationAlert.setText(EMPTY_TEXT);

            return false;
        }

        return true;
    }

}
