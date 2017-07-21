package id.astrajingga.monicca.features.gb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class GbStartAlt extends AppCompatActivity {
    private String object;
    private EditText objectName;
    private EditText objectPrice;
    private EditText objectDeadline;
    private Spinner objectTime;
    private ArrayAdapter<CharSequence> adapterTime;

    private String name;
    private String priceString;
    private double price;
    private String deadlineString;
    private double deadline;
    private String time;
    private double[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gb_start_activity_alt);

        Intent intent = getIntent();
        object = intent.getStringExtra("object");

        // state object type
        TextView gbstartTextObject = (TextView) findViewById(R.id.gbstart_text_objecttype);
        gbstartTextObject.setText(object);

        // state the object price rate
        TextView gbstartTextRate = (TextView) findViewById(R.id.gbstart_text_rate);
        TextView gbstartTextRatetitle = (TextView) findViewById(R.id.gbstart_text_ratetitle);
        switch (object) {
            case "Car":
                gbstartTextRatetitle.setText(R.string.gb_text_car_rate);
                gbstartTextRate.setText(R.string.gb_text_car);
                break;
            case "Bike":
                gbstartTextRatetitle.setText(R.string.gb_text_bike_rate);
                gbstartTextRate.setText(R.string.gb_text_bike);
                break;
            case "Wedding":
                gbstartTextRatetitle.setText(R.string.gb_text_wedding_rate);
                gbstartTextRate.setText(R.string.gb_text_wedding);
                break;
            case "Trip":
                gbstartTextRatetitle.setText(R.string.gb_text_trip_rate);
                gbstartTextRate.setText(R.string.gb_text_trip);
                break;
            default:
                gbstartTextRatetitle.setText(R.string.gb_text_gadget_rate);
                gbstartTextRate.setText(R.string.gb_text_gadget);
                break;
        }

        objectName = (EditText) findViewById(R.id.gbstart_edittext_objectname);
        objectPrice = (EditText) findViewById(R.id.gbstart_edittext_objectprice);
        objectPrice.addTextChangedListener(TextwatcherObjectprice());
        objectDeadline = (EditText) findViewById(R.id.gbstart_edittext_timevalue);
        objectTime = (Spinner) findViewById(R.id.gbstart_spinner_time);
        adapterTime = ArrayAdapter.createFromResource(this, R.array.time_list, android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        objectTime.setAdapter(adapterTime);

        Calculate();
    }

    public void Calculate() {
        Button calculate = (Button) findViewById(R.id.gbstart_button_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = objectName.getText().toString();
                priceString = objectPrice.getText().toString().trim().replaceAll(",", "");
                deadlineString = objectDeadline.getText().toString().trim();

                // fields check
                if (TextUtils.isEmpty(name)) {
                    objectName.setError("You can't leave this empty.");
                }

                if (TextUtils.isEmpty(priceString)) {
                    objectPrice.setError("You can't leave this empty.");
                }

                if (TextUtils.isEmpty(deadlineString)) {
                    objectDeadline.setError("You can't leave this empty.");
                    return;
                }

                FormulaMonth formulaMonth = new FormulaMonth();
                FormulaYear formulaYear = new FormulaYear();
                price = Double.valueOf(priceString);
                deadline = Double.valueOf(deadlineString);
                time = objectTime.getSelectedItem().toString();

                switch (object) {
                    case "Car":
                        if (time.equals("Month")) {
                            result = formulaMonth.Car(price, deadline);
                        } else {
                            result = formulaYear.Car(price, deadline);
                        }
                        break;
                    case "Bike":
                        if (time.equals("Month")) {
                            result = formulaMonth.Bike(price, deadline);
                        } else {
                            result = formulaYear.Bike(price, deadline);
                        }
                        break;
                    case "Wedding":
                        if (time.equals("Month")) {
                            result = formulaMonth.Wedding(price, deadline);
                        } else {
                            result = formulaYear.Wedding(price, deadline);
                        }
                        break;
                    case "Trip":
                        if (time.equals("Month")) {
                            result = formulaMonth.Trip(price, deadline);
                        } else {
                            result = formulaYear.Trip(price, deadline);
                        }
                        break;
                    default:
                        if (time.equals("Month")) {
                            result = formulaMonth.Gadget(price, deadline);
                        } else {
                            result = formulaYear.Gadget(price, deadline);
                        }
                        break;
                }

                Bundle bundle = new Bundle();

                bundle.putString("object", object);
                bundle.putString("name", name);
                bundle.putString("time", time);
                bundle.putDouble("price", price);
                bundle.putDouble("deadline", deadline);
                bundle.putDoubleArray("result", result);

                Intent intent =  new Intent(GbStartAlt.this, GbResultAlt.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // objectprice EditText TextWatcher
    private TextWatcher TextwatcherObjectprice() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                objectPrice.removeTextChangedListener(this);

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

                    // setting text after format to fcstartEdittextBalance
                    objectPrice.setText(formattedString);
                    objectPrice.setSelection(objectPrice.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                objectPrice.addTextChangedListener(this);
            }
        };
    }
}
