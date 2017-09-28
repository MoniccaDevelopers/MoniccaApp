package id.astrajingga.monicca.features.fc;

/**
 * Created by Djaffar on 7/25/2017.
 */

public class FcFormula {
    private double balanceIdeal;
    private double savingsIdeal;
    private double instalmentIdeal;

    private int parameter;

    public int ParameterCheck (double balance, double income, double savings, double instalment) {

        if (balance >= (2 * income)) {
            parameter++;
        } else {
            parameter = parameter;
        }

        if (savings >= (0.1 * income)) {
            parameter++;
        } else {
            parameter = parameter;
        }

        if (instalment <= (0.35 * income)) {
            parameter++;
        } else {
            parameter = parameter;
        }

        return parameter;
    }

    public double[] IdealResult (double income, double instalment) {

        balanceIdeal = 3 * instalment;
        savingsIdeal = 0.1 * income;
        instalmentIdeal = 0.35 * income;

        double[] result = {
                balanceIdeal,
                savingsIdeal,
                instalmentIdeal
        };

        return result;
    }
}
