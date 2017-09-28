package id.astrajingga.monicca.features.pa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.astrajingga.monicca.R;

/**
 * Created by Admin on 8/7/2017.
 */

public class PaQuestionThree extends AppCompatActivity {
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pa_questionthree_activity);

        btnNext = (Button) findViewById(R.id.btn_next);

        Next();
    }

    private void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaQuestionThree.this, PaQuestionFour.class);
                startActivity(intent);
            }
        });
    }
}
