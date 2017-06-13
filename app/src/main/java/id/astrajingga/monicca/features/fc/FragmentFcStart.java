package id.astrajingga.monicca.features.fc;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
public class FragmentFcStart extends Fragment {
    // variables
    EditText fcstartEdittextBalance,
        fcstartEdittextIncome,
        fcstartEdittextSavings,
        fcstartEdittextMortgage;

    String fcstartStringBalance,
            fcstartStringIncome,
            fcstartStringSavings,
            fcstartStringMortgage,
            fcstartStringCondition;

    int parameter;

    public FragmentFcStart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fc_start_fragment, container, false);

        // visibility toggles
        TextInputLayout fcstartTextlayoutBalance = (TextInputLayout)view.findViewById(R.id.fcstart_textlayout_balance);
        TextInputLayout fcstartTextlayoutIncome = (TextInputLayout)view.findViewById(R.id.fcstart_textlayout_income);
        TextInputLayout fcstartTextlayoutSavings = (TextInputLayout)view.findViewById(R.id.fcstart_textlayout_savings);
        TextInputLayout fcstartTextlayoutMortage = (TextInputLayout)view.findViewById(R.id.fcstart_textlayout_mortgage);
        if (fcstartTextlayoutBalance.getEditText() != null) {
            fcstartTextlayoutBalance.getEditText();
        }

        if (fcstartTextlayoutIncome.getEditText() != null) {
            fcstartTextlayoutIncome.getEditText();
        }

        if (fcstartTextlayoutSavings.getEditText() != null) {
            fcstartTextlayoutSavings.getEditText();
        }

        if (fcstartTextlayoutMortage.getEditText() != null) {
            fcstartTextlayoutMortage.getEditText();
        }

        fcstartEdittextBalance = (EditText) view.findViewById(R.id.fcstart_edittext_balance);
        fcstartEdittextBalance.addTextChangedListener(TextwatcherBalance());

        fcstartEdittextIncome = (EditText) view.findViewById(R.id.fcstart_edittext_income);
        fcstartEdittextIncome.addTextChangedListener(TextwatcherIncome());

        fcstartEdittextSavings = (EditText)view.findViewById(R.id.fcstart_edittext_savings);
        fcstartEdittextSavings.addTextChangedListener(TextwatcherSavings());

        fcstartEdittextMortgage = (EditText)view.findViewById(R.id.fcstart_edittext_mortgage);
        fcstartEdittextMortgage.addTextChangedListener(TextwatcherMortgage());

        // calculate button function
        Button fcStartButtonCalculate = (Button)view.findViewById(R.id.fcstart_button_calculate);
        fcStartButtonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            fcstartStringBalance = fcstartEdittextBalance.getText().toString().replaceAll(",", "");

            fcstartStringIncome = fcstartEdittextIncome.getText().toString().replaceAll(",", "");

            fcstartStringSavings = fcstartEdittextSavings.getText().toString().replaceAll(",", "");

            fcstartStringMortgage = fcstartEdittextMortgage.getText().toString().replaceAll(",", "");

            // fields check
            if (TextUtils.isEmpty(fcstartStringBalance)) {
                fcstartEdittextBalance.setError("You can't leave this empty.");
                return;
            } else if (TextUtils.isEmpty(fcstartStringIncome)) {
                fcstartEdittextIncome.setError("You can't leave this empty.");
                return;
            } else if (TextUtils.isEmpty(fcstartStringSavings)) {
                fcstartEdittextSavings.setError("You can't leave this empty.");
                return;
            } else if (TextUtils.isEmpty(fcstartStringMortgage)) {
                fcstartEdittextMortgage.setError("You can't leave this empty.");
                return;
            }

            double fcstartDoubleBalance = Double.parseDouble(fcstartStringBalance);

            double fcstartDoubleIncome = Double.parseDouble(fcstartStringIncome);

            double fcstartDoubleSavings = Double.parseDouble(fcstartStringSavings);

            double fcstartDoubleMortgage = Double.parseDouble(fcstartStringMortgage);

            // calculation to determine financial status result
            parameter = 0;
            if(fcstartDoubleBalance >= (2 * fcstartDoubleIncome)) {
                parameter = parameter + 1;
            } else {
                parameter = parameter + 0;
            }
            if(fcstartDoubleSavings >= (0.1 * fcstartDoubleIncome)) {
                parameter = parameter + 1;
            } else {
                parameter = parameter + 0;
            }
            if(fcstartDoubleMortgage <= (0.35 * fcstartDoubleIncome)) {
                parameter = parameter + 1;
            } else {
                parameter = parameter + 0;
            }

            if (parameter == 3) {
                fcstartStringCondition = "Excellent";
            } else if (parameter == 2) {
                fcstartStringCondition = "Good";
            } else if (parameter == 1) {
                fcstartStringCondition = "Warning";
            } else if (parameter == 0) {
                fcstartStringCondition = "Bad";
            }

            // calculation for ideal financial advice
            double fcstartDoubleIdealbalance = 3 * fcstartDoubleMortgage;
            double fcstartDoubleIdealsavings =  0.1 * fcstartDoubleIncome;
            double fcstartDoubleIdealmortgage = 0.35 * fcstartDoubleIncome;

            // declare a bundle and fill it with variables
            Bundle bundle = new Bundle();
            bundle.putString("condition", fcstartStringCondition);
            bundle.putDouble("idealBalance", fcstartDoubleIdealbalance);
            bundle.putDouble("idealSavings", fcstartDoubleIdealsavings);
            bundle.putDouble("idealMortgage", fcstartDoubleIdealmortgage);

            // declare FragmentManager to call fragmentFcResult
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentFcResult fragmentFcResult = new FragmentFcResult();
            fragmentFcResult.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_container, fragmentFcResult).addToBackStack("Main");
            fragmentTransaction.commit();
            }
        });

        return view;
    }

    // this is definitely not the right practice to do multiple TextWacther, i'll do some research later on this one
    // balance EditText TextWatcher
    private TextWatcher TextwatcherBalance() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fcstartEdittextBalance.removeTextChangedListener(this);

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
                    fcstartEdittextBalance.setText(formattedString);
                    fcstartEdittextBalance.setSelection(fcstartEdittextBalance.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                fcstartEdittextBalance.addTextChangedListener(this);
            }
        };
    }

    // income EditText TextWatcher
    private TextWatcher TextwatcherIncome() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fcstartEdittextIncome.removeTextChangedListener(this);

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
                    fcstartEdittextIncome.setText(formattedString);
                    fcstartEdittextIncome.setSelection(fcstartEdittextIncome.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                fcstartEdittextIncome.addTextChangedListener(this);
            }
        };
    }

    // savings EditText TextWatcher
    private TextWatcher TextwatcherSavings() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fcstartEdittextSavings.removeTextChangedListener(this);

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
                    fcstartEdittextSavings.setText(formattedString);
                    fcstartEdittextSavings.setSelection(fcstartEdittextSavings.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                fcstartEdittextSavings.addTextChangedListener(this);
            }
        };
    }

    // mortgage EditText TextWatcher
    private TextWatcher TextwatcherMortgage() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fcstartEdittextMortgage.removeTextChangedListener(this);

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
                    fcstartEdittextMortgage.setText(formattedString);
                    fcstartEdittextMortgage.setSelection(fcstartEdittextMortgage.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                fcstartEdittextMortgage.addTextChangedListener(this);
            }
        };
    }
}
