package pl.swpws.model;

import java.util.List;

public class QuestionFirstTask {
    private int userId;
    private int questionNumber;
    private List<ApplianceAttribute> options;
    private int selectedAnswer;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public List<ApplianceAttribute> getOptions() {
        return options;
    }

    public void setOptions(List<ApplianceAttribute> options) {
        this.options = options;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
