package id.astrajingga.monicca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Djaffar on 7/4/2017.
 */

public class SignupActivity extends AppCompatActivity {

    // VARIABLES
    EditText signupEdittextEmail,
            signupEdittextPassword;

    String signupStringEmail,
            signupStringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signup_activity);

        // PASSWORD VISIBILITY TOOGLE
        TextInputLayout signupTextlayoutPassword = (TextInputLayout) findViewById(R.id.signup_textlayout_password);
        if (signupTextlayoutPassword.getEditText() != null) {
            signupTextlayoutPassword.getEditText();
        }

        signupEdittextEmail = (EditText) findViewById(R.id.signup_edittext_email);
        signupEdittextPassword = (EditText) findViewById(R.id.signup_edittext_password);

        signupBtn();

        signinBtn();
    }

    // SIGN UP BUTTON FUNCTION
    public void signupBtn() {
        Button btnSignup = (Button) findViewById(R.id.signup_button_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupStringEmail = signupEdittextEmail.getText().toString().trim();
                signupStringPassword = signupEdittextPassword.getText().toString().trim();

                // FIELDS CHECK
                if (TextUtils.isEmpty(signupStringEmail)) {
                    signupEdittextEmail.setError("You can't leave this empty.");
                } else if (TextUtils.isEmpty(signupStringPassword)) {
                    signupEdittextPassword.setError("You can't leave this empty.");
                }

            }
        });
    }

    // SIGN IN BUTTON FUNCTION
    public void signinBtn() {
        TextView signinBtn = (TextView) findViewById(R.id.signup_button_signin);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}
