package id.astrajingga.monicca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.astrajingga.monicca.auth.AddressUrl;
import id.astrajingga.monicca.auth.AppController;
import id.astrajingga.monicca.auth.SQLiteHandler;
import id.astrajingga.monicca.auth.SessionManager;

/**
 * Created by Djaffar on 7/4/2017.
 */

public class Signup extends AppCompatActivity {
    // variables
    EditText signupEdittextEmail,
            signupEdittextPassword;

    String signupStringEmail,
            signupStringPassword;

    //For Register Declaration
    private SessionManager session;
    private SQLiteHandler db;
    private ProgressDialog pDialog;
    private static final String TAG = Signup.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signup_activity);

        // password visibility toggles
        TextInputLayout signupTextlayoutPassword = (TextInputLayout) findViewById(R.id.signup_textlayout_password);
        if (signupTextlayoutPassword.getEditText() != null) {
            signupTextlayoutPassword.getEditText();
        }

        signupEdittextEmail = (EditText) findViewById(R.id.signup_edittext_email);
        signupEdittextPassword = (EditText) findViewById(R.id.signup_edittext_password);

        //Loading
        pDialog = new ProgressDialog(Signup.this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(Signup.this);

        // SQLite database handler
        db = new SQLiteHandler(Signup.this);

        signupBtn();
        signinBtn();
    }

    public void signupBtn() {
        Button btnSignup = (Button) findViewById(R.id.signup_button_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupStringEmail = signupEdittextEmail.getText().toString().trim();
                signupStringPassword = signupEdittextPassword.getText().toString().trim();

                // fields check
                if (TextUtils.isEmpty(signupStringEmail)) {
                    signupEdittextEmail.setError("You can't leave this empty.");
                    return;
                } else if (TextUtils.isEmpty(signupStringPassword)) {
                    signupEdittextPassword.setError("You can't leave this empty.");
                    return;
                }

                //give auto name todatabase for release
                String nama = "user v1";
                registerUser(nama, signupStringEmail, signupStringPassword);

            }
        });
    }

    public void signinBtn() {
        TextView signinBtn = (TextView) findViewById(R.id.signup_button_signin);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Signup.this, Signin.class);
                startActivity(intent);
            }
        });
    }

    //Registering Proccess
    private void registerUser(final String name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        //view Loding interface
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AddressUrl.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        session.setLogin(true);
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        Toast.makeText(Signup.this, "User successfully registered. Please sign in.", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                Signup.this,
                                Main.class);
                        startActivity(intent);
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(Signup.this,
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(Signup.this,
                        "Connection Error.\nPlease check your internet connection.", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
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
