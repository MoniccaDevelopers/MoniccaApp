package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpStart extends AppCompatActivity {
    // variables
    EditText epstartEdittextApplicantname,
            epstartEdittextApplicantage,
            epstartEdittextInstitutionname;

    ArrayAdapter<CharSequence> adapter;

    Spinner epstartSpinnerEducationlevel;

    String epstartStringApplicantname,
            epstartStringApplicantage,
            epstartStringInstitutionname,
            epstartStringEducationlevel;

    double epstartDoubleApplicantage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_start_activity);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_education_plan);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        epstartEdittextApplicantname = (EditText) findViewById(R.id.epstart_edittext_applicantname);
        epstartEdittextApplicantage = (EditText) findViewById(R.id.epstart_edittext_applicantage);
        epstartEdittextInstitutionname = (EditText) findViewById(R.id.epstart_edittext_institutionname);

        // spinner
        epstartSpinnerEducationlevel = (Spinner) findViewById(R.id.epstart_spinner_educationlevel);
        adapter = ArrayAdapter.createFromResource(this, R.array.edulevel_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        epstartSpinnerEducationlevel.setAdapter(adapter);
        epstartSpinnerEducationlevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });

        Button epstartButtonNext = (Button) findViewById(R.id.epstart_button_next);
        epstartButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                epstartStringApplicantname = epstartEdittextApplicantname.getText().toString();
                epstartStringApplicantage = epstartEdittextApplicantage.getText().toString().trim();
                epstartStringInstitutionname = epstartEdittextInstitutionname.getText().toString();
                epstartStringEducationlevel = epstartSpinnerEducationlevel.getSelectedItem().toString().trim();

                // fields check
                if (TextUtils.isEmpty(epstartStringApplicantname)) {
                    epstartEdittextApplicantname.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(epstartStringApplicantage)) {
                    epstartEdittextApplicantage.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(epstartStringInstitutionname)) {
                    epstartEdittextInstitutionname.setError("You can't leave this empty.");
                    return;
                }

                epstartDoubleApplicantage = Double.parseDouble(epstartStringApplicantage);

                // age and educational level check
                if (epstartDoubleApplicantage > 25) {
                    Toast.makeText(EpStart.this, "Applicant age can not more than 25", Toast.LENGTH_SHORT).show();
                    return;
                } else if (epstartStringEducationlevel.equals("Elementary School")) {
                    if (epstartDoubleApplicantage > 7) {
                        Toast.makeText(EpStart.this, "To attend elementary school. Age can not be more than 7", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("Middle School")) {
                    if (epstartDoubleApplicantage > 11) {
                        Toast.makeText(EpStart.this, "To attend middle school. Age can not be more than 11", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("High School")) {
                    if (epstartDoubleApplicantage > 14) {
                        Toast.makeText(EpStart.this, "To attend high school. Age can not be more than 14", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("College")) {
                    if (epstartDoubleApplicantage > 25) {
                        Toast.makeText(EpStart.this, "To attend college. Age can not be more than 25", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("applicantname", epstartStringApplicantname);
                bundle.putDouble("applicantage", epstartDoubleApplicantage);
                bundle.putString("institutionname", epstartStringInstitutionname);
                bundle.putString("educationlevel", epstartStringEducationlevel);

                Intent intent = new Intent(EpStart.this, EpCalculate.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
