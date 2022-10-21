package model;

import model.enums.MeasureUnity;

import java.math.BigDecimal;

public class Temperature {

    private double value;
    private MeasureUnity unity;

    public Temperature(double value, MeasureUnity unity) {
        this.value = value;
        this.unity = unity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MeasureUnity getUnity() {
        return unity;
    }

    public void setUnity(MeasureUnity unity) {
        this.unity = unity;
    }

    @Override
    public String toString() {
        String aux = String.valueOf(value);

        switch (unity) {
            case CELSIUS -> aux += " ºC";
            case FAHRENHEIT -> aux += " °F";
            case KELVIN -> aux += " K";
        }

        return aux;
    }
}
