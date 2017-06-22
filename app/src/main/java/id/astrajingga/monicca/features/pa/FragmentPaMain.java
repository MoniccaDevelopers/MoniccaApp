package id.astrajingga.monicca.features.pa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 6/19/2017.
 */

public class FragmentPaMain extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pa_main_fragment, container, false);

        return view;
    }
}
