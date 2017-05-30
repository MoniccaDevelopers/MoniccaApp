package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    /*
    // variables
    EditText signupEdittextEmail,
            signupEdittextPassword,
            signupEdittextIncome;

    String signupStringEmail,
            signupStringPassword,
            signupStringIncome;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signup_activity);

        // sign up button function
        Button signUpButtonSignUp = (Button) findViewById(R.id.signup_button_signup);
        signUpButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Will be available on next implementation.\nPlease use our Demo instead.", Toast.LENGTH_SHORT).show();

                /*
                signupEdittextEmail = (EditText) findViewById(R.id.signup_edittext_email);

                signupEdittextPassword = (EditText) findViewById(R.id.signup_edittext_password);

                signupEdittextIncome = (EditText) findViewById(R.id.signup_edittext_income);

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
                Intent intent = new Intent(Signup.this, Main.class);
                intent.putExtra("email", signupStringEmail);
                intent.putExtra("password", signupStringPassword);
                intent.putExtra("income", signupStringIncome);
                startActivity(intent);
                */
            }
        });

        // sign in button
        TextView signUpButtonSignIn = (TextView) findViewById(R.id.signup_button_signin);
        signUpButtonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Signin.class);
                startActivity(intent);
            }
        });
    }

    // hide the soft keyboard when user touch other place
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}