package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.astrajingga.monicca.intro.IntroActivity;

public class SigninActivity extends AppCompatActivity {

    // VARIABLES
    EditText signinEdittextEmail,
            signinEdittextPassword;

    String signinStringEmail,
            signinStringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signin_activity);

        // CHECK IF APP IS ON FIRST RUN OR NOT
        onFirstRun();

        // PASSWORD VISIBILITY TOOGLE
        TextInputLayout signinTextlayoutPassword = (TextInputLayout) findViewById(R.id.signin_textlayout_password);
        if (signinTextlayoutPassword.getEditText() != null) {
            signinTextlayoutPassword.getEditText();
        }

        signinEdittextEmail = (EditText) findViewById(R.id.signin_edittext_email);
        signinEdittextPassword = (EditText) findViewById(R.id.signin_edittext_password);

        // SIGN IN BUTTON FUNCTION
        Button signInButtonSignIn = (Button) findViewById(R.id.signin_button_signin);
        signInButtonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                signinStringEmail = signinEdittextEmail.getText().toString().trim();
                signinStringPassword = signinEdittextPassword.getText().toString().trim();

                // FIELDS CHECK
                if (TextUtils.isEmpty(signinStringEmail)) {
                    signinEdittextEmail.setError("You can't leave this empty.");
                } else if (TextUtils.isEmpty(signinStringPassword)) {
                    signinEdittextPassword.setError("You can't leave this empty.");
                }

                // STATIC USER AUTH
                else if (!signinStringEmail.equals("listrik") && !signinStringPassword.equals("petir")) {
                    Toast.makeText(SigninActivity.this, "Incorrect E-mail or Password.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // SIGN UP BUTTON FUNCTION
        TextView signInButtonSignUp = (TextView) findViewById(R.id.signin_button_signup);
        signInButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                // startActivity(intent);

                Toast.makeText(SigninActivity.this, "Will be available on the next implementation, \n" +
                                "Please try our demo instead.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // DEMO BUTTON FUNCTION
        TextView signInButtonDemo = (TextView) findViewById(R.id.signin_button_demo);
        signInButtonDemo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void onFirstRun() {

        // PREFERENCE NAME
        String PREF_NAME = "introPref";

        // APP VERSION CODE
        String VERSION_CODE_KEY = "5";

        int DOESNT_EXIST = 0;

        // GET APP CURRENT VERSION CODE
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // GET APP SAVED VERSION CODE
        SharedPreferences preferences =
                getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int savedVersionCode = preferences.getInt(VERSION_CODE_KEY, DOESNT_EXIST);

        // CHECK IS THIS A FIRST RUN OR UPDATE
        if (currentVersionCode == savedVersionCode) {

            // THIS IS A NORMAL RUN

            // IF SAVED VERSION CODE IS NULL
        } else if (savedVersionCode == DOESNT_EXIST) {

            // THIS IS A FIRST RUN OR THE USER CLEARED THE SHARED PREFERENCES
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
            preferences.edit().putInt(VERSION_CODE_KEY, currentVersionCode)
                    .apply();


            // IF CURRENT VERSION CODE IS HIGHER THAN SAVED VERSION CODE
        } else if (currentVersionCode > savedVersionCode) {

            // THIS IS AN UPDATE

        }
    }


    // HIDE THE SOFT KEYBOARD WHEN USER TOUCH OTHER PLACE
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
