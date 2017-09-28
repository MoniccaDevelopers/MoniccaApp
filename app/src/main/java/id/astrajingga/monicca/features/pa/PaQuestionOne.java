package id.astrajingga.monicca.features.pa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class PaQuestionOne extends AppCompatActivity {
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pa_questionone_activity);

        btnNext = (Button) findViewById(R.id.btn_next);

        Next();
    }

    public void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaQuestionOne.this, PaQuestionTwo.class);
                startActivity(intent);
            }
        });
    }
}
