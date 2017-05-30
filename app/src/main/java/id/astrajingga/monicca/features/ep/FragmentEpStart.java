package id.astrajingga.monicca.features.ep;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEpStart extends Fragment {
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

    public FragmentEpStart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.ep_start_fragment, container, false);

        epstartEdittextApplicantname = (EditText)view.findViewById(R.id.epstart_edittext_applicantname);
        epstartEdittextApplicantage = (EditText)view.findViewById(R.id.epstart_edittext_applicantage);
        epstartEdittextInstitutionname = (EditText)view.findViewById(R.id.epstart_edittext_institutionname);

        // spinner
        epstartSpinnerEducationlevel = (Spinner)view.findViewById(R.id.epstart_spinner_educationlevel);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.edulevel_list, android.R.layout.simple_spinner_item);
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

        Button epstartButtonNext = (Button)view.findViewById(R.id.epstart_button_next);
        epstartButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                epstartStringApplicantname = epstartEdittextApplicantname.getText().toString();
                epstartStringApplicantage = epstartEdittextApplicantage.getText().toString();
                epstartStringInstitutionname = epstartEdittextInstitutionname.getText().toString();
                epstartStringEducationlevel = epstartSpinnerEducationlevel.getSelectedItem().toString();

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
                    Toast.makeText(getContext(), "Applicant age can not more than 25", Toast.LENGTH_SHORT).show();
                    return;
                } else if (epstartStringEducationlevel.equals("Elementary School")) {
                    if (epstartDoubleApplicantage > 7) {
                        Toast.makeText(getContext(), "To attend elementary school. Age can not be more than 7", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("Middle School")) {
                    if (epstartDoubleApplicantage > 11) {
                        Toast.makeText(getContext(), "To attend middle school. Age can not be more than 11", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("High School")) {
                    if (epstartDoubleApplicantage > 14) {
                        Toast.makeText(getContext(), "To attend high school. Age can not be more than 14", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (epstartStringEducationlevel.equals("College")) {
                    if (epstartDoubleApplicantage > 25) {
                        Toast.makeText(getContext(), "To attend college. Age can not be more than 25", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // declare a bundle and fill it with variables
                Bundle bundle = new Bundle();
                bundle.putString("applicantname", epstartStringApplicantname);
                bundle.putDouble("applicantage", epstartDoubleApplicantage);
                bundle.putString("institutionname", epstartStringInstitutionname);
                bundle.putString("educationlevel", epstartStringEducationlevel);

                // declare FragmentManager to call fragmentEpResult
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentEpCalculate fragmentEpCalculate = new FragmentEpCalculate();
                fragmentEpCalculate.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragmentEpCalculate).addToBackStack("Main");
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
