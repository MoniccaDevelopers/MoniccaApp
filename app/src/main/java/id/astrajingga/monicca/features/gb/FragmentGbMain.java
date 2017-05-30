package id.astrajingga.monicca.features.gb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.astrajingga.monicca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGbMain extends Fragment {


    public FragmentGbMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gb_main_fragment, container, false);

        Button gbmainButtonStart = (Button)view.findViewById(R.id.gbmain_button_start);
        gbmainButtonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentGbStart fragmentGbStart = new FragmentGbStart();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentGbStart).addToBackStack("Main").commit();
            }
        });

        return view;
    }
}
