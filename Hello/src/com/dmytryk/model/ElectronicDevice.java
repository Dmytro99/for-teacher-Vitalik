package com.dmytryk.model;

public class ElectronicDevice {

    private String nameOfDevice;
    private int power;
    private boolean isOn;

    public boolean getOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getNameOfDevice() {
        return nameOfDevice;
    }

    public void setNameOfDevice(String nameOfDevice) {
        this.nameOfDevice = nameOfDevice;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "nameOfDevice: " + nameOfDevice +
                ", power: " + power +
                ", isOn: " + isOn +
                '}' + "\n";
    }
}
