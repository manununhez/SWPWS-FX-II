package pl.swpws.model;

import java.util.List;
import java.util.Map;

public class DataGenerator {
    public static List<ApplianceAttribute> getApplianceAttributeListExample() {
        return List.of(
                new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS2, 60, 65, false),
                new ApplianceAttribute(1000, 8, ApplianceAttribute.EnergyClass.APLUS, 70, 65, false),
                new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS, 60, 45, true));
    }

    public static List<ApplianceAttribute> getApplianceAttributeList() {
        return List.of(
                new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS2, 60, 65, false),
                new ApplianceAttribute(1000, 8, ApplianceAttribute.EnergyClass.APLUS, 70, 65, false),
                new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS, 60, 45, true));
    }

    public static Map<ApplianceAttribute.AttributesName, List<String>> getApplianceAttributesNameMap() {
        return Map.of(
                ApplianceAttribute.AttributesName.SPIN_SPEED, List.of("800", "1000", "1200", "1400", "1600"),
                ApplianceAttribute.AttributesName.DRUM_CAPACITY, List.of("4", "5", "6", "7", "8", "9", "10"),
                ApplianceAttribute.AttributesName.ENERGY_CLASS, List.of(ApplianceAttribute.EnergyClass.A.label, ApplianceAttribute.EnergyClass.APLUS.label, ApplianceAttribute.EnergyClass.APLUS2.label, ApplianceAttribute.EnergyClass.APLUS3.label),
                ApplianceAttribute.AttributesName.NOISE_LEVEL, List.of("70", "65", "60", "55", "50", "45", "40"),
                ApplianceAttribute.AttributesName.WATER_CONSUMPTION, List.of("70", "60", "50", "40", "30"),
                ApplianceAttribute.AttributesName.FAST_PROGRAM, List.of(ApplianceAttribute.FastProgram.YES.label, ApplianceAttribute.FastProgram.NO.label));
    }

}
