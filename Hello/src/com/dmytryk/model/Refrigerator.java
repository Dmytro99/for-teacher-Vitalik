package com.dmytryk.model;

public class Refrigerator extends ElectronicDevice {

    public Refrigerator(String refrigerator, int twenty, boolean on) {
        setNameOfDevice(refrigerator);
        setPower(twenty);
        setOn(on);
    }

}
