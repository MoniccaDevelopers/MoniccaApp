package id.astrajingga.monicca.features.gb;

/**
 * Created by Djaffar on 7/19/2017.
 */

public class GbFormulaMonth {
    private double interest = 0.0096;
    private double inflationCar = 0.075;
    private double inflationBike = 0.03;
    private double inflationWedding = 0.125;
    private double inflationTrip = 0.15;
    private double deflationGadget = 0.36;

    private double futureValue;
    private double factor;
    private double monthlyResult;
    private double yearlyResult;

    public double[] Car (double price, double deadline) {

        futureValue = price * (Math.pow((1 + inflationCar), (deadline / 12)));
        factor = (Math.pow((1 + interest), (deadline / 12)) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                factor,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Bike (double price, double deadline) {

        futureValue = price * (Math.pow((1 + inflationBike), (deadline / 12)));
        factor = (Math.pow((1 + interest), (deadline / 12)) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                factor,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Wedding (double price, double deadline) {

        futureValue = price * (Math.pow((1 + inflationWedding), (deadline / 12)));
        factor = (Math.pow((1 + interest), (deadline / 12)) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                factor,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Trip (double price, double deadline) {

        futureValue = price * (Math.pow((1 + inflationTrip), (deadline / 12)));
        factor = (Math.pow((1 + interest), (deadline / 12)) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                factor,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Gadget (double price, double deadline) {

        futureValue = price * (Math.pow((1 - deflationGadget), (deadline / 12)));
        factor = (Math.pow((1 + interest), (deadline / 12)) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
            futureValue,
            factor,
            yearlyResult,
            monthlyResult
        };

        return result;
    }
}
