package id.astrajingga.monicca.features.gb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGbResult extends Fragment {
    // variables
    ExpandableTextView expandableTextView;

    String gbresultStringObject,
            gbresultStringObjectname,
            gbresultStringTime,
            gbresultStringTimevalue;

    double gbresultDoubleTimevalue,
            gbresultDoubleObjectprice,
            gbresultDoubleFuturevalue,
            gbresultDoubleSavingsyearly,
            gbresultDoubleSavingsmonthly;

    public FragmentGbResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gb_result_fragment, container, false);

        Bundle bundle = getArguments();
        gbresultStringObject = bundle.getString("object");
        gbresultStringObjectname = bundle.getString("objectname");
        gbresultStringTime = bundle.getString("time");
        gbresultDoubleTimevalue = bundle.getDouble("timevalue");
        gbresultDoubleObjectprice = bundle.getDouble("objectprice");
        gbresultDoubleFuturevalue = bundle.getDouble("futurevalue");
        gbresultDoubleSavingsyearly = bundle.getDouble("savingsyearly");
        gbresultDoubleSavingsmonthly = bundle.getDouble("savingsmonthly");

        // set decimal format
        DecimalFormat decimalFormat =  new DecimalFormat("0.#");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        gbresultStringTimevalue = decimalFormat.format(gbresultDoubleTimevalue);

        TextView gbresultTextObject = (TextView)view.findViewById(R.id.gbresult_text_object);
        gbresultTextObject.setText(gbresultStringObject);

        TextView gbresultTextObjectname = (TextView)view.findViewById(R.id.gbresult_text_objectname);
        gbresultTextObjectname.setText(gbresultStringObjectname);

        TextView gbresultTextObjectprice = (TextView)view.findViewById(R.id.gbresult_text_objectprice);
        gbresultTextObjectprice.setText(indonesianRp.format(gbresultDoubleObjectprice));

        TextView gbresultTextTimevalue = (TextView)view.findViewById(R.id.gbresult_text_timevalue);
        gbresultTextTimevalue.setText(gbresultStringTimevalue);

        TextView gbresultTextTime = (TextView)view.findViewById(R.id.gbresult_text_time);
        gbresultTextTime.setText(gbresultStringTime);

        TextView gbresultTextFuturevalue = (TextView)view.findViewById(R.id.gbresult_text_futurevalue);
        gbresultTextFuturevalue.setText(indonesianRp.format(gbresultDoubleFuturevalue));

        String gbresultStringSavingsyearly = indonesianRp.format(gbresultDoubleSavingsyearly);

        String gbresultStringSavingsmonthly = indonesianRp.format(gbresultDoubleSavingsmonthly);

        // advice
        String advice = "Savings for Desired Object\n\n" +
                "per Year: " + gbresultStringSavingsyearly + "\n" +
                "per Month: " + gbresultStringSavingsmonthly;

        expandableTextView = (ExpandableTextView)view.findViewById(R.id.gbresult_expand_advice);
        expandableTextView.setText(advice);

        Button gbresultButtonSave = (Button)view.findViewById(R.id.gbresult_button_save);
        gbresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("object", gbresultStringObject);
                bundle.putString("objectname", gbresultStringObjectname);
                bundle.putString("time", gbresultStringTime);
                bundle.putDouble("timevalue", gbresultDoubleTimevalue);
                bundle.putDouble("objectprice", gbresultDoubleObjectprice);
                bundle.putDouble("futurevalue", gbresultDoubleFuturevalue);
                bundle.putDouble("savingsyearly", gbresultDoubleSavingsyearly);
                bundle.putDouble("savingsmonthly", gbresultDoubleSavingsmonthly);

                // declare FragmentManager to call fragmentFcResult
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentGbHistory fragmentGbHistory = new FragmentGbHistory();
                fragmentGbHistory.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragmentGbHistory).addToBackStack("Main");
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
