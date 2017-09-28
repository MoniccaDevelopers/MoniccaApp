package id.astrajingga.monicca.features.gb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import id.astrajingga.monicca.R;
import id.astrajingga.monicca.adapter.ListViewGoalBaseAdapter;

/**
 * Created by Djaffar on 7/15/2017.
 */

public class GbMainAlt extends AppCompatActivity {

    // LIST VIEW IMAGES
    private int[] images = {
            R.drawable.ic_car,
            R.drawable.ic_bike,
            R.drawable.ic_marriage,
            R.drawable.ic_trip,
            R.drawable.ic_gadget
    };

    // LIST VIEW TITLES
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

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.toolbar_goal_base);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ListView listView = (ListView) findViewById(R.id.gbmain_listview);

        ListViewGoalBaseAdapter listViewGoalBaseAdapter = new ListViewGoalBaseAdapter(this, text, images);

        listView.setAdapter(listViewGoalBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
