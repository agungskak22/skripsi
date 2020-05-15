package intant.call.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intant.call.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumeCaloryFragment extends Fragment {

    public ConsumeCaloryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consume_calory, container, false);
    }
}
