package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dataaccess.SensorReadingJSONParser;
import domain.SensorReading;
import domain.Sensor;
public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor(2,1,1,"Heat register");

        try {
            SensorReadingJSONParser.readFile("dpellinen-2742/ex2f/resources/readings.json");
            sensor.setSensorReadings(SensorReadingJSONParser.getSensorReadings());
        } catch (Exception e) {
            System.out.println(e);
        }
//        for (SensorReading r : sensorReadings) {
//            System.out.println(r.toString());
//        }
        int index = sensor.findMinReadingIndex();
        System.out.println("Min: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex();
        System.out.println("Max: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        index = sensor.findMinReadingIndex1(0, 4);
        System.out.println("Min between 0 and 4 = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex1(0, 4);
        System.out.println("Max between 0 and 4 = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        // Invalid index
//        index = sensor.findMinReadingIndex1(-1, 95);
//        System.out.println("Min between 0 and 4 = " + index + ", " +
//                sensor.getSensorReadings().get(index).toString());
//        index = sensor.findMaxReadingIndex1(8, 1000);
//        System.out.println("Max between 0 and 4 = " + index + ", " +
//                sensor.getSensorReadings().get(index).toString());

        try {
            index = sensor.findMaxReadingIndex1(-1, 10);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            index = sensor.findMaxReadingIndex1(1, -1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            index = sensor.findMaxReadingIndex1(10, 9);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            index = sensor.findMaxReadingIndex1(100, 118);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        index = sensor.findNextCycleMax(0);
        System.out.println("First max after index 0: index = " + index + ", " +
        sensor.getSensorReadings().get(index).toString());

        index = sensor.findNextCycleMin(0);
        System.out.println("First min after index 0: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());


    }
}