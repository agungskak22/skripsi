package intant.call.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import intant.call.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculateCaloryFragment extends Fragment {

    Spinner spinnerActivityLevel;

    public CalculateCaloryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculate_calory, container, false);

        initView(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner();
    }

    public void initView(View rootView) {
        spinnerActivityLevel = rootView.findViewById(R.id.spinner_activity_level);
    }

    public void setSpinner() {
        String[] listActivityLevel = {
                "Tidak Aktif (tidak ada berolahraga)",
                "Lumayan Aktif",
                "Aktif"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, listActivityLevel);
        spinnerActivityLevel.setAdapter(adapter);
    }
}
