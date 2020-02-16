package pl.swpws.model;

import java.util.*;

public class DataGenerator {
    public static List<ApplianceAttribute> getApplianceAttributeListExample() {
        List<ApplianceAttribute> list = new ArrayList<>();
        list.add(new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS2, 60, 65, false));
        list.add(new ApplianceAttribute(1000, 8, ApplianceAttribute.EnergyClass.APLUS, 70, 65, false));
        list.add(new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS, 60, 45, true));
        return list;
    }

    public static List<ApplianceAttribute> getApplianceAttributeList() {
        List<ApplianceAttribute> list = new ArrayList<>();
        list.add(new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS2, 60, 65, false));
        list.add(new ApplianceAttribute(1000, 8, ApplianceAttribute.EnergyClass.APLUS, 70, 65, false));
        list.add(new ApplianceAttribute(1000, 4, ApplianceAttribute.EnergyClass.APLUS, 60, 45, true));
        return list;
    }

    public static Map<ApplianceAttribute.AttributesName, List<String>> getApplianceAttributesNameMap() {
        Map<ApplianceAttribute.AttributesName, List<String>> map = new HashMap<>();
        map.put(ApplianceAttribute.AttributesName.SPIN_SPEED, Arrays.asList("800", "1000", "1200", "1400", "1600"));
        map.put(ApplianceAttribute.AttributesName.DRUM_CAPACITY, Arrays.asList("4", "5", "6", "7", "8", "9", "10"));
        map.put(ApplianceAttribute.AttributesName.ENERGY_CLASS, Arrays.asList(ApplianceAttribute.EnergyClass.A.label, ApplianceAttribute.EnergyClass.APLUS.label, ApplianceAttribute.EnergyClass.APLUS2.label, ApplianceAttribute.EnergyClass.APLUS3.label));
        map.put(ApplianceAttribute.AttributesName.NOISE_LEVEL, Arrays.asList("70", "65", "60", "55", "50", "45", "40"));
        map.put(ApplianceAttribute.AttributesName.WATER_CONSUMPTION, Arrays.asList("70", "60", "50", "40", "30"));
        map.put(ApplianceAttribute.AttributesName.FAST_PROGRAM, Arrays.asList(ApplianceAttribute.FastProgram.YES.label, ApplianceAttribute.FastProgram.NO.label));
        return map;
    }

}
