package id.astrajingga.monicca.features.fc;


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
public class FragmentFcMain extends Fragment {


    public FragmentFcMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fc_main_fragment, container, false);

        // start button function
        Button fcmainButtonStart = (Button)view.findViewById(R.id.fcmain_button_start);
        fcmainButtonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentFcStart fragmentFcStart = new FragmentFcStart();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentFcStart).addToBackStack("Main").commit();
            }
        });

        return view;
    }
}
