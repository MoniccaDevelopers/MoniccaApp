package id.astrajingga.monicca.features.gb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class GbMainAlt extends AppCompatActivity {

    // references to our images
    private int[] images = {
            R.drawable.gb_btn_car,
            R.drawable.gb_btn_bike,
            R.drawable.gb_btn_wedding,
            R.drawable.gb_btn_trip,
            R.drawable.gb_btn_gadget
    };

    // references to our text
    private String[] text = {
            "Car",
            "Bike",
            "Wedding",
            "Trip",
            "Gadget"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gb_main_activity_alt);

        GridView gridView = (GridView) findViewById(R.id.gbmain_gridview);

        GridAdapter gridAdapter = new GridAdapter(this, text, images);

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent(GbMainAlt.this, GbStartAlt.class);
                    intent.putExtra("object", text[position]);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(GbMainAlt.this, GbStartAlt.class);
                    intent.putExtra("object", text[position]);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(GbMainAlt.this, GbStartAlt.class);
                    intent.putExtra("object", text[position]);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(GbMainAlt.this, GbStartAlt.class);
                    intent.putExtra("object", text[position]);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(GbMainAlt.this, GbStartAlt.class);
                    intent.putExtra("object", text[position]);
                    startActivity(intent);
                }
            }
        });
    }
}
