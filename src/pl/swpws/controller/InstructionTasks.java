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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swpws.common.rating.RatingPlus;
import pl.swpws.data.repository.Repository;
import pl.swpws.model.ApplianceAttribute.AttributesName;
import pl.swpws.model.SceneName;
import pl.swpws.model.User;

import static pl.swpws.model.ApplianceAttribute.AttributeImportanceLevel.getAttributeImportanceLevel;
import static pl.swpws.model.ApplianceAttribute.AttributesID.*;

public class InstructionTasks {

    static class FirstInstruction extends RootPage {
        public static final String MAIN_TITLE = "First instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Za chwilę zobaczy Pani zadanie, którego celem będzie wybranie " +
                "najlepszej pralki spośród 3 przedstawionych modeli. Będzie Pani " +
                "proszona o dokonanie wielu takich wyborów.\n\n" +
                "Zależy nam, aby za każdym razem wybrała Pani najlepszą z trzech " +
                "pralek.";

        private static final String MAIN_PAGE_INSTRUCTION_MALE = "Za chwilę zobaczy Pan zadanie, którego celem będzie wybranie " +
                "najlepszej pralki spośród 3 przedstawionych modeli. Będzie Pan " +
                "proszony o dokonanie wielu takich wyborów.\n\n" +
                "Zależy nam, aby za każdym razem wybrał Pan najlepszą z trzech " +
                "pralek.";

