package id.astrajingga.monicca.features.gb;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGbStart extends Fragment {
    //variables

    Spinner gbstartSpinnerObject,
            gbstartSpinnerTime;

    ArrayAdapter<CharSequence> adapterObject,
                                adapterTime;
    
    EditText gbstartEdittextObjectname,
            gbstartEdittextTimevalue,
            gbstartEdittextObjectprice;

    TextView gbstartTextBasedon,
            gbstartTextInflationrate;

    String gbstartStringObject,
            gbstartStringObjectname,
            gbstartStringObjectprice,
            gbstartStringTimevalue,
            gbstartStringTime;

    double gbstartDoubleObjectprice,
            gbstartDoubleTimevalue,
            gbstartDoubleFuturevalue,
            gbstartDoubleSavingsyearly,
            gbstartDoubleSavingsmonthly;

    public FragmentGbStart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gb_start_fragment, container, false);

        gbstartTextBasedon = (TextView) view.findViewById(R.id.gbstart_text_basedon);

        gbstartTextInflationrate = (TextView) view.findViewById(R.id.gbstart_text_inflationrate);

        gbstartEdittextObjectname = (EditText)view.findViewById(R.id.gbstart_edittext_objectname);

        gbstartEdittextObjectprice = (EditText)view.findViewById(R.id.gbstart_edittext_objectprice);
        gbstartEdittextObjectprice.addTextChangedListener(TextwatcherObjectprice());

        gbstartEdittextTimevalue = (EditText)view.findViewById(R.id.gbstart_edittext_timevalue);

        // spinner object
        gbstartSpinnerObject = (Spinner)view.findViewById(R.id.gbstart_spinner_object);
        adapterObject = ArrayAdapter.createFromResource(getContext(), R.array.object_list, android.R.layout.simple_spinner_item);
        adapterObject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gbstartSpinnerObject.setAdapter(adapterObject);
        gbstartSpinnerObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(getContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();

                // determine market info content
                if (gbstartSpinnerObject.getSelectedItem().toString().equals("Car")) {
                    gbstartTextBasedon.setText("Based on MarketWatch's Research");
                    gbstartTextInflationrate.setText("Car Price Deflation: 10.00% / Year");
                } else if (gbstartSpinnerObject.getSelectedItem().toString().equals("Bike")) {
                    gbstartTextBasedon.setText("Based on MarketWatch's Research");
                    gbstartTextInflationrate.setText("Bike Price Deflation: 13.00% / Year");
                } else if (gbstartSpinnerObject.getSelectedItem().toString().equals("Marriage")) {
                    gbstartTextBasedon.setText("Based on BIG Media's Research");
                    gbstartTextInflationrate.setText("Marriage Cost Inflation: 15.00% / Year");
                } else if (gbstartSpinnerObject.getSelectedItem().toString().equals("Trip")) {
                    gbstartTextBasedon.setText("Based on ZapFinance's Survey");
                    gbstartTextInflationrate.setText("Trip Cost Inflation: 15.10% / Year");
                } else if (gbstartSpinnerObject.getSelectedItem().toString().equals("Gadget")) {
                    gbstartTextBasedon.setText("Based on MarketWatch's Survey");
                    gbstartTextInflationrate.setText("Gadget Price Deflation: 10.00% / Year");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });

        // spinner time
        gbstartSpinnerTime = (Spinner)view.findViewById(R.id.gbstart_spinner_time);
        adapterTime = ArrayAdapter.createFromResource(getContext(), R.array.time_list, android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gbstartSpinnerTime.setAdapter(adapterTime);
        gbstartSpinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });

        Button gbstartButtonCalculate = (Button)view.findViewById(R.id.gbstart_button_calculate);
        gbstartButtonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                gbstartStringObject = gbstartSpinnerObject.getSelectedItem().toString();

                gbstartStringTime = gbstartSpinnerTime.getSelectedItem().toString();

                gbstartStringObjectname = gbstartEdittextObjectname.getText().toString();

                gbstartStringObjectprice = gbstartEdittextObjectprice.getText().toString().replaceAll(",", "");

                gbstartStringTimevalue = gbstartEdittextTimevalue.getText().toString();

                // fields check
                if (TextUtils.isEmpty(gbstartStringObjectname)) {
                    gbstartEdittextObjectname.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(gbstartStringTimevalue)) {
                    gbstartEdittextTimevalue.setError("You can't leave this empty.");
                    return;
                }

                gbstartDoubleObjectprice = Double.valueOf(gbstartStringObjectprice);

                gbstartDoubleTimevalue = Double.valueOf(gbstartStringTimevalue);

                // variables for calculation
                double interest = 0.065;
                double carPriceDeflation = 0.1;
                double bikePriceDeflation = 0.13;
                double marriageCostInflation = 0.15;
                double tripCostInflation = 0.151;
                double gadgetPriceDeflation = 0.1;

                // conditional, logic, calculation and fucking cancer
                if (gbstartStringObject.equals("Car")) {
                    if (gbstartStringTime.equals("Month")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * (Math.pow((1 - carPriceDeflation), (gbstartDoubleTimevalue / 12)));
                        double factor = (Math.pow((1 + interest), (gbstartDoubleTimevalue / 12)) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    } else if (gbstartStringTime.equals("Year")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * Math.pow((1 - carPriceDeflation), gbstartDoubleTimevalue);
                        double factor = (Math.pow((1 + interest), gbstartDoubleTimevalue) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    }

                } else if (gbstartStringObject.equals("Bike")) {
                    if (gbstartStringTime.equals("Month")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * (Math.pow((1 - bikePriceDeflation), (gbstartDoubleTimevalue / 12)));
                        double factor = (Math.pow((1 + interest), (gbstartDoubleTimevalue / 12)) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    } else if (gbstartStringTime.equals("Year")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * Math.pow((1 - bikePriceDeflation), gbstartDoubleTimevalue);
                        double factor = (Math.pow((1 + interest), gbstartDoubleTimevalue) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    }

                } else if (gbstartStringObject.equals("Marriage")) {
                    if (gbstartStringTime.equals("Month")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * (Math.pow((1 + marriageCostInflation), (gbstartDoubleTimevalue / 12)));
                        double factor = (Math.pow((1 + interest), (gbstartDoubleTimevalue / 12)) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    } else if (gbstartStringTime.equals("Year")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * Math.pow((1 + marriageCostInflation), gbstartDoubleTimevalue);
                        double factor = (Math.pow((1 + interest), gbstartDoubleTimevalue) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    }

                } else if (gbstartStringObject.equals("Trip")) {
                    if (gbstartStringTime.equals("Month")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * (Math.pow((1 + tripCostInflation), (gbstartDoubleTimevalue / 12)));
                        double factor = (Math.pow((1 + interest), (gbstartDoubleTimevalue / 12)) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    } else if (gbstartStringTime.equals("Year")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * Math.pow((1 + tripCostInflation), gbstartDoubleTimevalue);
                        double factor = (Math.pow((1 + interest), gbstartDoubleTimevalue) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    }

                } else if (gbstartStringObject.equals("Gadget")) {
                    if (gbstartStringTime.equals("Month")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * (Math.pow((1 - gadgetPriceDeflation), (gbstartDoubleTimevalue / 12)));
                        double factor = (Math.pow((1 + interest), (gbstartDoubleTimevalue / 12)) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    } else if (gbstartStringTime.equals("Year")) {
                        gbstartDoubleFuturevalue = gbstartDoubleObjectprice * Math.pow((1 - gadgetPriceDeflation), gbstartDoubleTimevalue);
                        double factor = (Math.pow((1 + interest), gbstartDoubleTimevalue) - 1) / interest;
                        gbstartDoubleSavingsyearly = gbstartDoubleFuturevalue / factor;
                        gbstartDoubleSavingsmonthly = gbstartDoubleSavingsyearly / 12;
                    }
                }

            // declare a bundle and fill it with variables
            Bundle bundle = new Bundle();
            bundle.putString("object", gbstartStringObject);
            bundle.putString("objectname", gbstartStringObjectname);
            bundle.putString("time", gbstartStringTime);
            bundle.putDouble("timevalue", gbstartDoubleTimevalue);
            bundle.putDouble("objectprice", gbstartDoubleObjectprice);
            bundle.putDouble("futurevalue", gbstartDoubleFuturevalue);
            bundle.putDouble("savingsyearly", gbstartDoubleSavingsyearly);
            bundle.putDouble("savingsmonthly", gbstartDoubleSavingsmonthly);

            // declare FragmentManager to call fragmentEpResult
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentGbResult fragmentGbResult = new FragmentGbResult();
            fragmentGbResult.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_container, fragmentGbResult).addToBackStack("Main");
            fragmentTransaction.commit();
            }

        });

        return view;
    }

    // objectprice EditText TextWatcher
    private TextWatcher TextwatcherObjectprice() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gbstartEdittextObjectprice.removeTextChangedListener(this);

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
                    gbstartEdittextObjectprice.setText(formattedString);
                    gbstartEdittextObjectprice.setSelection(gbstartEdittextObjectprice.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                gbstartEdittextObjectprice.addTextChangedListener(this);
            }
        };
    }
}
