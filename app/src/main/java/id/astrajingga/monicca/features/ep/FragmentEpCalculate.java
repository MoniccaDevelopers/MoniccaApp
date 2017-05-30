package id.astrajingga.monicca.features.ep;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEpCalculate extends Fragment {
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

    public FragmentEpCalculate() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ep_calculate_fragment, container, false);

        Bundle bundle = getArguments();

        // variables passed from epstart
        epcalculateStringApplicantname = bundle.getString("applicantname");
        epcalculateDoubleApplicantage = bundle.getDouble("applicantage");
        epcalculateStringInstitutionname = bundle.getString("institutionname");
        epcalculateStringEducationlevel = bundle.getString("educationlevel");

        epcalculateEdittextEntrancefee = (EditText)view.findViewById(R.id.epcalculate_edittext_entrancefee);
        epcalculateEdittextEntrancefee.addTextChangedListener(TextwatcherEntrancefee());

        epcalculateEdittextTuitionfee = (EditText)view.findViewById(R.id.epcalculate_edittext_tuitionfee);
        epcalculateEdittextTuitionfee.addTextChangedListener(TextwatcherTuitionfee());

        epcalculateEdittextAnnualfee = (EditText)view.findViewById(R.id.epcalculate_edittext_annualfee);
        epcalculateEdittextAnnualfee.addTextChangedListener(TextwatcherAnnualfee());

        Button epcalculateButtonCalculate = (Button)view.findViewById(R.id.epcalculate_button_calculate);
        epcalculateButtonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                epcalculateStringEntrancefee = epcalculateEdittextEntrancefee.getText().toString().replaceAll(",", "");

                epcalculateStringTuitionfee = epcalculateEdittextTuitionfee.getText().toString().replaceAll(",", "");

                epcalculateStringAnnualfee = epcalculateEdittextAnnualfee.getText().toString().replaceAll(",", "");

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
                } else if (epcalculateStringEducationlevel.equals("College")) {
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
                double interest = 0.065;
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

                // declare FragmentManager to call fragmentEpResult
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentEpResult fragmentEpResult = new FragmentEpResult();
                fragmentEpResult.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragmentEpResult).addToBackStack("Main");
                fragmentTransaction.commit();

            }
        });

        return view;
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
