package pl.swpws.model;

import java.util.List;

public class QuestionFirstTask {
    private int userId;
    private int questionNumber;
    private int selectedAnswer;

    public QuestionFirstTask(int userId, int questionNumber, int selectedAnswer) {
        this.userId = userId;
        this.questionNumber = questionNumber;
        this.selectedAnswer = selectedAnswer;
    }

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

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
