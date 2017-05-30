package id.astrajingga.monicca.features.fc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFcResult extends Fragment {
    // variables
    String fcresultStringCondition;

    double fcresultDoubleIdealbalance,
            fcresultDoubleIdealsavings,
            fcresultDoubleIdealmortgage;

    public FragmentFcResult() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fc_result_fragment, container, false);

        Bundle bundle = getArguments();

        fcresultStringCondition = bundle.getString("condition");
        fcresultDoubleIdealbalance = bundle.getDouble("idealBalance");
        fcresultDoubleIdealsavings = bundle.getDouble("idealSavings");
        fcresultDoubleIdealmortgage = bundle.getDouble("idealMortgage");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        TextView fcresultTextCondition = (TextView)view.findViewById(R.id.fcresult_text_condition);
        fcresultTextCondition.setText(fcresultStringCondition);

        TextView fcresultTextBalance = (TextView)view.findViewById(R.id.fcresult_text_balance);
        fcresultTextBalance.setText(indonesianRp.format(fcresultDoubleIdealbalance));

        TextView fcresultTextSavings = (TextView)view.findViewById(R.id.fcresult_text_savings);
        fcresultTextSavings.setText(indonesianRp.format(fcresultDoubleIdealsavings));

        TextView fcresultTextMortgage = (TextView)view.findViewById(R.id.fcresult_text_mortgage);
        fcresultTextMortgage.setText(indonesianRp.format(fcresultDoubleIdealmortgage));

        // save button function
        Button fcresultButtonSave = (Button)view.findViewById(R.id.fcresult_button_save);
        fcresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            // declare a bundle and fill it with variables
            Bundle bundle = new Bundle();
            bundle.putString("condition", fcresultStringCondition);
            bundle.putDouble("idealBalance", fcresultDoubleIdealbalance);
            bundle.putDouble("idealSavings", fcresultDoubleIdealsavings);
            bundle.putDouble("idealMortgage", fcresultDoubleIdealmortgage);

            // declare FragmentManager to call fragmentFcResult
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentFcHistory fragmentFcHistory = new FragmentFcHistory();
            fragmentFcHistory.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_container, fragmentFcHistory).addToBackStack("Main");
            fragmentTransaction.commit();
            }
        });

        return view;

    }

}
