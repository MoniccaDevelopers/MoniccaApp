package id.astrajingga.monicca.features.gb;

import android.content.Intent;
import android.os.Bundle;
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

public class GbResultAlt extends AppCompatActivity {
    // variables
    ExpandableTextView expandableTextView;

    String object,
            name,
            time,
            deadlineString;

    double price,
            deadline,
            futureValue,
            yearlyResult,
            monthlyResult;

    double[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gb_result_activity_alt);

        Bundle bundle = getIntent().getExtras();

        object = bundle.getString("object");
        name = bundle.getString("name");
        time = bundle.getString("time");
        price = bundle.getDouble("price");
        deadline = bundle.getDouble("deadline");
        result = bundle.getDoubleArray("result");

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

        deadlineString = decimalFormat.format(deadline);
        futureValue = result[0];
        yearlyResult = result[1];
        monthlyResult = result[2];

        TextView gbresultTextObject = (TextView) findViewById(R.id.gbresult_text_object);
        gbresultTextObject.setText(object);

        TextView gbresultTextObjectname = (TextView) findViewById(R.id.gbresult_text_objectname);
        gbresultTextObjectname.setText(name);

        TextView gbresultTextObjectprice = (TextView) findViewById(R.id.gbresult_text_objectprice);
        gbresultTextObjectprice.setText(indonesianRp.format(price));

        TextView gbresultTextTimevalue = (TextView) findViewById(R.id.gbresult_text_timevalue);
        gbresultTextTimevalue.setText(deadlineString);

        TextView gbresultTextTime = (TextView) findViewById(R.id.gbresult_text_time);
        gbresultTextTime.setText(time);

        TextView gbresultTextFuturevalue = (TextView) findViewById(R.id.gbresult_text_futurevalue);
        gbresultTextFuturevalue.setText(indonesianRp.format(futureValue));

        String gbresultStringSavingsyearly = indonesianRp.format(yearlyResult);

        String gbresultStringSavingsmonthly = indonesianRp.format(monthlyResult);

        // advice
        String advice = "Savings for Desired Object\n\n" +
                "per Year: " + gbresultStringSavingsyearly + "\n" +
                "per Month: " + gbresultStringSavingsmonthly;

        expandableTextView = (ExpandableTextView) findViewById(R.id.gbresult_expand_advice);
        expandableTextView.setText(advice);

        Button gbresultButtonSave = (Button) findViewById(R.id.gbresult_button_save);
        gbresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("object", object);
                bundle.putString("name", name);
                bundle.putString("deadline", deadlineString);
                bundle.putString("time", time);
                bundle.putDouble("price", price);
                bundle.putDoubleArray("result", result);

                Intent intent = new Intent(GbResultAlt.this, GbHistoryAlt.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}