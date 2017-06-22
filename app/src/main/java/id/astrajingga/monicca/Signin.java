package id.astrajingga.monicca;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import id.astrajingga.monicca.auth.AddressUrl;
import id.astrajingga.monicca.auth.AppController;
import id.astrajingga.monicca.auth.LoginRequest;
import id.astrajingga.monicca.auth.SQLiteHandler;
import id.astrajingga.monicca.auth.SessionManager;

public class Signin extends AppCompatActivity {
    // variables
    EditText signinEdittextEmail,
            signinEdittextPassword;

    String signinStringEmail,
            signinStringPassword,
            authChecker;

    //Facebook Declaration
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;

    //For Login Declaration
    private SessionManager session;
    private SQLiteHandler db;
    private ProgressDialog pDialog;
    private static final String TAG = FragmentSignup.class.getSimpleName();


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

        signinEdittextEmail = (EditText) findViewById(R.id.signin_edittext_email);
        signinEdittextPassword = (EditText) findViewById(R.id.signin_edittext_password);

        //login versi 2

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        /*if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Signin.this, Main.class);
            startActivity(intent);
            finish();
        }*/

       // sign in button function
        Button signInButtonSignIn = (Button) findViewById(R.id.signin_button_signin);
        signInButtonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            String email = signinEdittextEmail.getText().toString().trim();
            String password = signinEdittextPassword.getText().toString().trim();



            // fields check

            if (!email.isEmpty() && !password.isEmpty() ) {
                //signinEdittextEmail.setError("You can't leave this empty.");
                //checkLogin(signinStringEmail, signinStringPassword);
                checkLogin(email, password);
            } else {
               // signinEdittextPassword.setError("You can't leave this empty.");
                Toast.makeText(getApplicationContext(),
                        "Please enter the Email & Password", Toast.LENGTH_LONG)
                        .show();
            }
                //checkLogin(email, password);



            /* listrik petir auth

            else if (!"listrik".equals(signinStringEmail)) {
                Toast.makeText(getApplicationContext(), "Wrong Email or Password.", Toast.LENGTH_SHORT).show();
                return;
            } else if (!"petir".equals(signinStringPassword)) {
                Toast.makeText(getApplicationContext(), "Wrong Email or Password.", Toast.LENGTH_SHORT).show();
                return;
            }
            */
                // Login versi 1 - Response received from the server versi
                /*
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("name");

                                Intent intent = new Intent(Signin.this, Main.class);
                                intent.putExtra("name", name);
                                intent.putExtra("email", signinStringEmail);
                                Signin.this.startActivity(intent);

                                //authChecker = "signin";
                                //intent.putExtra("authchecker", authChecker);
                                //intent.putExtra("username", signinStringEmail);

                                SharedPreferences preferences = getSharedPreferences("FBPREF",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("emailfb", signinStringEmail );
                                editor.apply();

                                Toast.makeText(Signin.this, "Now Login With Register Email", Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signin.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                // go to Main class if pass fields check
                LoginRequest loginRequest = new LoginRequest(signinStringEmail, signinStringPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signin.this);
                queue.add(loginRequest);
                */
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
                authChecker = "demo";
                signinStringEmail = "Demo";
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.putExtra("authchecker", authChecker);
                intent.putExtra("username", signinStringEmail);
                startActivity(intent);
            }
        });

        //Facebook Processing Token
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));

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
        SharedPreferences preferences = getSharedPreferences("FBPREF",MODE_PRIVATE);
        String prefUsername = preferences.getString("emailfb", "");
        if (prefUsername!=""){
            Intent main = new Intent(Signin.this, Main.class);
            startActivity(main);
        }
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Signin.this, Main.class);
            startActivity(intent);
            finish();
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

    //Faceboot get data
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

    //Login via email versi 2
    private void checkLogin(final String email, final String password) {


        pDialog.setMessage("Logging in ...");
        showDialog();

        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AddressUrl.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        // Launch main activity
                        Intent intent = new Intent(Signin.this,
                                Main.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
