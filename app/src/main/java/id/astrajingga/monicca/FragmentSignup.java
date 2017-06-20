package id.astrajingga.monicca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FragmentSignup extends Fragment {
    // variables
    EditText signupEdittextEmail,
            signupEdittextPassword,
            signupEdittextIncome;

    String signupStringEmail,
            signupStringPassword,
            signupStringIncome,
            authChecker;

    public FragmentSignup() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_signup_fragment, container, false);

        // visibility toggles
        TextInputLayout signupTextlayoutPassword = (TextInputLayout)view.findViewById(R.id.signup_textlayout_password);
        TextInputLayout signupTextlayoutIncome = (TextInputLayout)view.findViewById(R.id.signup_textlayout_income);
        if (signupTextlayoutPassword.getEditText() != null) {
            signupTextlayoutPassword.getEditText();
        }

        if (signupTextlayoutIncome.getEditText() != null) {
            signupTextlayoutIncome.getEditText();
        }

        signupEdittextEmail = (EditText) view.findViewById(R.id.signup_edittext_email);

        signupEdittextPassword = (EditText) view.findViewById(R.id.signup_edittext_password);

        signupEdittextIncome = (EditText) view.findViewById(R.id.signup_edittext_income);
        signupEdittextIncome.addTextChangedListener(TextwatcherIncome());

        // sign up button function
        Button signUpButtonSignUp = (Button) view.findViewById(R.id.signup_button_signup);
        signUpButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                signupStringEmail = signupEdittextEmail.getText().toString();

                signupStringPassword = signupEdittextPassword.getText().toString();

                signupStringIncome = signupEdittextIncome.getText().toString();

                if (TextUtils.isEmpty(signupStringEmail)) {
                    signupEdittextEmail.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(signupStringPassword)) {
                    signupEdittextPassword.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(signupStringIncome)) {
                    signupEdittextIncome.setError("You can't leave this empty.");
                    return;
                }

                // go to Main class if pass fields check
                authChecker = "signup";
                Intent intent = new Intent(getActivity(), Main.class);
                intent.putExtra("authchecker", authChecker);
                intent.putExtra("username", signupStringEmail);
                intent.putExtra("password", signupStringPassword);
                intent.putExtra("income", signupStringIncome);
                startActivity(intent);
            }
        });

        // sign in button
        TextView signUpButtonSignIn = (TextView) view.findViewById(R.id.signup_button_signin);
        signUpButtonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), Signin.class);
                startActivity(intent);
            }
        });

        return view;
    }

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
                signupEdittextIncome.removeTextChangedListener(this);

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
                    signupEdittextIncome.setText(formattedString);
                    signupEdittextIncome.setSelection(signupEdittextIncome.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                signupEdittextIncome.addTextChangedListener(this);
            }
        };
    }
}