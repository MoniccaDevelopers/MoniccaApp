package id.astrajingga.monicca.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment {
    // variables
    ExpandableTextView expandableTextView;


    public FragmentSettings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_settings_fragment, container, false);

        // credits
        String credits = "Credits\n\n" +
                "Copyright \u00a9 2017 Astrajingga Inovasi Digital";

        expandableTextView = (ExpandableTextView) view.findViewById(R.id.settings_expand_item);
        expandableTextView.setText(credits);

        /*
        // credits button function
        RelativeLayout credits = (RelativeLayout)view.findViewById(R.id.credits);
        credits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Icons made by Freepik from www.flaticon.com", Toast.LENGTH_LONG).show();
            }
        });
        */

        return view;
    }
}