        public FirstInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(30.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);
            labelMainTitle.setTextAlignment(TextAlignment.LEFT);

            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }

    }

    static class SecondInstruction extends RootPage {
        public static final String MAIN_TITLE = "Second instruction";

        public static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Zgodnie z badaniami marketingowymi przyznaliśmy plusy właściwościom pralek. Im " +
                "wyższa liczba plusów, tym ważniejsza dla przeciętnego użytkownika jest dana " +
                "właściwość. Prosimy, aby dokonując wyboru produktu kierowała się Pani " +
                "ważnością podanych właściwości.";
        public static final String MAIN_PAGE_INSTRUCTION_MALE = "Zgodnie z badaniami marketingowymi przyznaliśmy plusy właściwościom pralek. Im " +
                "wyższa liczba plusów, tym ważniejsza dla przeciętnego użytkownika jest dana " +
                "właściwość. Prosimy, aby dokonując wyboru produktu kierował się Pan " +
                "ważnością podanych właściwości.";
        private static final String TABLE_TITLE1 = "właściwość";
        private static final String TABLE_TITLE2 = "ważność";
        private static final double MAIN_PAGE_INSTRUCTION_TEXT_SIZE_SMALL = 30.0;


        public SecondInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(MAIN_PAGE_INSTRUCTION_TEXT_SIZE_SMALL));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);


            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getChildren().add(labelMainTitle);
            vBox.getChildren().add(getGridPaneDescription());
            vBox.setPadding(new Insets(20, 125, 20, 125));

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

        private GridPane getGridPaneDescription() {
            Label gridTitle1 = getTableTitleLabel(TABLE_TITLE1);
            Label gridTitle2 = getTableTitleLabel(TABLE_TITLE2);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setAlignment(Pos.TOP_CENTER);

            gridPane.add(gridTitle1, 1, 0);
            gridPane.add(gridTitle2, 2, 0);

            gridPane.add(getParamLabel(AttributesName.SPIN_SPEED.label), 1, 1);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(SPIN_SPEED)), 2, 1);

            gridPane.add(getParamLabel(AttributesName.DRUM_CAPACITY.label), 1, 2);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(DRUM_CAPACITY)), 2, 2);

            gridPane.add(getParamLabel(AttributesName.ENERGY_CLASS.label), 1, 3);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(ENERGY_CLASS)), 2, 3);

            gridPane.add(getParamLabel(AttributesName.NOISE_LEVEL.label), 1, 4);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(NOISE_LEVEL)), 2, 4);

            gridPane.add(getParamLabel(AttributesName.WATER_CONSUMPTION.label), 1, 5);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(WATER_CONSUMPTION)), 2, 5);

            gridPane.add(getParamLabel(AttributesName.FAST_PROGRAM.label), 1, 6);
            gridPane.add(getDisabledRating(getAttributeImportanceLevel(FAST_PROGRAM)), 2, 6);

            return gridPane;
        }

        private Label getTableTitleLabel(String name) {
            Label titleLabel = new Label(name);
            titleLabel.setFont(new Font(25.0));
            titleLabel.setPadding(new Insets(5, 0, 5, 0));
            return titleLabel;
        }

        private Label getParamLabel(String name) {
            Label label = new Label(name);
            label.setFont(new Font(20.0));
            label.setPadding(new Insets(5, 0, 5, 0));
            return label;
        }

        private RatingPlus getDisabledRating(int value) {
            RatingPlus ratingPlus = new RatingPlus(6);
            ratingPlus.setRating(value);
            ratingPlus.setDisable(true);

            ratingPlus.setPadding(new Insets(10, 0, 10, 0));
            return ratingPlus;
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }

    }

    static class ThirdInstruction extends RootPage {
        public static final String MAIN_TITLE = "Third Instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Dla przeciętnego konsumenta najważniejszą właściwością jest maksymalna " +
                "prędkość wirowania, wyrażana w liczbie obrotów na minutę i stąd sześć " +
                "plusów. Pralki, które będzie Pani porównywała, mają maksymalną prędkość " +
                "wirowania od 800 do 1600 obrotów. Im większa prędkość wirowania, tym w " +
                "ocenie konsumentów lepiej.\n\n" +
                "Kolejną ważną właściwością (pięć plusów) jest pojemność bębna pralki w " +
                "kilogramach. Pralki, które będzie Pani miała do porównania, mają pojemność " +
                "od 4 kg do 10 kg. Im większa pojemność bębna, tym zdaniem konsumentów " +
                "lepiej.\n\n" +
                "Następną istotną właściwością (cztery plusy) jest klasa energetyczna " +
                "wyrażona w symbolach od A do A+++, gdzie większa liczba plusów przy " +
                "literze „A” wskazuje na wyższą klasę energetyczną.\n\n" +
                "Kolejna właściwość, oceniana przez konsumentów na trzy plusy, to " +
                "poziom hałasu w decybelach (db). Prezentowane pralki będą miały " +
                "poziom hałasu od 70db do 40db, gdzie mniejsza wartość oznacza " +
                "cichszą pralkę. Cicha praca jest preferowana przez większość " +
                "użytkowników.\n\n" +
                "Następna właściwość, to zużycie wody w litrach (dwa plusy). " +
                "Porównywane pralki będą zużywały od 70l do 30l na cykl prania. " +
                "Konsumenci preferują pralki zużywające mniejszą ilość wody.\n\n" +
                "Ostatnia właściwość (jeden plus) to obecność lub brak programu " +
                "szybkiego prania. Przeciętny konsument chce mieć możliwość " +
                "ustawienia szybkiego prania.";
        private static final String MAIN_PAGE_INSTRUCTION_MALE = "Dla przeciętnego konsumenta najważniejszą właściwością jest maksymalna " +
                "prędkość wirowania, wyrażana w liczbie obrotów na minutę i stąd sześć " +
                "plusów. Pralki, które będzie Pan porównywał, mają maksymalną prędkość " +
                "wirowania od 800 do 1600 obrotów. Im większa prędkość wirowania, tym w " +
                "ocenie konsumentów lepiej.\n\n" +
                "Kolejną ważną właściwością (pięć plusów) jest pojemność bębna pralki w " +
                "kilogramach. Pralki, które będzie Pan miał do porównania, mają pojemność " +
                "od 4 kg do 10 kg. Im większa pojemność bębna, tym zdaniem konsumentów " +
                "lepiej.\n\n" +
                "Następną istotną właściwością (cztery plusy) jest klasa energetyczna " +
                "wyrażona w symbolach od A do A+++, gdzie większa liczba plusów przy " +
                "literze „A” wskazuje na wyższą klasę energetyczną.\n\n" +
                "Kolejna właściwość, oceniana przez konsumentów na trzy plusy, to " +
                "poziom hałasu w decybelach (db). Prezentowane pralki będą miały " +
                "poziom hałasu od 70db do 40db, gdzie mniejsza wartość oznacza " +
                "cichszą pralkę. Cicha praca jest preferowana przez większość " +
                "użytkowników.\n\n" +
                "Następna właściwość, to zużycie wody w litrach (dwa plusy). " +
                "Porównywane pralki będą zużywały od 70l do 30l na cykl prania. " +
                "Konsumenci preferują pralki zużywające mniejszą ilość wody.\n\n" +
                "Ostatnia właściwość (jeden plus) to obecność lub brak programu " +
                "szybkiego prania. Przeciętny konsument chce mieć możliwość " +
                "ustawienia szybkiego prania.";


        public ThirdInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(20.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);

            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }

    }

