package id.astrajingga.monicca.features.fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/14/2017.
 */

public class FcStartAlt extends AppCompatActivity {
    private EditText balanceEdit;
    private EditText incomeEdit;
    private EditText savingsEdit;
    private EditText instalmentEdit;

    private double balance;
    private String balanceString;

    private double income;
    private String incomeString;

    private double savings;
    private String savingsString;

    private double instalment;
    private String instalmentString;

    private int parameter;
    private double[] result;
    private String condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_start_activity);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_financial_checkup);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        balanceEdit = (EditText) findViewById(R.id.fcstart_edittext_balance);
        balanceEdit.addTextChangedListener(TextwatcherbalanceEdit());

        incomeEdit = (EditText) findViewById(R.id.fcstart_edittext_income);
        incomeEdit.addTextChangedListener(TextwatcherIncome());

        savingsEdit = (EditText) findViewById(R.id.fcstart_edittext_savings);
        savingsEdit.addTextChangedListener(TextwatcherSavings());

        instalmentEdit = (EditText) findViewById(R.id.fcstart_edittext_instalment);
        instalmentEdit.addTextChangedListener(TextwatcherInstalment());

        Calculate();
    }

    public void Calculate() {
        Button calculate = (Button) findViewById(R.id.fcstart_button_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceString = balanceEdit.getText().toString().trim().replaceAll(",", "");
                incomeString = incomeEdit.getText().toString().trim().replaceAll(",", "");
                savingsString = savingsEdit.getText().toString().trim().replaceAll(",", "");
                instalmentString = instalmentEdit.getText().toString().trim().replaceAll(",", "");

                // fields check
                if (TextUtils.isEmpty(balanceString)) {
                    balanceEdit.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(incomeString)) {
                    incomeEdit.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(savingsString)) {
                    savingsEdit.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(instalmentString)) {
                    instalmentEdit.setError("You can't leave this empty.");
                    return;
                }

                FcFormula fcFormula = new FcFormula();
                balance = Double.valueOf(balanceString);
                income = Double.valueOf(incomeString);
                savings = Double.valueOf(savingsString);
                instalment = Double.valueOf(instalmentString);

                parameter = fcFormula.ParameterCheck(balance, income, savings, instalment);

                if (parameter == 3) {
                    condition = "Excellent";
                } else if (parameter == 2) {
                    condition = "Good";
                } else if (parameter == 1) {
                    condition = "Warning";
                } else if (parameter == 0) {
                    condition = "Bad";
                }

                result = fcFormula.IdealResult(income, instalment);

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("condition", condition);
                bundle.putDoubleArray("result", result);

                Intent intent = new Intent(FcStartAlt.this, FcResultAlt.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // balanceEdit EditText TextWatcher
    private TextWatcher TextwatcherbalanceEdit() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                balanceEdit.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to balanceEdit
                    balanceEdit.setText(formattedString);
                    balanceEdit.setSelection(balanceEdit.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                balanceEdit.addTextChangedListener(this);
            }

        };
    }

    // income EditText TextWatcher
    private TextWatcher TextwatcherIncome() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                incomeEdit.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to balanceEdit
                    incomeEdit.setText(formattedString);
                    incomeEdit.setSelection(incomeEdit.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                incomeEdit.addTextChangedListener(this);
            }
        };
    }

    // savings EditText TextWatcher
    private TextWatcher TextwatcherSavings() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                savingsEdit.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to balanceEdit
                    savingsEdit.setText(formattedString);
                    savingsEdit.setSelection(savingsEdit.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                savingsEdit.addTextChangedListener(this);
            }
        };
    }

    // instalment EditText TextWatcher
    private TextWatcher TextwatcherInstalment() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                instalmentEdit.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    // setting text after format to balanceEdit
                    instalmentEdit.setText(formattedString);
                    instalmentEdit.setSelection(instalmentEdit.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                instalmentEdit.addTextChangedListener(this);
            }
        };
    }
}