package service;

import model.Temperature;
import model.enums.MeasureUnity;

public class ConversorService {

    private MeasureUnity originUnity;
    private MeasureUnity targetUnity;

    public ConversorService(MeasureUnity originUnity, MeasureUnity targetUnity) {
        this.originUnity = originUnity;
        this.targetUnity = targetUnity;
    }

    public double converter(Temperature temperature) {

        double valor = temperature.getValue();

        if(originUnity.equals(targetUnity)) {
            return valor;
        }

        switch (targetUnity){
            case KELVIN:
                if(originUnity.equals(MeasureUnity.CELSIUS))
                    return celsiusToKelvin(valor);
                else
                    return fahrenheitToKelvin(valor);
            case CELSIUS:
                if(originUnity.equals(MeasureUnity.KELVIN))
                    return kelvinToCelsius(valor);
                else
                    return fahrenheitToCelsius(valor);
            case FAHRENHEIT:
                if(originUnity.equals(MeasureUnity.CELSIUS))
                    return celsiusToFahrenheit(valor);
                else
                    return kelvinToFahrenheit(valor);
        }

        return valor;
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * (9.0 / 5.0)) + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return fahrenheit*(9.0/5.0) + 32.0;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return ((fahrenheit - 32.0) * (5.0/9.0))  + 273.0;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (9.0/5.0)*(kelvin - 273.0) + 32.0;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.0;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.0;
    }

}
