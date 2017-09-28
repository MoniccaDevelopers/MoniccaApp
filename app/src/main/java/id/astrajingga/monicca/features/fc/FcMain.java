package id.astrajingga.monicca.features.fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.astrajingga.monicca.R;

public class FcMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_main_activity);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_financial_checkup);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Start();
    }

    public void Start() {
        Button fcmainButtonStart = (Button) findViewById(R.id.fcmain_button_start);
        fcmainButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FcMain.this, FcStartAlt.class);
                startActivity(intent);
            }
        });
    }
}
