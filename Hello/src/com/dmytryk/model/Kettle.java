package com.dmytryk.model;

public class Kettle extends ElectronicDevice {

    public Kettle(String kettle, int fifteen, boolean on) {
        setNameOfDevice(kettle);
        setPower(fifteen);
        setOn(on);
    }

}
