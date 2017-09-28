package id.astrajingga.monicca.features.pa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.astrajingga.monicca.R;

/**
 * Created by Admin on 8/1/2017.
 */

public class PaQuestionTwo extends AppCompatActivity{
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pa_questiontwo_activity);

        btnNext = (Button) findViewById(R.id.btn_next);

        Next();
    }

    public void Next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaQuestionTwo.this, PaQuestionThree.class);
                startActivity(intent);
            }
        });
    }
}
