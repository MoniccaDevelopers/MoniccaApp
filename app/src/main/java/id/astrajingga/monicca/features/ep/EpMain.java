package id.astrajingga.monicca.features.ep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.astrajingga.monicca.R;
import id.astrajingga.monicca.features.fc.FcMain;
import id.astrajingga.monicca.features.fc.FcStart;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class EpMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ep_main_activity);

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