//    static class FourthInstruction extends RootPage {
//        public static final String MAIN_TITLE = "Fourth Instruction";
//        private static final String MAIN_PAGE_INSTRUCTION_MALE = "Kolejna właściwość, oceniana przez konsumentów na trzy plusy, to " +
//                "poziom hałasu w decybelach (db). Prezentowane pralki będą miały " +
//                "poziom hałasu od 70db do 40db, gdzie mniejsza wartość oznacza " +
//                "cichszą pralkę. Cicha praca jest preferowana przez większość " +
//                "użytkowników.\n\n" +
//                "Następna właściwość, to zużycie wody w litrach (dwa plusy). " +
//                "Porównywane pralki będą zużywały od 70l do 30l na cykl prania. " +
//                "Konsumenci preferują pralki zużywające mniejszą ilość wody.\n\n" +
//                "Ostatnia właściwość (jeden plus) to obecność lub brak programu " +
//                "szybkiego prania. Przeciętny konsument chce mieć możliwość" +
//                "ustawienia szybkiego prania.";
//        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Kolejna właściwość, oceniana przez konsumentów na trzy plusy, to " +
//                "poziom hałasu w decybelach (db). Prezentowane pralki będą miały " +
//                "poziom hałasu od 70db do 40db, gdzie mniejsza wartość oznacza " +
//                "cichszą pralkę. Cicha praca jest preferowana przez większość " +
//                "użytkowników.\n\n" +
//                "Następna właściwość, to zużycie wody w litrach (dwa plusy). " +
//                "Porównywane pralki będą zużywały od 70l do 30l na cykl prania. " +
//                "Konsumenci preferują pralki zużywające mniejszą ilość wody.\n\n" +
//                "Ostatnia właściwość (jeden plus) to obecność lub brak programu " +
//                "szybkiego prania. Przeciętny konsument chce mieć możliwość" +
//                "ustawienia szybkiego prania.";
//
//
//        public FourthInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
//            super(stage, parent, sceneName, repository);
//        }
//
//        @Override
//        public Node getNodeScene() {
//            Label labelMainTitle = new Label(getMainInstruction());
//            labelMainTitle.setFont(new Font(30.0));
//            labelMainTitle.setWrapText(true);
//            labelMainTitle.setAlignment(Pos.TOP_CENTER);
//
//            VBox vBox = new VBox();
//            vBox.setSpacing(50.0);
//            vBox.setAlignment(Pos.TOP_CENTER);
//            vBox.getChildren().add(labelMainTitle);
//            vBox.setPadding(new Insets(40, 125, 40, 125));
//
//            vBox.setFocusTraversable(true);//To detect keyEvents!
//            vBox.setOnKeyPressed(this);
//            vBox.setOnKeyReleased(this);
//
//            return vBox;
//        }
//
//        private String getMainInstruction() {
//            User user = mRepository.getUser();
//            if (user.getSex().equals(UserForm.SEX_FEMALE_LABEL))
//                return MAIN_PAGE_INSTRUCTION_FEMALE;
//            else
//                return MAIN_PAGE_INSTRUCTION_MALE;
//        }
//
//        @Override
//        public void handle(KeyEvent keyEvent) {
//            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
//                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
//                goToNextPage();
//            }
//        }
//
//    }


    static class FifthInstruction extends RootPage {
        public static final String MAIN_TITLE = "Fifth Instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Za chwilę zobaczy Pani cztery\n" +
                "przykładowe zadania dotyczące\n" +
                "wyboru pralek. W każdym\n" +
                "zadaniu proszę wybrać\n" +
                "najlepszą pralkę, kierując się ich\n" +
                "właściwościami.";
        private static final String MAIN_PAGE_INSTRUCTION_MALE = "Za chwilę zobaczy Pan cztery\n" +
                "przykładowe zadania dotyczące\n" +
                "wyboru pralek. W każdym\n" +
                "zadaniu proszę wybrać\n" +
                "najlepszą pralkę, kierując się ich\n" +
                "właściwościami.";


        public FifthInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(50.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setTextAlignment(TextAlignment.CENTER);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);

            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }

    }

    static class SixthInstruction extends RootPage {
        public static final String MAIN_TITLE = "Sixth Instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "To już koniec przykładowych\n" +
                "zadań. Za chwilę przejdzie Pani\n" +
                "do zadań właściwych. W razie\n" +
                "pytań, proszę dać znać osobie\n" +
                "prowadzącej badanie.";
        private static final String MAIN_PAGE_INSTRUCTION_MALE = "To już koniec przykładowych\n" +
                "zadań. Za chwilę przejdzie Pan\n" +
                "do zadań właściwych. W razie\n" +
                "pytań, proszę dać znać osobie\n" +
                "prowadzącej badanie.";


        public SixthInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(50.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setTextAlignment(TextAlignment.CENTER);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);

            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }
    }

    static class SeventhInstruction extends RootPage {
        public static final String MAIN_TITLE = "Seventh Instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "Teraz proszę sobie wyobrazić,\n" +
                "że kupuje Pani pralkę dla siebie.";
        private static final String MAIN_PAGE_INSTRUCTION_MALE = "Teraz proszę sobie wyobrazić,\n" +
                "że kupuje Pan pralkę dla siebie.";


        public SeventhInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(50.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setTextAlignment(TextAlignment.CENTER);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);

            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                    (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER)) {
                goToNextPage();
            }
        }

    }

    static class FinalInstruction extends RootPage {
        public static final String MAIN_TITLE = "Final Instruction";
        private static final String MAIN_PAGE_INSTRUCTION_FEMALE = "To już koniec tego zadania.\n" +
                "Dziękujemy!";
        private static final String MAIN_PAGE_INSTRUCTION_MALE = "To już koniec tego zadania.\n" +
                "Dziękujemy!";

        public FinalInstruction(Stage stage, BorderPane parent, SceneName sceneName, Repository repository) {
            super(stage, parent, sceneName, repository);
        }

        @Override
        public Node getNodeScene() {
            Label labelMainTitle = new Label(getMainInstruction());
            labelMainTitle.setFont(new Font(50.0));
            labelMainTitle.setWrapText(true);
            labelMainTitle.setTextAlignment(TextAlignment.CENTER);
            labelMainTitle.setAlignment(Pos.TOP_CENTER);


            VBox vBox = new VBox();
            vBox.setSpacing(50.0);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().add(labelMainTitle);
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

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                mStage.close();
            }
        }
    }
}
