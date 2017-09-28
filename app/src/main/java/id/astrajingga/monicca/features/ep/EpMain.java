package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_main_activity);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_education_plan);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Start();
    }

    public void Start() {
        Button epmainButtonStart = (Button) findViewById(R.id.epmain_button_start);
        epmainButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EpMain.this, EpStart.class);
                startActivity(intent);
            }
        });
    }
}
