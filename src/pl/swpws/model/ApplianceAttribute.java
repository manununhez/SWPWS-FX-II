package pl.swpws.model;

public class ApplianceAttribute {
    public static final int APPLIANCE_ATTRIBUTES_COUNT = 6;

    public enum EnergyClass {
        A("A"),
        APLUS("A+"),
        APLUS2("A++"),
        APLUS3("A+++");

        public final String label;

        EnergyClass(String label) {
            this.label = label;
        }
    }

    public enum AttributeImportanceLevel {
        SPIN_SPEED(6),
        DRUM_CAPACITY(5),
        ENERGY_CLASS(4),
        NOISE_LEVEL(3),
        WATER_CONSUMPTION(2),
        FAST_PROGRAM(1);

        public final int value;

        AttributeImportanceLevel(int value) {

            this.value = value;
        }

        public static int getAttributeImportanceLevel(AttributesID attributesID) {
            for (AttributeImportanceLevel level : AttributeImportanceLevel.values())
                if (level.name().equals(attributesID.name()))
                    return level.value;

            return 0;
        }
    }

    public enum AttributesName {
        SPIN_SPEED("max prędkość wirowania (obr/min)"),
        DRUM_CAPACITY("pojemność bębna (kg)"),
        ENERGY_CLASS("klasa energetyczna"),
        NOISE_LEVEL("poziom hałasu (db)"),
        WATER_CONSUMPTION("zużycie wody (l)"),
        FAST_PROGRAM("program szybki");

        public final String label;

        AttributesName(String s) {
            this.label = s;
        }

        //According to specs: Fast program and energy class do not have unit of measurement
        public static String getAttributeName(AttributesID attributesID) {
            for (AttributesName unit : AttributesName.values())
                if (unit.name().equals(attributesID.name()))
                    return unit.label;

            return "";
        }
    }

    public enum AttributesID {
        SPIN_SPEED("A1"),
        DRUM_CAPACITY("A2"),
        ENERGY_CLASS("A3"),
        NOISE_LEVEL("A4"),
        WATER_CONSUMPTION("A5"),
        FAST_PROGRAM("A6");

        public final String label;

        AttributesID(String s) {
            this.label = s;
        }

        //According to specs: Fast program and energy class do not have unit of measurement
        public static String getAttributeID(int value) {
            String attributeIndex = AttributeIndex.getAttributeIndex(value);
            for (AttributesID unit : AttributesID.values())
                if (unit.name().equals(attributeIndex))
                    return unit.label;

            return "";
        }
    }

    public enum AttributesMeasurementUnit {
        SPIN_SPEED(" obr/min"),
        DRUM_CAPACITY(" kg"),
        NOISE_LEVEL("db"),
        WATER_CONSUMPTION("l");

        public final String label;

        AttributesMeasurementUnit(String s) {
            this.label = s;
        }

        //According to specs: Fast program and energy class do not have unit of measurement
        public static String getAttributeMeasurementUnit(AttributesID attributesID) {
            for (AttributesMeasurementUnit unit : AttributesMeasurementUnit.values())
                if (unit.name().equals(attributesID.name()))
                    return unit.label;

            return "";
        }
    }

    public enum FastProgram {
        YES("jest"),
        NO("brak");

        public final String label;

        FastProgram(String label) {

            this.label = label;
        }
    }


    public enum AttributeIndex {
        SPIN_SPEED(0),
        DRUM_CAPACITY(1),
        ENERGY_CLASS(2),
        NOISE_LEVEL(3),
        WATER_CONSUMPTION(4),
        FAST_PROGRAM(5);

        public final int value;

        AttributeIndex(int value) {

            this.value = value;
        }

        public static int getAttributeIndex(AttributesID attributesID) {
            for (AttributeIndex level : AttributeIndex.values())
                if (level.name().equals(attributesID.name()))
                    return level.value;

            return 0;
        }

        public static String getAttributeIndex(int value) {
            for (AttributeIndex level : AttributeIndex.values())
                if (level.value == value)
                    return level.name();

            return "";
        }
    }

    //private int importanceLevel;// (1 to 6 plus) - Poziom ważności
    private int maxSpinSpeed;
    private int drumCapacity;
    private String energyClass;
    private int noiseLevel;
    private int waterConsumption;
    private boolean fastProgram;// (program szybki - Y/N, Jest/Brak)


    public ApplianceAttribute(int maxSpinSpeed, int drumCapacity,
                              EnergyClass energyClass, int noiseLevel, int waterConsumption, boolean fastProgram) {
        this.maxSpinSpeed = maxSpinSpeed;
        this.drumCapacity = drumCapacity;
        this.energyClass = energyClass.label;
        this.noiseLevel = noiseLevel;
        this.waterConsumption = waterConsumption;
        this.fastProgram = fastProgram;
    }


    public int getMaxSpinSpeed() {
        return maxSpinSpeed;
    }

    public void setMaxSpinSpeed(int maxSpinSpeed) {
        this.maxSpinSpeed = maxSpinSpeed;
    }

    public int getDrumCapacity() {
        return drumCapacity;
    }

    public void setDrumCapacity(int drumCapacity) {
        this.drumCapacity = drumCapacity;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(EnergyClass energyClass) {
        this.energyClass = energyClass.label;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public int getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public boolean isFastProgram() {
        return fastProgram;
    }

    public String isFastProgramTranslated() {
        return fastProgram ? FastProgram.YES.label : FastProgram.NO.label;
    }

    public void setFastProgram(boolean fastProgram) {
        this.fastProgram = fastProgram;
    }

    @Override
    public String toString() {
        return "ApplianceAttribute{" +
                "maxSpinSpeed=" + maxSpinSpeed +
                ", drumCapacity=" + drumCapacity +
                ", energyClass='" + energyClass + '\'' +
                ", noiseLevel=" + noiseLevel +
                ", waterConsumption=" + waterConsumption +
                ", fastProgram=" + fastProgram +
                '}';
    }
}
