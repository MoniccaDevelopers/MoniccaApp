package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    // variables
    EditText signinEdittextEmail,
            signinEdittextPassword;

    String signinStringEmail,
            signinStringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signin_activity);

        // password visibility toggle
        TextInputLayout signinTextlayoutPassword = (TextInputLayout) findViewById(R.id.signin_textlayout_password);
        if (signinTextlayoutPassword.getEditText() != null) {
            signinTextlayoutPassword.getEditText();
        }

        // sign in button function
        Button signInButtonSignIn = (Button) findViewById(R.id.signin_button_signin);
        signInButtonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            signinEdittextEmail = (EditText) findViewById(R.id.signin_edittext_email);

            signinEdittextPassword = (EditText) findViewById(R.id.signin_edittext_password);

            signinStringEmail = signinEdittextEmail.getText().toString();

            signinStringPassword = signinEdittextPassword.getText().toString();

            // fields check
            if (TextUtils.isEmpty(signinStringEmail)) {
                signinEdittextEmail.setError("You can't leave this empty.");
                return;
            } else if (TextUtils.isEmpty(signinStringPassword)) {
                signinEdittextPassword.setError("You can't leave this empty.");
                return;
            } else if (!"listrik".equals(signinStringEmail)) {
                Toast.makeText(getApplicationContext(), "Wrong Email or Password.", Toast.LENGTH_SHORT).show();
                return;
            } else if (!"petir".equals(signinStringPassword)) {
                Toast.makeText(getApplicationContext(), "Wrong Email or Password.", Toast.LENGTH_SHORT).show();
                return;
            }

            // go to Main class if pass fields check
            Intent intent = new Intent(getApplicationContext(), Main.class);
            startActivity(intent);
            }
        });

        // sign up button function
        TextView signInButtonSignUp = (TextView) findViewById(R.id.signin_button_signup);
        signInButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LinearLayout signinField = (LinearLayout) findViewById(R.id.signin_field);
                signinField.setVisibility(View.GONE);
                FragmentSignup fragmentSignup = new FragmentSignup();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.signin_fragment_container, fragmentSignup).addToBackStack("Main").commit();
            }
        });

        // demo button function
        TextView signInButtonDemo = (TextView) findViewById(R.id.signin_button_demo);
        signInButtonDemo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
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
