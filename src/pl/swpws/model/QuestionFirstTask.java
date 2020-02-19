package pl.swpws.model;

import java.util.List;

public class QuestionFirstTask {
    private int userId;

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    private int mQuestionID;
    private int questionNumber;
    private int selectedAnswer;

    public QuestionFirstTask(int userId, int questionID, int questionNumber, int selectedAnswer) {
        this.userId = userId;
        mQuestionID = questionID;
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
