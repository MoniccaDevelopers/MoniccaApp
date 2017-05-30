package id.astrajingga.monicca.features.gb;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGbHistory extends Fragment {
    // variables
    ExpandableTextView expandableTextView;

    String gbhistoryStringObject,
            gbhistoryStringObjectname,
            gbhistoryStringTime;

    double gbhistoryDoubleTimevalue,
            gbhistoryDoubleObjectprice,
            gbhistoryDoubleFuturevalue,
            gbhistoryDoubleSavingsyearly,
            gbhistoryDoubleSavingsmonthly;

    public FragmentGbHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gb_history_fragment, container, false);

        Bundle bundle = getArguments();

        gbhistoryStringObject = bundle.getString("object");
        gbhistoryStringObjectname = bundle.getString("objectname");
        gbhistoryStringTime = bundle.getString("time");

        gbhistoryDoubleTimevalue = bundle.getDouble("timevalue");
        gbhistoryDoubleObjectprice = bundle.getDouble("objectprice");
        gbhistoryDoubleFuturevalue = bundle.getDouble("futurevalue");
        gbhistoryDoubleSavingsyearly = bundle.getDouble("savingsyearly");
        gbhistoryDoubleSavingsmonthly = bundle.getDouble("savingsmonthly");

        // set decimal format
        DecimalFormat decimalFormat =  new DecimalFormat("0.#");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        String gbhistoryStringTimevalue = decimalFormat.format(gbhistoryDoubleTimevalue);
        String gbhistoryStringObjectprice = indonesianRp.format(gbhistoryDoubleObjectprice);
        String gbhistoryStringFuturevalue = indonesianRp.format(gbhistoryDoubleFuturevalue);
        String gbhistoryStringSavingsyearly = indonesianRp.format(gbhistoryDoubleSavingsyearly);
        String gbhistoryStringSavingmonthly = indonesianRp.format(gbhistoryDoubleSavingsmonthly);

        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String history = date + "\n\n" +
                "Object: " + gbhistoryStringObject + "\n" +
                "Object Name or Title: " + gbhistoryStringObjectname + "\n" +
                "Current Price: " + gbhistoryStringObjectprice + "\n" +
                "Target Deadline: " + gbhistoryStringTimevalue + " " + gbhistoryStringTime + "\n" +
                "Estimate Price: " + gbhistoryStringFuturevalue + "\n\n" +
                "MONICCA Recommends\n\n" +
                "Savings for Desired Object\n" +
                "per Year: " + gbhistoryStringSavingsyearly + "\n" +
                "per Month: " + gbhistoryStringSavingmonthly;

        expandableTextView = (ExpandableTextView)view.findViewById(R.id.gbhistory_expand_item);
        expandableTextView.setText(history);

        Button gbhistoryButtonHome = (Button)view.findViewById(R.id.gbhistory_button_home);
        gbhistoryButtonHome.setOnClickListener(new View.OnClickListener() {

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
