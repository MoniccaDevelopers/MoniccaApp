package id.astrajingga.monicca.features.ep;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import id.astrajingga.monicca.R;
import id.astrajingga.monicca.features.fc.FragmentFcStart;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEpMain extends Fragment {


    public FragmentEpMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ep_main_fragment, container, false);

        // start button function
        Button epmainButtonStart = (Button) view.findViewById(R.id.epmain_button_start);
        epmainButtonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentEpStart fragmentEpStart = new FragmentEpStart();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentEpStart).addToBackStack("Main").commit();
            }
        });
        return view;
    }
}
