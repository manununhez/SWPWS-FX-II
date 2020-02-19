package pl.swpws.data.local;

import javafx.scene.control.Alert;
import pl.swpws.Main;
import pl.swpws.data.local.model.AttributeListCSVWrapper;
import pl.swpws.model.*;
import pl.swpws.util.CSVReader;
import pl.swpws.util.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class FileDataSource {
    private static FileDataSource INSTANCE;
    private static final String WARM_UP_EXERCISE_FILE_TO_OPEN = "input_all_warmup.csv";
    private static final String EXERCISE_FILE_TO_OPEN = "input_all_main.csv";
    private static final String RESULT_FILE_CSV_USER = "Users.csv";
    private static final String RESULT_FILE_CSV_FIRST_TASK = "FirstTask.csv";
    private static final String RESULT_FILE_CSV_SECOND_TASK = "SecondTask.csv";
    private static final String RESULT_FILE_CSV_FINAL_TASK = "FinalTask.csv";
    private static final String FILE_PATH = "filePath";
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
        //File directory = new File("./");
//        System.out.println(directory.getAbsolutePath());

        List<Attribute> resultAttributeList = new ArrayList<>();

        File file = new File(EXERCISE_FILE_TO_OPEN);
        System.out.println(file.getAbsolutePath());

        if (file.exists()) {
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
        } else {
            showAlert(LOAD_ERROR_WARNING, LOAD_ERROR_WARNING_PATH, file.getPath());
        }
        return resultAttributeList;
    }

    public List<Attribute> loadAttributeListsWarmUpFromCSV() {
        List<Attribute> resultAttributeList = new ArrayList<>();

        File file = new File(WARM_UP_EXERCISE_FILE_TO_OPEN);

        if (file.exists()) {
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
        } else {
            showAlert(LOAD_ERROR_WARNING, LOAD_ERROR_WARNING_PATH, file.getPath());
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

        } else {
            prefs.remove(FILE_PATH);

        }
    }


    public void saveUser(User user) {
        CSVWriter csvWriter;

        File file = new File(RESULT_FILE_CSV_USER);

        if (!file.exists()) {
            try {
                csvWriter = new CSVWriter(file, null);
                csvWriter.writeHeader(new String[]{"UserID", "Number", "Sex", "Age", "Profession", "YearsEducation"});


                csvWriter.writeData(new String[]{
                        String.valueOf(user.getId()),
                        String.valueOf(user.getNumber()),
                        user.getSex(),
                        String.valueOf(user.getAge()),
                        user.getProfession(),
                        String.valueOf(user.getYearsEducation())
                });


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);
            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        } else {
            try {
                csvWriter = new CSVWriter(file, null, true);

                csvWriter.writeData(new String[]{
                        String.valueOf(user.getId()),
                        String.valueOf(user.getNumber()),
                        user.getSex(),
                        String.valueOf(user.getAge()),
                        user.getProfession(),
                        String.valueOf(user.getYearsEducation())
                });


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);

            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        }
    }

    public void saveFirstTask(QuestionFirstTask questionFirstTask) {
        CSVWriter csvWriter;

        File file = new File(RESULT_FILE_CSV_FIRST_TASK);

        if (!file.exists()) {
            try {
                csvWriter = new CSVWriter(file, null);
                csvWriter.writeHeader(new String[]{"UserID", "QuestionID", "QuestionNumber", "SelectedAnswer"});


                csvWriter.writeData(new String[]{
                        String.valueOf(questionFirstTask.getUserId()),
                        String.valueOf(questionFirstTask.getQuestionID()),
                        String.valueOf(questionFirstTask.getQuestionNumber()),
                        String.valueOf(questionFirstTask.getSelectedAnswer()),
                });


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);
            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        } else {
            try {
                csvWriter = new CSVWriter(file, null, true);

                csvWriter.writeData(new String[]{
                        String.valueOf(questionFirstTask.getUserId()),
                        String.valueOf(questionFirstTask.getQuestionID()),
                        String.valueOf(questionFirstTask.getQuestionNumber()),
                        String.valueOf(questionFirstTask.getSelectedAnswer()),
                });


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);

            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        }

    }


    public void saveSecondTask(List<QuestionSecondTask> secondTaskList) {
        CSVWriter csvWriter;

        File file = new File(RESULT_FILE_CSV_SECOND_TASK);

        if (!file.exists()) {
            try {
                csvWriter = new CSVWriter(file, null);
                csvWriter.writeHeader(new String[]{"UserID", "AttributeCode", "Rating"});

                for (QuestionSecondTask questionSecondTask : secondTaskList) {
                    csvWriter.writeData(new String[]{
                            String.valueOf(questionSecondTask.getUserId()),
                            questionSecondTask.getAttributeCode(),
                            String.valueOf(questionSecondTask.getRating()),
                    });
                }

                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);
            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        } else {
            try {
                csvWriter = new CSVWriter(file, null, true);

                for (QuestionSecondTask questionSecondTask : secondTaskList) {
                    csvWriter.writeData(new String[]{
                            String.valueOf(questionSecondTask.getUserId()),
                            questionSecondTask.getAttributeCode(),
                            String.valueOf(questionSecondTask.getRating()),
                    });
                }


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);

            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        }
    }

    public void saveFinalTask(List<QuestionFinalTask> finalTaskList) {
        CSVWriter csvWriter;

        File file = new File(RESULT_FILE_CSV_FINAL_TASK);

        if (!file.exists()) {
            try {
                csvWriter = new CSVWriter(file, null);
                csvWriter.writeHeader(new String[]{"UserID", "AttributeCode", "SelectedValue"});

                for (QuestionFinalTask questionSecondTask : finalTaskList) {
                    csvWriter.writeData(new String[]{
                            String.valueOf(questionSecondTask.getUserId()),
                            questionSecondTask.getAttributeCode(),
                            questionSecondTask.getSelectedValue(),
                    });
                }

                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);
            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        } else {
            try {
                csvWriter = new CSVWriter(file, null, true);

                for (QuestionFinalTask questionSecondTask : finalTaskList) {
                    csvWriter.writeData(new String[]{
                            String.valueOf(questionSecondTask.getUserId()),
                            questionSecondTask.getAttributeCode(),
                            questionSecondTask.getSelectedValue(),
                    });
                }


                csvWriter.close();
                // Save the file path to the registry.
                setFilePath(file);

            } catch (IOException e) {
                e.printStackTrace();

                showAlert(SAVE_ERROR_WARNING, SAVE_ERROR_WARNING_PATH, file.getPath());
            }
        }
    }


}

