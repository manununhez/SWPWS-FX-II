package pl.swpws.model;

public class ApplianceAttribute {
    public enum EnergyClass {
        A("A"),
        APLUS("A++"),
        APLUS2("A+++"),
        APLUS3("A+++");

        private final String label;

        EnergyClass(String label) {
            this.label = label;
        }
    }

    public enum AttributesName {
        SPIN_SPEED("max prędkość wirowania(obr/min)"),
        DRUM_CAPACITY("pojemność bębna (kg)"),
        ENERGY_CLASS("klasa energetyczna"),
        NOISE_LEVEL("poziom hałasu (db)"),
        WATER_CONSUMPTION("zużycie wody (l)"),
        FAST_PROGRAM("program szybki");

        public final String label;

        AttributesName(String s) {
            this.label = s;
        }
    }

    public enum FastProgram {
        YES("Jest"),
        NO("Brak");

        public String label;

        FastProgram(String label) {

            this.label = label;
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


    public static int getAttributeImportanceLevel(AttributesName attributesName) {
        switch (attributesName) {
            case SPIN_SPEED:
                return 6;
            case DRUM_CAPACITY:
                return 5;
            case ENERGY_CLASS:
                return 4;
            case NOISE_LEVEL:
                return 3;
            case WATER_CONSUMPTION:
                return 2;
            case FAST_PROGRAM:
                return 1;
            default:
                return 0;
        }
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
