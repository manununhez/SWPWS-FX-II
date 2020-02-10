package pl.swpws.model;

import java.util.HashMap;

public class ApplianceAttribute {
    enum EnergyClass {
        A, APLUS, APLUS2, APLUS3
    }

    private int importanceLevel;// (1 to 6 plus) - Poziom ważności
    private int maxSpinSpeed;// (max prędkość wirowania(obr/min))
    private int drumCapacity;// (pojemność bębna (kg))
    private EnergyClass energyClass;// (more? klasa energetyczna)
    private int noiseLevel;// (poziom hałasu (db))
    private int waterConsumption;// (zużycie wody (l))
    private boolean fastProgram;// (program szybki - Y/N, Jest/Brak)

    private HashMap<AttributesName, Integer> attributesImportanceLevel;

    public ApplianceAttribute() {
        attributesImportanceLevel = new HashMap<>();
        //TODO Mover a otro modelo?
        attributesImportanceLevel.put(AttributesName.SPIN_SPEED, 6);
        attributesImportanceLevel.put(AttributesName.DRUM_CAPACITY, 5);
        attributesImportanceLevel.put(AttributesName.ENERGY_CLASS, 4);
        attributesImportanceLevel.put(AttributesName.NOISE_LEVEL, 3);
        attributesImportanceLevel.put(AttributesName.WATER_CONSUMPTION, 2);
        attributesImportanceLevel.put(AttributesName.FAST_PROGRAM, 1);
    }

    enum AttributesName{
        SPIN_SPEED, DRUM_CAPACITY, ENERGY_CLASS, NOISE_LEVEL, WATER_CONSUMPTION,
        FAST_PROGRAM
    }
}
