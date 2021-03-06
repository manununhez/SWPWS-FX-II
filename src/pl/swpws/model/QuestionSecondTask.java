package pl.swpws.model;

public class QuestionSecondTask {
    private int userId;
    private String attributeCode;
    private int rating;

    public QuestionSecondTask(int userId, String attributeCode, int rating) {
        this.userId = userId;
        this.attributeCode = attributeCode;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
