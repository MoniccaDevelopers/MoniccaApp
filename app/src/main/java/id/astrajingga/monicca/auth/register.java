package id.astrajingga.monicca.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import id.astrajingga.monicca.R;

public class register extends AppCompatActivity {

    EditText e_name, e_password, e_phone, e_email;
    String name,password,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_register);

        e_name = (EditText) findViewById(R.id.name);
        e_password = (EditText) findViewById(R.id.password);
        e_phone = (EditText) findViewById(R.id.phone);
        e_email = (EditText) findViewById(R.id.email);

    }

    public void reguser(View view) {
        name = e_name.getText().toString();
        password = e_password.getText().toString();
        phone = e_phone.getText().toString();
        email = e_email.getText().toString();
        String method="register";
        register_process register_process = new register_process(this);
        register_process.execute(method,name,password,phone,email);
        finish();
    }
}
