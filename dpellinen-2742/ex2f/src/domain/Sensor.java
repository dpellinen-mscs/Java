package domain;

import java.util.ArrayList;
import java.util.Objects;

public class Sensor {
    private int sensorId;
    private int roomId;
    private int sensorTypeId;
    private String description;
    private Room room;
    private SensorType sensorType;
    private ArrayList<SensorReading> sensorReadings;

    public Sensor(int sensorId, int roomId, int sensorTypeId, String description) {
        this.sensorId = sensorId;
        this.roomId = roomId;
        this.sensorTypeId = sensorTypeId;
        this.description = description;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public domain.Room getRoom() {
        return room;
    }

    public void setRoom(domain.Room room) {
        this.room = room;
    }

    public domain.SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(domain.SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return sensorId == sensor.sensorId &&
                roomId == sensor.roomId &&
                sensorTypeId == sensor.sensorTypeId &&
                Objects.equals(description, sensor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, roomId, sensorTypeId, description);
    }

    @Override
    public String toString() {
        return Integer.toString(sensorId) +
                ", roomId=" + roomId +
                ", sensorTypeId=" + sensorTypeId +
                ", " + description;
    }

    public ArrayList<SensorReading> getSensorReadings() {
        return sensorReadings;
    }

    public void setSensorReadings(ArrayList<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }

    public int findMinReadingIndex() {
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < sensorReadings.size(); i++) {
            float value = sensorReadings.get(i).getValue();

            if (value < minValue) {
                minValue = (int)value;
                minIndex = i;
            }
        }

        return minIndex;
    }

    public int findMinReadingIndex1(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= sensorReadings.size() || startIndex > endIndex) {
            throw new IndexOutOfBoundsException("Invalid startIndex or endIndex");
        }

        float minValue = sensorReadings.get(startIndex).getValue(); // Initialize with the first value

        for (int i = startIndex + 1; i <= endIndex; i++) {
            float value = sensorReadings.get(i).getValue();

            if (value < minValue) {
                minValue = value;
            }
        }

        return (int)minValue;
    }


    public int findMaxReadingIndex()  {
        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < sensorReadings.size(); i++) {
            float value = sensorReadings.get(i).getValue();

            if (value > maxValue) {
                maxValue = (int)value;
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public int findMaxReadingIndex1(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= sensorReadings.size() || startIndex > endIndex) {
            throw new IndexOutOfBoundsException("Invalid startIndex or endIndex");
        }

        float maxValue = sensorReadings.get(startIndex).getValue(); // Initialize with the first value

        for (int i = startIndex + 1; i <= endIndex; i++) {
            float value = sensorReadings.get(i).getValue();

            if (value > maxValue) {
                maxValue = value;
            }
        }

        return (int)maxValue;
    }

    public int findNextCycleMax (int startIndex) {
        SensorReading rMax = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for (; i < this.sensorReadings.size(); i++) {
            if (rMax.getValue() < this.sensorReadings.get(i).getValue())
                rMax = this.sensorReadings.get(i);
            else
                break;
        }
        return i -1;
    }

    public int findNextCycleMin (int startIndex) {
        SensorReading rMin = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for (; i < this.sensorReadings.size(); i++) {
            if (rMin.getValue() > this.sensorReadings.get(i).getValue())
                rMin = this.sensorReadings.get(i);
            else
                break;
        }
        return i -1;
    }

    public SensorReading getSensorReading (int index) {
        return this.sensorReadings.get(index);
    }
}