package com.dmytryk.model;

public class Toaster extends ElectronicDevice {

    public Toaster(String toaster, int twentyFive, boolean off) {
        setNameOfDevice(toaster);
        setPower(twentyFive);
        setOn(off);
    }

}
