package pl.swpws.model;

public class Attribute {
    public int id;
    public String attributeId;
    public int[] boldSelector;
    public String attributeName;
    public String[] values;

    public Attribute(int id, String attributeId, int[] boldSelector, String attributeName, String[] values) {
        this.id = id;
        this.attributeId = attributeId;
        this.boldSelector = boldSelector;
        this.attributeName = attributeName;
        this.values = values;
    }
}
