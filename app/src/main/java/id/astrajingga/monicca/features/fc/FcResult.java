package id.astrajingga.monicca.features.fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/14/2017.
 */

public class FcResult extends AppCompatActivity {
    // variables
    String fcresultStringCondition;

    double fcresultDoubleIdealbalance,
            fcresultDoubleIdealsavings,
            fcresultDoubleIdealmortgage;

    ImageView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_result_activity);

        Bundle bundle = getIntent().getExtras();

        fcresultStringCondition = bundle.getString("condition");
        fcresultDoubleIdealbalance = bundle.getDouble("idealBalance");
        fcresultDoubleIdealsavings = bundle.getDouble("idealSavings");
        fcresultDoubleIdealmortgage = bundle.getDouble("idealMortgage");

        // determine the indicator bar
        indicator = (ImageView) findViewById(R.id.fcresult_img_indicator);
        if (fcresultStringCondition.equals("Bad")) {
            indicator.setImageDrawable(ContextCompat.getDrawable(FcResult.this, R.drawable.indicator_bad));
        } else if (fcresultStringCondition.equals("Warning")) {
            indicator.setImageDrawable(ContextCompat.getDrawable(FcResult.this, R.drawable.indicator_warning));
        } else if (fcresultStringCondition.equals("Good")) {
            indicator.setImageDrawable(ContextCompat.getDrawable(FcResult.this, R.drawable.indicator_good));
        } else if (fcresultStringCondition.equals("Excellent")) {
            indicator.setImageDrawable(ContextCompat.getDrawable(FcResult.this, R.drawable.indicator_excellent));
        }

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        indonesianRp.setMaximumFractionDigits(0);
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        TextView fcresultTextCondition = (TextView) findViewById(R.id.fcresult_text_condition);
        fcresultTextCondition.setText(fcresultStringCondition);

        TextView fcresultTextBalance = (TextView) findViewById(R.id.fcresult_text_balance);
        fcresultTextBalance.setText(indonesianRp.format(fcresultDoubleIdealbalance));

        TextView fcresultTextSavings = (TextView) findViewById(R.id.fcresult_text_savings);
        fcresultTextSavings.setText(indonesianRp.format(fcresultDoubleIdealsavings));

        TextView fcresultTextMortgage = (TextView) findViewById(R.id.fcresult_text_mortgage);
        fcresultTextMortgage.setText(indonesianRp.format(fcresultDoubleIdealmortgage));

        // save button function
        Button fcresultButtonSave = (Button) findViewById(R.id.fcresult_button_save);
        fcresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("condition", fcresultStringCondition);
                bundle.putDouble("idealBalance", fcresultDoubleIdealbalance);
                bundle.putDouble("idealSavings", fcresultDoubleIdealsavings);
                bundle.putDouble("idealMortgage", fcresultDoubleIdealmortgage);

                Intent intent = new Intent(FcResult.this, FcHistory.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
