package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpResult extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_result_activity);

        Bundle bundle = getIntent().getExtras();

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
        indonesianRp.setMaximumFractionDigits(0);
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        epresultStringEnrolltime = decimalFormat.format(epresultDoubleEnrolltime);

        TextView epresultTextApplicantname = (TextView) findViewById(R.id.epresult_text_applicantname);
        epresultTextApplicantname.setText(epresultStringApplicantname);

        TextView epresultTextInstitutionname = (TextView) findViewById(R.id.epresult_text_institutionname);
        epresultTextInstitutionname.setText(epresultStringInstitutionname);

        TextView epresultTextEducationlevel = (TextView) findViewById(R.id.epresult_text_educationlevel);
        epresultTextEducationlevel.setText(epresultStringEducationlevel);

        TextView epresultTextEnrollTime = (TextView) findViewById(R.id.epresult_text_enrolltime);
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

        expandableTextView = (ExpandableTextView) findViewById(R.id.epresult_expand_advice);
        expandableTextView.setText(advice);

        Button epresultButtonSave = (Button) findViewById(R.id.epresult_button_save);
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

                Intent intent = new Intent(EpResult.this, EpHistory.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
