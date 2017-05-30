package id.astrajingga.monicca.features.ep;


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
public class FragmentEpHistory extends Fragment {
    // variables
    ExpandableTextView expandableTextView;

    String ephistoryStringApplicantname,
            ephistoryStringInstitutionname,
            ephistoryStringEducationlevel,
            ephistoryStringEnrolltime;

    double ephistoryDoubleEntranceyear,
            ephistoryDoubleEntrancemonth,
            ephistoryDoubleTuitionyear,
            ephistoryDoubleTuitionmonth;

    public FragmentEpHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ep_history_fragment, container, false);

        Bundle bundle = getArguments();

        ephistoryStringApplicantname = bundle.getString("applicantname");
        ephistoryStringInstitutionname = bundle.getString("institutionname");
        ephistoryStringEducationlevel = bundle.getString("educationlevel");
        ephistoryStringEnrolltime = bundle.getString("enrolltime");

        ephistoryDoubleEntranceyear = bundle.getDouble("entranceyear");
        ephistoryDoubleEntrancemonth = bundle.getDouble("entrancemonth");
        ephistoryDoubleTuitionyear = bundle.getDouble("tuitionyear");
        ephistoryDoubleTuitionmonth = bundle.getDouble("tuitionmonth");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        String ephistoryStringEntranceyear = indonesianRp.format(ephistoryDoubleEntranceyear);
        String ephistoryStringEntrancemonth = indonesianRp.format(ephistoryDoubleEntrancemonth);
        String ephistoryStringTuitionyear = indonesianRp.format(ephistoryDoubleTuitionyear);
        String ephistoryStringTuitionmonth = indonesianRp.format(ephistoryDoubleTuitionmonth);

        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String history = date + "\n\n" +
                "Applicant Name: " + ephistoryStringApplicantname + "\n" +
                ephistoryStringEducationlevel + " at " + ephistoryStringInstitutionname + "\n" +
                ephistoryStringEnrolltime + " Years to go\n\n" +
                "MONICCA Recommends\n\n" +
                "Savings for Entrance Fee\n" +
                "per Year: " + ephistoryStringEntranceyear + "\n" +
                "per Month: " + ephistoryStringEntrancemonth + "\n\n" +
                "Savings for Tuition Fee\n" +
                "per Year: " + ephistoryStringTuitionyear + "\n" +
                "per Month: " + ephistoryStringTuitionmonth;

        expandableTextView = (ExpandableTextView)view.findViewById(R.id.ephistory_expand_item);
        expandableTextView.setText(history);

        Button ephistoryButtonHome = (Button)view.findViewById(R.id.ephistory_button_home);
        ephistoryButtonHome.setOnClickListener(new View.OnClickListener() {

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