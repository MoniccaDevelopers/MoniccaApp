package id.astrajingga.monicca.features.ep;


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
import id.astrajingga.monicca.features.fc.FragmentFcHistory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEpResult extends Fragment {
    // variables
    ExpandableTextView expandableTextView;

    String epresultStringApplicantname,
            epresultStringInstitutionname,
            epresultStringEducationlevel,
            epresultStringEnrolltime;

    double epresultDoubleEnrolltime,
            epresultDoubleEntranceyear,
            epresultDoubleEntrancemonth,
            epresultDoubleTuitionyear,
            epresultDoubleTuitionmonth;

    public FragmentEpResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ep_result_fragment, container, false);

        Bundle bundle = getArguments();

        epresultStringApplicantname = bundle.getString("applicantname");
        epresultStringInstitutionname = bundle.getString("institutionname");
        epresultStringEducationlevel = bundle.getString("educationlevel");

        epresultDoubleEnrolltime = bundle.getDouble("enrolltime");
        epresultDoubleEntranceyear = bundle.getDouble("entranceyear");
        epresultDoubleEntrancemonth = bundle.getDouble("entrancemonth");
        epresultDoubleTuitionyear = bundle.getDouble("tuitionyear");
        epresultDoubleTuitionmonth = bundle.getDouble("tuitionmonth");

        // set decimal format
        DecimalFormat decimalFormat =  new DecimalFormat("0.#");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        epresultStringEnrolltime = decimalFormat.format(epresultDoubleEnrolltime);

        TextView epresultTextApplicantname = (TextView)view.findViewById(R.id.epresult_text_applicantname);
        epresultTextApplicantname.setText(epresultStringApplicantname);

        TextView epresultTextInstitutionname = (TextView)view.findViewById(R.id.epresult_text_institutionname);
        epresultTextInstitutionname.setText(epresultStringInstitutionname);

        TextView epresultTextEducationlevel = (TextView)view.findViewById(R.id.epresult_text_educationlevel);
        epresultTextEducationlevel.setText(epresultStringEducationlevel);

        TextView epresultTextEnrollTime = (TextView)view.findViewById(R.id.epresult_text_enrolltime);
        epresultTextEnrollTime.setText(epresultStringEnrolltime);

        String epresultStringEntranceyear = indonesianRp.format(epresultDoubleEntranceyear);
        String epresultStringEntrancemonth = indonesianRp.format(epresultDoubleEntrancemonth);
        String epresultStringTuitionyear = indonesianRp.format(epresultDoubleTuitionyear);
        String epresultStringTuitionmonth = indonesianRp.format(epresultDoubleTuitionmonth);

        // advice
        String advice = "Savings for Entrance Fee\n" +
                                "per Year: " + epresultStringEntranceyear + "\n" +
                                "per Month: " + epresultStringEntrancemonth + "\n\n" +
                        "Savings for Tuition Fee\n" +
                                "per Year: " + epresultStringTuitionyear + "\n" +
                                "per Month: " + epresultStringTuitionmonth;

        expandableTextView = (ExpandableTextView)view.findViewById(R.id.epresult_expand_advice);
        expandableTextView.setText(advice);

        Button epresultButtonSave = (Button)view.findViewById(R.id.epresult_button_save);
        epresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("applicantname", epresultStringApplicantname);
                bundle.putString("institutionname", epresultStringInstitutionname);
                bundle.putString("educationlevel", epresultStringEducationlevel);
                bundle.putString("enrolltime", epresultStringEnrolltime);

                bundle.putDouble("entranceyear", epresultDoubleEntranceyear);
                bundle.putDouble("entrancemonth", epresultDoubleEntrancemonth);
                bundle.putDouble("tuitionyear", epresultDoubleTuitionyear);
                bundle.putDouble("tuitionmonth", epresultDoubleTuitionmonth);

                // declare FragmentManager to call fragmentFcResult
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentEpHistory fragmentEpHistory = new FragmentEpHistory();
                fragmentEpHistory.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragmentEpHistory).addToBackStack("Main");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
