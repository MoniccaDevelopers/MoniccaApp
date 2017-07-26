package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import id.astrajingga.monicca.Main;
import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpHistory extends AppCompatActivity{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_history_activity);

        Bundle bundle = getIntent().getExtras();

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
        indonesianRp.setMaximumFractionDigits(0);
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
                "Applicant Name: " + ephistoryStringApplicantname + "\n\n" +
                "More Detail...\n\n" +
                ephistoryStringEducationlevel + " at " + ephistoryStringInstitutionname + "\n" +
                ephistoryStringEnrolltime + " Years to go\n\n" +
                "MONICCA Recommends\n\n" +
                "Savings for Entrance Fee\n" +
                "per Year: " + ephistoryStringEntranceyear + "\n" +
                "per Month: " + ephistoryStringEntrancemonth + "\n\n" +
                "Savings for Tuition Fee\n" +
                "per Year: " + ephistoryStringTuitionyear + "\n" +
                "per Month: " + ephistoryStringTuitionmonth;

        expandableTextView = (ExpandableTextView) findViewById(R.id.ephistory_expand_item);
        expandableTextView.setText(history);

        Button ephistoryButtonHome = (Button) findViewById(R.id.ephistory_button_home);
        ephistoryButtonHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EpHistory.this, Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
