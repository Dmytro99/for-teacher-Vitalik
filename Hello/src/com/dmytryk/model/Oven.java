package com.dmytryk.model;

public class Oven extends ElectronicDevice {

    public Oven(String oven, int thirtyFive, boolean on) {
        setNameOfDevice(oven);
        setPower(thirtyFive);
        setOn(on);
    }

}
