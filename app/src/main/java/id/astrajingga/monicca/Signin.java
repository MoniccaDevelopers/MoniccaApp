package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.Arrays;

public class Signin extends AppCompatActivity {
    // variables
    EditText signinEdittextEmail,
            signinEdittextPassword;

    String signinStringEmail,
            signinStringPassword;

    //Facebook Declaration
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
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

        //Facebook Processing

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };
        /*
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        */

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        /*FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);

                Toast.makeText(getApplicationContext(), "Logging in..." + loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);
        */

        // Facebook Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                setProfileToView(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
                Toast.makeText(Signin.this, "Now Login With Facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(Signin.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();

        //Facebook keep login
        //Profile profile = Profile.getCurrentProfile();
        //nextActivity(profile);
        SharedPreferences preferences = getSharedPreferences("FBPREF",MODE_PRIVATE);
        String prefemail = preferences.getString("emailfb", "");
        if (prefemail!=""){
            Intent main = new Intent(Signin.this, Main.class);
            startActivity(main);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
    }

    //Facebook Activity Callback
    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    //Facebook Function
    /*
    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(Signin.this, Main.class);

            String name = profile.getName().toString();
            String id   = profile.getId();

            SharedPreferences preferences = getSharedPreferences("FBPREF",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("namefb", name );
            editor.putString("idfb", id );
            editor.apply();

            startActivity(main);
        }
    }
    */

    private void setProfileToView(JSONObject jsonObject) {
        try {
            Intent main = new Intent(Signin.this, Main.class);

            String name     = jsonObject.getString("name").toString();
            String email    = jsonObject.getString("email").toString();
            String gender   = jsonObject.getString("gender").toString();

            SharedPreferences preferences = getSharedPreferences("FBPREF",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("namefb", name );
            editor.putString("emailfb", email );
            editor.putString("genderfb", gender );
            editor.apply();

            startActivity(main);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
