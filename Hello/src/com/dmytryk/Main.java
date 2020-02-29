package com.dmytryk;

import com.dmytryk.logic.DeviceManager;

public class Main {

    public static void main(String[] args) {
        DeviceManager deviceManager = new DeviceManager();
        deviceManager.allDevices();
        deviceManager.findByParameter();
        deviceManager.allPower();
    }

}
