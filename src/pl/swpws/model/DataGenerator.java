package pl.swpws.model;

import pl.swpws.model.ApplianceAttribute.EnergyClass;

import java.util.*;

public class DataGenerator {

    public static final int ATTRIBUTE_NUMBER = 6;
    private List<Attribute> attributeList;
    private List<Attribute> exampleList;

    public DataGenerator() {
        getExampleListOfAttributes();

        getListOfAttributes(); //TODO here the file should be read and parsed
    }

    public static List<List<String>> getApplianceAttributesNameMap() {
        List<List<String>> listLis = new ArrayList<>();
        listLis.add( Arrays.asList("800", "1000", "1200", "1400", "1600")); //AttributesID.SPIN_SPEED
        listLis.add(Arrays.asList("4", "5", "6", "7", "8", "9", "10"));//AttributesID.DRUM_CAPACITY
        listLis.add(Arrays.asList(EnergyClass.A.label,
                EnergyClass.APLUS.label,
                EnergyClass.APLUS2.label,
                EnergyClass.APLUS3.label)); //AttributesID.ENERGY_CLASS
        listLis.add(Arrays.asList("70", "65", "60", "55", "50", "45", "40"));//AttributesID.NOISE_LEVEL,
        listLis.add(Arrays.asList("70", "60", "50", "40", "30"));//AttributesID.WATER_CONSUMPTION,
        listLis.add(Arrays.asList(ApplianceAttribute.FastProgram.YES.label, ApplianceAttribute.FastProgram.NO.label)); //AttributesID.FAST_PROGRAM,
        return listLis;
    }

