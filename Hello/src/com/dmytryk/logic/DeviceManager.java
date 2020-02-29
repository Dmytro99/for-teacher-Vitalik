package com.dmytryk.logic;

import com.dmytryk.model.ElectronicDevice;
import com.dmytryk.model.Kettle;
import com.dmytryk.model.Oven;
import com.dmytryk.model.Refrigerator;
import com.dmytryk.model.Toaster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DeviceManager {

    private List<ElectronicDevice> electronicDevices;

    public DeviceManager() {
        electronicDevices = new ArrayList<>();
        electronicDevices.add(new Refrigerator("refrigerator", 20, true));
        electronicDevices.add(new Kettle("kettle", 15, true));
        electronicDevices.add(new Oven("oven", 35, true));
        electronicDevices.add(new Toaster("toaster", 25, false));
    }

    public void allDevices() {
        electronicDevices.stream()
                .sorted(Comparator.comparingInt(ElectronicDevice::getPower))
                .forEach(System.out::print);
    }

    public void findByParameter() {
        Scanner sc = new Scanner(System.in);
        String strInput = sc.nextLine();
        int numInput = sc.nextInt();
        boolean boolInput = sc.nextBoolean();

        electronicDevices
                .stream()
                .filter(p -> p.getNameOfDevice().equals((strInput)))
                .filter(p -> p.getPower() == numInput)
                .filter(p -> p.getOn() == boolInput)
                .forEach(System.out::println);
    }

    public void allPower() {
        Integer power = electronicDevices.stream()
                .filter(ElectronicDevice::getOn)
                .map(ElectronicDevice::getPower)
                .reduce((a, b) -> a + b)
                .orElse(0);

        System.out.println(power);
    }


    public List<ElectronicDevice> getElectronicDevices() {
        return electronicDevices;
    }

}


