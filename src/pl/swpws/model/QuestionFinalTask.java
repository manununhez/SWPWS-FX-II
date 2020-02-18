package pl.swpws.model;

public class QuestionFinalTask {
    private int userId;
    private String attributeCode;
    private String selectedValue;

    public QuestionFinalTask(int userId, String attributeCode, String selectedValue) {
        this.userId = userId;
        this.attributeCode = attributeCode;
        this.selectedValue = selectedValue;
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

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
}
