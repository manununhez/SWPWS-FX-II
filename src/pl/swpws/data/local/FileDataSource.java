package pl.swpws.data.local;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import pl.swpws.Main;
import pl.swpws.model.Attribute;
import pl.swpws.util.CSVReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class FileDataSource {
    private static FileDataSource INSTANCE;
    private static final String FILE_PATH = "filePath";
    public static final String RESULTS_FILE_CSV_NAME = "Results.csv";
    public static final String FILE_EXTENSION_FILTER = "*";
    public static final String FILE_EXTENSION_TITLE = "CSV files (*.csv)";
    public static final String OPEN_FILE = "Open file...";
    public static final String SAVE_FILE = "Save file...";
    public static final String CSV_FILE_EXTENSION = ".csv";
    public static final String ERROR_WARNING = "Error";
    public static final String SAVE_ERROR_WARNING = "Could not save data";
    public static final String SAVE_ERROR_WARNING_PATH = "Could not save data to file:\n";
    public static final String LOAD_ERROR_WARNING = "Could not load data";
    public static final String LOAD_ERROR_WARNING_PATH = "Could not load data from file:\n";

    private FileDataSource() {
    }

    public static synchronized FileDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (FileDataSource.class) {
                INSTANCE = new FileDataSource();
            }
        }
        return INSTANCE;
    }

    public List<Attribute> loadAttributeListsFromCSV() {
        List<Attribute> resultAttributeList = new ArrayList<>();

        //Try to load last opened person file
        //File file = getFilePath();

        //if (file == null || file.getName().equals(RESULTS_FILE_CSV_NAME)) //if file not found, the user should choose another file
        File file = chooserFile(OPEN_FILE);

        if (file != null) {
            if (file.getPath().endsWith(CSV_FILE_EXTENSION)) {
                BufferedReader br;
                try {
                    //Create the file reader

                    br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                            StandardCharsets.UTF_8));
                    CSVReader csvReader = new CSVReader(br);

                    AttributeListCSVWrapper wordListCSVWrapper = new AttributeListCSVWrapper(csvReader.readAll());

                    resultAttributeList = wordListCSVWrapper.getListFromCSV();

                    csvReader.close();

                    // Save the file path to the registry.
                    setFilePath(file);

                    return resultAttributeList;


                } catch (Exception e) { // catches ANY exception
                    showAlert(LOAD_ERROR_WARNING, LOAD_ERROR_WARNING_PATH, file.getPath());
                }
            }
        }
        return resultAttributeList;
    }

    private void showAlert(String headerText, String contentText, String filePath) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(FileDataSource.ERROR_WARNING);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText.concat(filePath));

        alert.showAndWait();
    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return File
     */
    private File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get(FILE_PATH, null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    private void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put(FILE_PATH, file.getPath());

            // TODO Update the stage title.
//            mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE + " - " + file.getName());
        } else {
            prefs.remove(FILE_PATH);

            // TODO Update the stage title.
//            mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE);
        }
    }


//    public void saveResults(boolean chooseSource, ObservableList<Participant> participants) {
//        if (!chooseSource)
//            saveResultsToFileCSV(new File(RESULTS_FILE_CSV_NAME), participants);
//        else {
//            File chosenFile = chooserFile(SAVE_FILE);
//            if (chosenFile != null) {
//                if (chosenFile.getPath().endsWith(CSV_FILE_EXTENSION)) {
//                    saveResultsToFileCSV(chosenFile, participants);
//                }
//            }
//        }
//    }


    public File chooserFile(String title) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilterCSV = new FileChooser.ExtensionFilter(
                FILE_EXTENSION_TITLE, FILE_EXTENSION_FILTER.concat(CSV_FILE_EXTENSION));
        fileChooser.getExtensionFilters().add(extFilterCSV);
        fileChooser.setTitle(title);

        if (title.equals(OPEN_FILE))
            return fileChooser.showOpenDialog(null);
        else
            return fileChooser.showSaveDialog(null);

    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param participants
     */
//    public void saveResultsToFileCSV(File file, ObservableList<Participant> participants) {
//        CSVWriter csvWriter;
//        try {
//            csvWriter = new CSVWriter(file, null);
//            csvWriter.writeHeader(new String[]{"Participant#", "Sex", "Years of education",
//                    "Date of the experiment", "Category", "Lists"});
//
//            for (Participant participant : participants) {
//                List<String> listOfKeys = new ArrayList<>();
//
//                for (WordList wordList : participant.wordLists)
//                    listOfKeys.add(wordList.key);
//
//                csvWriter.writeData(new String[]{String.valueOf(participant.participantNumber),
//                        participant.sex, String.valueOf(participant.yearsOfEducation), participant.getTimestampFormatted(),
//                        participant.category, String.join(",", listOfKeys)});
//            }
//
//            csvWriter.close();
//            // Save the file path to the registry.
//            setFilePath(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
//        }
//    }

}

