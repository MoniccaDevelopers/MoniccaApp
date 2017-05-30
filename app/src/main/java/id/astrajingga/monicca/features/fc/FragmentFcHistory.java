package id.astrajingga.monicca.features.fc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import id.astrajingga.monicca.R;

public class FragmentFcHistory extends Fragment {
    // variables
    ExpandableTextView expandableTextView;

    String fchistoryStringCondition;
    
    double fchistoryDoubleIdealbalance,
            fchistoryDoubleIdealsavings,
            fchistoryDoubleIdealmortgage;

    public FragmentFcHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fc_history_fragment, container, false);

        Bundle bundle = getArguments();

        fchistoryStringCondition = bundle.getString("condition");
        fchistoryDoubleIdealbalance = bundle.getDouble("idealBalance");
        fchistoryDoubleIdealsavings = bundle.getDouble("idealSavings");
        fchistoryDoubleIdealmortgage = bundle.getDouble("idealMortgage");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        String fchistoryStringIdealbalance = indonesianRp.format(fchistoryDoubleIdealbalance);
        String fchistoryStringIdealsavings = indonesianRp.format(fchistoryDoubleIdealsavings);
        String fchistoryStringIdealmortgage = indonesianRp.format(fchistoryDoubleIdealmortgage);

        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String history = date + "\n\n" +
                "Financial Condition: " + fchistoryStringCondition + "\n\n" +
                "MONICCA Recommends\n" +
                "Ideal Balance: " + fchistoryStringIdealbalance + "\n" +
                "Ideal Monthly Savings: " + fchistoryStringIdealsavings + "\n" +
                "Ideal Monthly Mortgage: " + fchistoryStringIdealmortgage;

        expandableTextView = (ExpandableTextView)view.findViewById(R.id.fchistory_expand_item);
        expandableTextView.setText(history);

        Button fchistoryButtonHome = (Button)view.findViewById(R.id.fchistory_button_home);
        fchistoryButtonHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = "DEMO ACCOUNT";

                Intent intent =  new Intent(getActivity(), id.astrajingga.monicca.Main.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        return view;
    }
}