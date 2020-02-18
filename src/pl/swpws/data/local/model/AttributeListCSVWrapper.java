package pl.swpws.data.local.model;

import pl.swpws.model.Attribute;

import java.util.ArrayList;
import java.util.List;

public class AttributeListCSVWrapper {
//    ID,Attribute_ID,P1,P2,P3,właściwość,Pralka 1,Pralkaalka 2,Pralka 3

    private static final int LIST_ID_IDX = 0;
    private static final int ATTR_ID_IDX = 1;
    private static final int P1_IDX = 2;
    private static final int P2_IDX = 3;
    private static final int P3_IDX = 4;
    private static final int ATTR_DESCRIPTION_IDX = 5;
    private static final int PRALKA1_VALUE_IDX = 6;
    private static final int PRALKA2_VALUE_IDX = 7;
    private static final int PRALKA3_VALUE_IDX = 8;
    private List stringAttributes;

    public AttributeListCSVWrapper(List stringAttributes) {
        this.stringAttributes = stringAttributes;

    }

    public List<Attribute> getListFromCSV() {
        List<Attribute> attributesList = new ArrayList<>();
        //i==0 is the first row with titles -- Ignored
        for (int i = 1; i < stringAttributes.size(); i++) {
            String[] token = (String[]) stringAttributes.get(i);
            attributesList.add(new Attribute(Integer.parseInt(token[LIST_ID_IDX]),
                    token[ATTR_ID_IDX],
                    new int[]{Integer.parseInt(token[P1_IDX]), Integer.parseInt(token[P2_IDX]), Integer.parseInt(token[P3_IDX])},
                    token[ATTR_DESCRIPTION_IDX],
                    new String[]{token[PRALKA1_VALUE_IDX], token[PRALKA2_VALUE_IDX], token[PRALKA3_VALUE_IDX]})
            );
        }
        return attributesList;
    }
}
