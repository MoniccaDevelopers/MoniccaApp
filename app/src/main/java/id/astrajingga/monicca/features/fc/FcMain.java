package id.astrajingga.monicca.features.fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/14/2017.
 */

public class FcMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_main_activity);

        Start();
    }

    public void Start() {
        Button fcmainButtonStart = (Button) findViewById(R.id.fcmain_button_start);
        fcmainButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FcMain.this, FcStart.class);
                startActivity(intent);
            }
        });
    }
}
