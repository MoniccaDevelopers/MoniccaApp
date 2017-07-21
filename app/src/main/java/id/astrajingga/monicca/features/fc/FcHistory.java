package id.astrajingga.monicca.features.fc;

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
 * Created by Djaffar on 7/14/2017.
 */

public class FcHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_history_activity);

        // variables
        ExpandableTextView expandableTextView;

        String fchistoryStringCondition;

        double fchistoryDoubleIdealbalance,
                fchistoryDoubleIdealsavings,
                fchistoryDoubleIdealmortgage;

        Bundle bundle = getIntent().getExtras();

        fchistoryStringCondition = bundle.getString("condition");
        fchistoryDoubleIdealbalance = bundle.getDouble("idealBalance");
        fchistoryDoubleIdealsavings = bundle.getDouble("idealSavings");
        fchistoryDoubleIdealmortgage = bundle.getDouble("idealMortgage");

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        indonesianRp.setMaximumFractionDigits(0);
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
                "More Detail...\n" +
                "MONICCA Recommends\n" +
                "Ideal Minimum Balance: " + fchistoryStringIdealbalance + "\n" +
                "Ideal Minimum Monthly Savings: " + fchistoryStringIdealsavings + "\n" +
                "Ideal Maximum Monthly Mortgage: " + fchistoryStringIdealmortgage;

        expandableTextView = (ExpandableTextView) findViewById(R.id.fchistory_expand_item);
        expandableTextView.setText(history);

        Button fchistoryButtonHome = (Button) findViewById(R.id.fchistory_button_home);
        fchistoryButtonHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(FcHistory.this, Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
