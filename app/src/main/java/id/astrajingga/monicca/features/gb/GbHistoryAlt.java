package id.astrajingga.monicca.features.gb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class GbHistoryAlt extends AppCompatActivity {
    // variables
    ExpandableTextView expandableTextView;

    String object,
            name,
            deadline,
            time;

    double price,
            futureValue,
            yearlyResult,
            monthlyResult;

    double[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gb_history_activity_alt);

        Bundle bundle = getIntent().getExtras();

        object = bundle.getString("object");
        name = bundle.getString("name");
        deadline = bundle.getString("deadline");
        time = bundle.getString("time");

        price = bundle.getDouble("price");
        result = bundle.getDoubleArray("result");

        futureValue = result[0];
        yearlyResult = result[1];
        monthlyResult = result[2];

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

        String gbhistoryStringObjectprice = indonesianRp.format(price);
        String gbhistoryStringFuturevalue = indonesianRp.format(futureValue);
        String gbhistoryStringSavingsyearly = indonesianRp.format(yearlyResult);
        String gbhistoryStringSavingmonthly = indonesianRp.format(monthlyResult);

        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String history = date + "\n\n" +
                "Object: " + object + "\n\n" +
                "More Detail...\n\n" +
                "Object Name or Title: " + name + "\n" +
                "Current Price: " + gbhistoryStringObjectprice + "\n" +
                "Target Deadline: " + deadline + " " + time + "\n" +
                "Estimate Price: " + gbhistoryStringFuturevalue + "\n\n" +
                "MONICCA Recommends\n\n" +
                "Savings for Desired Object\n" +
                "per Year: " + gbhistoryStringSavingsyearly + "\n" +
                "per Month: " + gbhistoryStringSavingmonthly;

        expandableTextView = (ExpandableTextView) findViewById(R.id.gbhistory_expand_item);
        expandableTextView.setText(history);

        Button gbhistoryButtonHome = (Button) findViewById(R.id.gbhistory_button_home);
        gbhistoryButtonHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = "DEMO ACCOUNT";

                Intent intent =  new Intent(GbHistoryAlt.this, id.astrajingga.monicca.Main.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });
    }
}
