package id.astrajingga.monicca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import id.astrajingga.monicca.auth.AddressUrl;
import id.astrajingga.monicca.auth.AppController;
import id.astrajingga.monicca.auth.SQLiteHandler;
import id.astrajingga.monicca.auth.SessionManager;

public class FragmentSignup extends Fragment {
    // variables
    EditText signupEdittextEmail,
            signupEdittextPassword,
            signupEdittextIncome;

    String signupStringEmail,
            signupStringPassword,
            signupStringIncome,
            authChecker;

    //For Login Declaration
    private SessionManager session;
    private SQLiteHandler db;
    private ProgressDialog pDialog;
    private static final String TAG = FragmentSignup.class.getSimpleName();

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


        //Loading
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        // Session manager
        session = new SessionManager(getActivity());

        // SQLite database handler
        db = new SQLiteHandler(getActivity());

        // sign up button function
        Button signUpButtonSignUp = (Button) view.findViewById(R.id.signup_button_signup);
        signUpButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                signupStringEmail = signupEdittextEmail.getText().toString().trim();
                signupStringPassword = signupEdittextPassword.getText().toString().trim();
                signupStringIncome = signupEdittextIncome.getText().toString().trim();

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
                String namaa = "user v1";
                registerUser(namaa, signupStringEmail, signupStringPassword);

                // go to Main class if pass fields check
                /*authChecker = "signup";
                Intent intent = new Intent(getActivity(), Main.class);
                intent.putExtra("authchecker", authChecker);
                intent.putExtra("username", signupStringEmail);
                intent.putExtra("password", signupStringPassword);
                intent.putExtra("income", signupStringIncome);
                startActivity(intent);*/
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


    private void registerUser(final String name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

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

                        Toast.makeText(getActivity(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                getActivity(),
                                Signin.class);
                        startActivity(intent);
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(),
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
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
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