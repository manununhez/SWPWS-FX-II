package pl.swpws.controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

public class UserForm implements EventHandler<KeyEvent> {

    public static final int PARAM_TEXT_SIZE = 20;
    public static final Font ATTRI_FONT_PROP = Font.font("Tahoma", FontWeight.NORMAL, PARAM_TEXT_SIZE);
    public static final String NUMER_LABEL = "Numer:";
    public static final String WIEK_LABEL = "Wiek:";
    public static final String ZAWOD_LABEL = "Zawod:";
    public static final String GENDER_LABEL = "Płeć:";
    public static final String EDUKACJI_LABEL = "Lata formalnej edukacji:";
    public static final String SEX_FEMALE_LABEL = "Kobieta";
    public static final String SEX_MALE_LABEL = "Mężczyzna";
    public static final String MAIN_TITLE = "Twoje dane";
    public static final String REGEX_THREE_DIGITS = "\\d{0,3}";
    public static final String REGEX_TWO_DIGITS = "\\d{0,2}";
    private final Stage mStage;
    private final BorderPane mParent;
    private TextField numberTxt;
    private TextField ageTxt;
    private TextField professionTxt;
    private TextField educationTxt;
    private Label numberAlert;
    private Label ageAlert;
    private Label professionAlert;
    private Label educationAlert;
    private ToggleGroup sexToggleGroup;

    public UserForm(Stage stage, BorderPane parent) {

        mStage = stage;
        mParent = parent;
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
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);


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
        numberTxt.setPromptText("number: E.g., 345");
        numberTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_THREE_DIGITS)) {//until three digit numbers only
                numberTxt.setText(oldValue);
            } else {
                numberTxt.setText(newValue);
            }
        });
        grid.add(numberTxt, 1, 1);

        //#######
        grid.add(getLabelForm(WIEK_LABEL), 0, 2);

        ageTxt = new TextField();
        ageTxt.setPromptText("age: E.g., 18");
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
        grid.add(getLabelForm(ZAWOD_LABEL), 0, 3);

        professionTxt = new TextField();
        professionTxt.setPromptText("profession: E.g., architect");
        grid.add(professionTxt, 1, 3);

        professionAlert = getLabelAlert();
        grid.add(professionAlert, 2, 3);

        //#######
        RadioButton rbFemale = new RadioButton(SEX_FEMALE_LABEL);
        RadioButton rbMale = new RadioButton(SEX_MALE_LABEL);

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
        grid.add(getLabelForm(EDUKACJI_LABEL), 0, 5);

        educationTxt = new TextField();
        educationTxt.setPromptText("education: E.g., 25");
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
        vBox.getChildren().add(scenetitle);
        vBox.getChildren().add(grid);
        vBox.setPadding(new Insets(80, 125, 80, 125));

        //Set keyboard listener
        vBox.setOnKeyPressed(this);
        vBox.setOnKeyReleased(this);


        return vBox;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_RELEASED &&
                (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER)) {

            if (formValidate())
                goToNextPage();

        }

//        if (event.getCode() == KeyCode.ESCAPE) {
//            mStage.close();
//        }

    }

    private void goToNextPage() {
        mParent.setCenter(TaskPage.getScenes().get(SceneName.FIRST_INSTR));
        mStage.setTitle(InstructionTasks.FirstInstruction.MAIN_TITLE);
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

        return false;
    }

}
