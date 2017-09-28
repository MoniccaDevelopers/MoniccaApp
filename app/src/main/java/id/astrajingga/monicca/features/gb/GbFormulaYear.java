package id.astrajingga.monicca.features.gb;

/**
 * Created by Djaffar on 7/19/2017.
 */

public class GbFormulaYear {
    private double interest = 0.0096;
    private double inflationCar = 0.075;
    private double inflationBike = 0.03;
    private double inflationWedding = 0.125;
    private double inflationTrip = 0.15;
    private double inflationGadget = 0.36;

    private double futureValue;
    private double factor;
    private double yearlyResult;
    private double monthlyResult;

    public double[] Car (double price, double deadline) {

        futureValue = price * Math.pow((1 + inflationCar), deadline);
        factor = (Math.pow((1 + interest), deadline) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Bike (double price, double deadline) {

        futureValue = price * Math.pow((1 + inflationBike), deadline);
        factor = (Math.pow((1 + interest), deadline) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Wedding (double price, double deadline) {

        futureValue = price * Math.pow((1 + inflationWedding), deadline);
        factor = (Math.pow((1 + interest), deadline) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Trip (double price, double deadline) {

        futureValue = price * Math.pow((1 + inflationTrip), deadline);
        factor = (Math.pow((1 + interest), deadline) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                yearlyResult,
                monthlyResult
        };

        return result;
    }

    public double[] Gadget (double price, double deadline) {

        futureValue = price * Math.pow((1 - inflationGadget), deadline);
        factor = (Math.pow((1 + interest), deadline) - 1) / interest;
        yearlyResult = futureValue / factor;
        monthlyResult = yearlyResult / 12;

        double[] result = {
                futureValue,
                yearlyResult,
                monthlyResult
        };

        return result;
    }
}