    private void getListOfAttributes() {
        attributeList = new ArrayList<>();
        attributeList.add(new Attribute(1, "A1", new int[]{1, 0, 0}, "max prędkość wirowania", new String[]{"1200", "800", "1000"}));
        attributeList.add(new Attribute(1, "A2", new int[]{0, 0, 0}, "pojemnosc bębna", new String[]{"4", "2", "4"}));
        attributeList.add(new Attribute(1, "A3", new int[]{1, 0, 0}, "klasa energetyczna", new String[]{"A+", "A+", "A"}));
        attributeList.add(new Attribute(1, "A4", new int[]{0, 1, 0}, "poziom hałasu", new String[]{"70", "60", "70"}));
        attributeList.add(new Attribute(1, "A5", new int[]{0, 0, 0}, "zużycie wody", new String[]{"65", "65", "65"}));
        attributeList.add(new Attribute(1, "A6", new int[]{0, 0, 1}, "program szybki", new String[]{"brak", "brak", "jest"}));

        attributeList.add(new Attribute(2, "A1", new int[]{0, 0, 1}, "poziom hałasu", new String[]{"70", "65", "70"}));
        attributeList.add(new Attribute(2, "A2", new int[]{1, 1, 1}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        attributeList.add(new Attribute(2, "A3", new int[]{0, 0, 0}, "program szybki", new String[]{"brak", "brak", "jest"}));
        attributeList.add(new Attribute(2, "A4", new int[]{0, 0, 0}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        attributeList.add(new Attribute(2, "A5", new int[]{0, 0, 1}, "zużycie wody", new String[]{"65", "65", "65"}));
        attributeList.add(new Attribute(2, "A6", new int[]{0, 1, 1}, "klasa energetyczna", new String[]{"A+", "A", "A"}));

        attributeList.add(new Attribute(3, "A1", new int[]{0, 0, 0}, "klasa energetyczna", new String[]{"A+", "A++", "A+++"}));
        attributeList.add(new Attribute(3, "A2", new int[]{0, 1, 0}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        attributeList.add(new Attribute(3, "A3", new int[]{0, 1, 0}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        attributeList.add(new Attribute(3, "A4", new int[]{1, 1, 0}, "poziom hałasu", new String[]{"70", "65", "70"}));
        attributeList.add(new Attribute(3, "A5", new int[]{0, 0, 0}, "zużycie wody", new String[]{"65", "65", "65"}));
        attributeList.add(new Attribute(3, "A6", new int[]{0, 0, 0}, "program szybki", new String[]{"brak", "brak", "brak"}));

        attributeList.add(new Attribute(4, "A1", new int[]{0, 0, 1}, "zużycie wody", new String[]{"65", "65", "65"}));
        attributeList.add(new Attribute(4, "A2", new int[]{0, 0, 1}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        attributeList.add(new Attribute(4, "A3", new int[]{0, 0, 1}, "poziom hałasu", new String[]{"70", "65", "70"}));
        attributeList.add(new Attribute(4, "A4", new int[]{1, 1, 1}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        attributeList.add(new Attribute(4, "A5", new int[]{0, 1, 0}, "program szybki", new String[]{"brak", "brak", "jest"}));
        attributeList.add(new Attribute(4, "A6", new int[]{0, 0, 0}, "klasa energetyczna", new String[]{"A+", "A", "A"}));

    }

    public List<Attribute> getListOfAttributesPerIteration(int iteration) {
        int firstIndexList = (iteration * ATTRIBUTE_NUMBER) - ATTRIBUTE_NUMBER;
        int lastIndexList = iteration * ATTRIBUTE_NUMBER;

        List<Attribute> attributes = new ArrayList<>();

        for (int i = firstIndexList; i < lastIndexList; i++) {
            attributes.add(attributeList.get(i));
        }

        return attributes;
    }

    public List<Attribute> getExampleListOfAttributes() {
        exampleList = new ArrayList<>();
        exampleList.add(new Attribute(1, "A1", new int[]{1, 0, 0}, "max prędkość wirowania", new String[]{"1200", "800", "1000"}));
        exampleList.add(new Attribute(1, "A2", new int[]{0, 0, 0}, "pojemnosc bębna", new String[]{"4", "2", "4"}));
        exampleList.add(new Attribute(1, "A3", new int[]{1, 0, 0}, "klasa energetyczna", new String[]{"A+", "A+", "A"}));
        exampleList.add(new Attribute(1, "A4", new int[]{0, 1, 0}, "poziom hałasu", new String[]{"70", "60", "70"}));
        exampleList.add(new Attribute(1, "A5", new int[]{0, 0, 0}, "zużycie wody", new String[]{"65", "65", "65"}));
        exampleList.add(new Attribute(1, "A6", new int[]{0, 0, 1}, "program szybki", new String[]{"brak", "brak", "jest"}));

        exampleList.add(new Attribute(2, "A1", new int[]{0, 0, 1}, "poziom hałasu", new String[]{"70", "65", "70"}));
        exampleList.add(new Attribute(2, "A2", new int[]{1, 1, 1}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        exampleList.add(new Attribute(2, "A3", new int[]{0, 0, 0}, "program szybki", new String[]{"brak", "brak", "jest"}));
        exampleList.add(new Attribute(2, "A4", new int[]{0, 0, 0}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        exampleList.add(new Attribute(2, "A5", new int[]{0, 0, 1}, "zużycie wody", new String[]{"65", "65", "65"}));
        exampleList.add(new Attribute(2, "A6", new int[]{0, 1, 1}, "klasa energetyczna", new String[]{"A+", "A", "A"}));

        exampleList.add(new Attribute(3, "A1", new int[]{0, 0, 0}, "klasa energetyczna", new String[]{"A+", "A++", "A+++"}));
        exampleList.add(new Attribute(3, "A2", new int[]{0, 1, 0}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        exampleList.add(new Attribute(3, "A3", new int[]{0, 1, 0}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        exampleList.add(new Attribute(3, "A4", new int[]{1, 1, 0}, "poziom hałasu", new String[]{"70", "65", "70"}));
        exampleList.add(new Attribute(3, "A5", new int[]{0, 0, 0}, "zużycie wody", new String[]{"65", "65", "65"}));
        exampleList.add(new Attribute(3, "A6", new int[]{0, 0, 0}, "program szybki", new String[]{"brak", "brak", "brak"}));

        exampleList.add(new Attribute(4, "A1", new int[]{0, 0, 1}, "zużycie wody", new String[]{"65", "65", "65"}));
        exampleList.add(new Attribute(4, "A2", new int[]{0, 0, 1}, "pojemnosc bębna", new String[]{"4", "4", "4"}));
        exampleList.add(new Attribute(4, "A3", new int[]{0, 0, 1}, "poziom hałasu", new String[]{"70", "65", "70"}));
        exampleList.add(new Attribute(4, "A4", new int[]{1, 1, 1}, "max prędkość wirowania", new String[]{"1200", "1000", "1000"}));
        exampleList.add(new Attribute(4, "A5", new int[]{0, 1, 0}, "program szybki", new String[]{"brak", "brak", "jest"}));
        exampleList.add(new Attribute(4, "A6", new int[]{0, 0, 0}, "klasa energetyczna", new String[]{"A+", "A", "A"}));

        return exampleList;
    }

    public List<Attribute> getListOfExampleAttributesPerIteration(int iteration) {
        int firstIndexList = (iteration * ATTRIBUTE_NUMBER) - ATTRIBUTE_NUMBER;
        int lastIndexList = iteration * ATTRIBUTE_NUMBER;

        List<Attribute> attributes = new ArrayList<>();

        for (int i = firstIndexList; i < lastIndexList; i++) {
            attributes.add(exampleList.get(i));
        }

        return attributes;
    }


    public int getFirstTaskCountIteration() {
        return attributeList.size() / ATTRIBUTE_NUMBER;
    }

    public int getFirstTaskExampleCountIteration() {
        return exampleList.size() / ATTRIBUTE_NUMBER;
    }

}
