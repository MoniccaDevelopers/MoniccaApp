package id.astrajingga.monicca.features.fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import id.astrajingga.monicca.MainActivity;
import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/14/2017.
 */

public class FcResultAlt extends AppCompatActivity {
    // variables
    private String condition;

    private double[] result;
    private double balanceIdeal;
    private double savingsIdeal;
    private double instalmentIdeal;

    private ImageView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_result_activity);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_financial_checkup);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();

        condition = bundle.getString("condition");
        result = bundle.getDoubleArray("result");

        // determine the indicator bar
        indicator = (ImageView) findViewById(R.id.fcresult_img_indicator);
        if (condition.equals("Bad")) {
            indicator.setImageResource(R.drawable.img_indicator_bad);
        } else if (condition.equals("Warning")) {
            indicator.setImageResource(R.drawable.img_indicator_warning);
        } else if (condition.equals("Good")) {
            indicator.setImageResource(R.drawable.img_indicator_good);
        } else if (condition.equals("Excellent")) {
            indicator.setImageResource(R.drawable.img_indicator_excellent);
        }

        // set currency symbol
        DecimalFormat indonesianRp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        indonesianRp.setMaximumFractionDigits(0);
        DecimalFormatSymbols rp = new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        indonesianRp.setDecimalFormatSymbols(rp);

        balanceIdeal = result[0];
        savingsIdeal = result[1];
        instalmentIdeal = result[2];

        TextView fcresultTextCondition = (TextView) findViewById(R.id.fcresult_text_condition);
        fcresultTextCondition.setText(condition);

        TextView fcresultTextBalance = (TextView) findViewById(R.id.fcresult_text_balance);
        fcresultTextBalance.setText(indonesianRp.format(balanceIdeal));

        TextView fcresultTextSavings = (TextView) findViewById(R.id.fcresult_text_savings);
        fcresultTextSavings.setText(indonesianRp.format(savingsIdeal));

        TextView fcresultTextInstalment = (TextView) findViewById(R.id.fcresult_text_instalment);
        fcresultTextInstalment.setText(indonesianRp.format(instalmentIdeal));

        // save button function
        Button fcresultButtonSave = (Button) findViewById(R.id.fcresult_button_save);
        fcresultButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(FcResultAlt.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
