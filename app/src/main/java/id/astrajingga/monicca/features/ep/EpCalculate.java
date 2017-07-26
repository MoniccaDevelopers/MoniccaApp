package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import id.astrajingga.monicca.R;
import id.astrajingga.monicca.features.fc.FcHistory;
import id.astrajingga.monicca.features.fc.FcResult;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpCalculate extends AppCompatActivity {
    // variables
    EditText epcalculateEdittextEntrancefee,
            epcalculateEdittextTuitionfee,
            epcalculateEdittextAnnualfee;

    String epcalculateStringApplicantname,
            epcalculateStringInstitutionname,
            epcalculateStringEducationlevel,
            epcalculateStringEntrancefee,
            epcalculateStringTuitionfee,
            epcalculateStringAnnualfee;

    double epcalculateDoubleApplicantage,
            epcalculateDoubleEntrancefee,
            epcalculateDoubleTuitionfee,
            epcalculationDoubleAnnualfee,
            epcalculateDoubleEnrollTime,
            educationDuration,
            totalSemester,
            epcalculateDoubleEntranceyear,
            epcalculateDoubleEntrancemonth,
            epcalculateDoubleTuitionyear,
            epcalculateDOubleTuitionmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_calculate_activity);

        Bundle bundle = getIntent().getExtras();

        // variables passed from epstart
        epcalculateStringApplicantname = bundle.getString("applicantname");
        epcalculateDoubleApplicantage = bundle.getDouble("applicantage");
        epcalculateStringInstitutionname = bundle.getString("institutionname");
        epcalculateStringEducationlevel = bundle.getString("educationlevel");

        epcalculateEdittextEntrancefee = (EditText) findViewById(R.id.epcalculate_edittext_entrancefee);
        epcalculateEdittextEntrancefee.addTextChangedListener(TextwatcherEntrancefee());

        epcalculateEdittextTuitionfee = (EditText) findViewById(R.id.epcalculate_edittext_tuitionfee);
        epcalculateEdittextTuitionfee.addTextChangedListener(TextwatcherTuitionfee());

        epcalculateEdittextAnnualfee = (EditText) findViewById(R.id.epcalculate_edittext_annualfee);
        epcalculateEdittextAnnualfee.addTextChangedListener(TextwatcherAnnualfee());

        Button epcalculateButtonCalculate = (Button) findViewById(R.id.epcalculate_button_calculate);
        epcalculateButtonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                epcalculateStringEntrancefee = epcalculateEdittextEntrancefee.getText().toString().trim().replaceAll(",", "");

                epcalculateStringTuitionfee = epcalculateEdittextTuitionfee.getText().toString().trim().replaceAll(",", "");

                epcalculateStringAnnualfee = epcalculateEdittextAnnualfee.getText().toString().trim().replaceAll(",", "");

                // fields check
                if (TextUtils.isEmpty(epcalculateStringEntrancefee)) {
                    epcalculateEdittextEntrancefee.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(epcalculateStringTuitionfee)) {
                    epcalculateEdittextTuitionfee.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(epcalculateStringAnnualfee)) {
                    epcalculateEdittextAnnualfee.setError("You can't leave this empty.");
                    return;
                }

                // determine enrollTime, educationDuration and totalSemester value
                if (epcalculateStringEducationlevel.equals("Elementary School")) {
                    epcalculateDoubleEnrollTime = 6 - epcalculateDoubleApplicantage;
                    educationDuration = 3;
                    totalSemester = 12;
                } else if (epcalculateStringEducationlevel.equals("Middle School")) {
                    epcalculateDoubleEnrollTime = 12 - epcalculateDoubleApplicantage;
                    educationDuration = 3;
                    totalSemester = 12;
                } else if (epcalculateStringEducationlevel.equals("High School")) {
                    epcalculateDoubleEnrollTime = 15 - epcalculateDoubleApplicantage;
                    educationDuration = 3;
                    totalSemester = 12;
                } else if (epcalculateStringEducationlevel.equals("College / Univ.")) {
                    educationDuration = 4;
                    totalSemester = 2;
                    if (epcalculateDoubleApplicantage > 18) {
                        epcalculateDoubleEnrollTime = epcalculateDoubleApplicantage - 18;
                    } else {
                        epcalculateDoubleEnrollTime = 18 - epcalculateDoubleApplicantage;
                    }
                }

                // variables for calculation
                epcalculateDoubleEntrancefee = Double.parseDouble(epcalculateStringEntrancefee);
                epcalculateDoubleTuitionfee = Double.parseDouble(epcalculateStringTuitionfee);
                epcalculationDoubleAnnualfee = Double.parseDouble(epcalculateStringAnnualfee);
                double interest = 0.0096;
                double inflationRate = 0.05;
                double educationCost = 0.151;
                double totalTuition = totalSemester * epcalculateDoubleTuitionfee;

                // calculate future entrance fee
                double futureValue = epcalculateDoubleEntrancefee * Math.pow((1 + educationCost), epcalculateDoubleEnrollTime);
                double factorOne = (Math.pow((1 + interest), epcalculateDoubleEnrollTime) - 1) / interest;
                epcalculateDoubleEntranceyear = futureValue / factorOne;
                epcalculateDoubleEntrancemonth = epcalculateDoubleEntranceyear / 12;

                // calculate future tuition fee
                double futureValueMonth = totalTuition * Math.pow((1 + educationCost), epcalculateDoubleEnrollTime);
                double futureValueAnnual = epcalculationDoubleAnnualfee * Math.pow((1 + educationCost), epcalculateDoubleEnrollTime);
                double factorTwo = (1 - (1 / (Math.pow((1 + inflationRate), educationDuration)))) / inflationRate;
                double futureValueMonthFix =  (futureValueMonth * factorTwo) * (1 + inflationRate);
                double futureValueAnnualFix = (futureValueAnnual * factorTwo) * (1 + inflationRate);
                epcalculateDoubleTuitionyear = ((futureValueMonthFix + futureValueAnnualFix) / factorOne);
                epcalculateDOubleTuitionmonth = epcalculateDoubleTuitionyear / 12;

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("applicantname", epcalculateStringApplicantname);
                bundle.putString("institutionname", epcalculateStringInstitutionname);
                bundle.putString("educationlevel", epcalculateStringEducationlevel);
                bundle.putDouble("enrolltime", epcalculateDoubleEnrollTime);

                bundle.putDouble("entranceyear", epcalculateDoubleEntranceyear);
                bundle.putDouble("entrancemonth", epcalculateDoubleEntrancemonth);

                bundle.putDouble("tuitionyear", epcalculateDoubleTuitionyear);
                bundle.putDouble("tuitionmonth", epcalculateDOubleTuitionmonth);

                Intent intent = new Intent(EpCalculate.this, EpResult.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // this is definitely not the right practice to do multiple TextWacther, i'll do some research later on this one
    // entrancefee EditText TextWatcher
    private TextWatcher TextwatcherEntrancefee() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                epcalculateEdittextEntrancefee.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to fcstartEdittextBalance
                    epcalculateEdittextEntrancefee.setText(formattedString);
                    epcalculateEdittextEntrancefee.setSelection(epcalculateEdittextEntrancefee.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                epcalculateEdittextEntrancefee.addTextChangedListener(this);
            }
        };
    }

    // tuitionfee EditText TextWatcher
    private TextWatcher TextwatcherTuitionfee() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                epcalculateEdittextTuitionfee.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to fcstartEdittextBalance
                    epcalculateEdittextTuitionfee.setText(formattedString);
                    epcalculateEdittextTuitionfee.setSelection(epcalculateEdittextTuitionfee.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                epcalculateEdittextTuitionfee.addTextChangedListener(this);
            }
        };
    }

    // annualfee EditText TextWatcher
    private TextWatcher TextwatcherAnnualfee() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                epcalculateEdittextAnnualfee.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to fcstartEdittextBalance
                    epcalculateEdittextAnnualfee.setText(formattedString);
                    epcalculateEdittextAnnualfee.setSelection(epcalculateEdittextAnnualfee.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                epcalculateEdittextAnnualfee.addTextChangedListener(this);
            }
        };
    }
}
