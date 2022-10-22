import exception.TemperatureParseException;
import model.Temperature;
import model.enums.MeasureUnity;
import service.TemperatureService;

import java.util.Scanner;

public class Main {

    private static void showWelcomeMessage() {
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to Temperature Conversor!");
        System.out.println("Author: Hugo Rafael");
        System.out.println("Made with 💛 for Santander Coders by Ada");
        System.out.println("-----------------------------------------------");
    }

    private static void showTemperatureOptions() {
        System.out.println("-----------------------------------------------");
        System.out.println("C - To choose Celsius");
        System.out.println("F - To choose Farenheit");
        System.out.println("K - To choose Kelvin");
        System.out.println("-----------------------------------------------");
    }

    private static void showApplicationMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("Application main menu: ");
        System.out.println("1 - To present all temperatures registered");
        System.out.println("2 - To add new temperature");
        System.out.println("3 - To get the average of the temperatures");
        System.out.println("4 - To show converted temperature values");
        System.out.println("0 - to exit");
        System.out.println("-----------------------------------------------");
    }

    private static void showAverageCalculationOptionMenu(MeasureUnity origin, MeasureUnity target) {
        System.out.println("a - to present average in " + origin.toString().toLowerCase());
        System.out.println("b - to present average in " + target.toString().toLowerCase());
        System.out.println("x - to exit to the main menu");
    }

    private static Temperature requestNewTemperature(MeasureUnity unity, Scanner keyboard) {
        System.out.print("Insert the temperature: ");
        Temperature temperature = new Temperature(keyboard.nextDouble(), unity);
        System.out.println("Temperature " + temperature + " has been recorded!");
        return temperature;
    }

    private static MeasureUnity requestMeasureUnity(String unityModelName, Scanner keyboard)
        throws TemperatureParseException {
        System.out.print("Insert the " + unityModelName + " measure unity: ");
        MeasureUnity unity;
        String unityString = keyboard.next();

        switch (unityString.toUpperCase()){
            case "C" -> unity = MeasureUnity.CELSIUS;
            case "F" -> unity = MeasureUnity.FAHRENHEIT;
            case "K" -> unity = MeasureUnity.KELVIN;
            default -> throw new TemperatureParseException("Incorrect measure of unit!");
        }

        return unity;
    }

    public static void main(String[] args) {

        showWelcomeMessage();

        Scanner keyboard = new Scanner(System.in);
        int option, size;
        TemperatureService temperatureService;

        System.out.println("some configurations before test...");
        System.out.print("Insert the amount of temperatures you want to test: ");
        size = keyboard.nextInt();

        showTemperatureOptions();
        MeasureUnity originUnity = requestMeasureUnity("origin", keyboard);
        MeasureUnity targetUnity = requestMeasureUnity("target", keyboard);

        temperatureService = new TemperatureService(size, originUnity, targetUnity);

        do {
            showApplicationMenu();
            option = keyboard.nextInt();

            switch (option) {
                case 1 -> temperatureService.printTemperatures();
                case 2 -> {
                    Temperature input = requestNewTemperature(temperatureService.getOriginUnity(), keyboard);
                    temperatureService.addTemperature(input);
                }
                case 3 -> {
                    showAverageCalculationOptionMenu(temperatureService.getOriginUnity(),
                            temperatureService.getTargetUnity());
                    String internalOption = keyboard.next();

                    switch (internalOption){
                        case "a" -> System.out.println(temperatureService.getOriginTemperaturesAverage());
                        case "b" -> System.out.println(temperatureService.getTargetTemperaturesAverage());
                    }
                }
                case 4 -> temperatureService.printConvertedTemperatures();
            }

        } while (option != 0);


    }

}