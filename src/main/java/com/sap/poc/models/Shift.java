package com.sap.poc.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Shift {
    ANY,
    DAY,
    NIGHT;

    public static List<Shift> getValidShifts() {
        List<Shift> list = new ArrayList<>(Arrays.asList(Shift.values()));
        list.remove(Shift.ANY);
        return list;
    }
}
