package service;

import exception.TemperatureOutOfSystemSizeException;
import model.Temperature;
import model.enums.MeasureUnity;

public class TemperatureService {

    private MeasureUnity originUnity;
    private MeasureUnity targetUnity;
    private int nextAvailablePosition;
    private ConversorService conversorService;
    private final Temperature[] temperatures;

    public TemperatureService(int numberOfTemperatures, MeasureUnity originUnity, MeasureUnity targetUnity) {
        this.originUnity = originUnity;
        this.targetUnity = targetUnity;
        temperatures = new Temperature[numberOfTemperatures];
        conversorService = new ConversorService(originUnity, targetUnity);
        nextAvailablePosition = 0;
    }

    public MeasureUnity getOriginUnity() {
        return originUnity;
    }

    public void setOriginUnity(MeasureUnity originUnity) {
        this.originUnity = originUnity;
    }

    public MeasureUnity getTargetUnity() {
        return targetUnity;
    }

    public void setTargetUnity(MeasureUnity targetUnity) {
        this.targetUnity = targetUnity;
    }

    public double getOriginTemperaturesAverage() {
        double sum = 0.0;
        double length = (double) temperatures.length;

        for (Temperature temperature: temperatures) {
            if(temperature == null) break;
            sum += temperature.getValue();
        }

        return sum/length;
    }

    public double getTargetTemperaturesAverage() {
        double sum = 0.0;
        double length = (double) temperatures.length;

        for (Temperature temperature: temperatures) {
            if(temperature == null) break;
            sum += conversorService.converter(temperature);
        }

        return sum/length;
    }

    private boolean isTemperaturesArrayFull() {
        return nextAvailablePosition == temperatures.length;
    }

    public void addTemperature(Temperature temperature)
            throws TemperatureOutOfSystemSizeException {

        if (isTemperaturesArrayFull()) {
            throw new TemperatureOutOfSystemSizeException("Exceeded the maximum of temperatures!");
        }

        temperatures[nextAvailablePosition++] = temperature;

        if(isTemperaturesArrayFull()) {
            System.out.println("You have reached the maximum of temperatures!");
        }
    }

    public void printTemperatures() {
        for (int i = 0; i < temperatures.length; i++) {
            if(temperatures[i] == null)break;

            System.out.println("Temperature #" + (i+1) + ": " + temperatures[i]);
        }
    }

    public void printConvertedTemperatures() {
        String measureUnityString = "";

        for (int i = 0; i < temperatures.length; i++) {
            if(temperatures[i] == null)break;

            switch (temperatures[i].getUnity()) {
                case CELSIUS -> measureUnityString  = " °C";
                case FAHRENHEIT -> measureUnityString = " °F";
                case KELVIN -> measureUnityString = " K";
            }
            System.out.println("Temperature #" + (i+1) + ": " + conversorService.converter(temperatures[i]) + measureUnityString) ;
        }
    }

}
