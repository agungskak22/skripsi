package intant.call;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import intant.call.MainFragments.CalculateCaloryFragment;
import intant.call.MainFragments.ConsumeCaloryFragment;
import intant.call.MainFragments.HomeFragment;
import intant.call.MainFragments.ListCaloryFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FrameLayout flFragmentContainer;
    private BottomNavigationView bottomNavigationView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        initView(v);

        return v;
    }

    private void initView(View rootView) {
        flFragmentContainer = rootView.findViewById(R.id.fl_fragment_container);
        bottomNavigationView = rootView.findViewById(R.id.bottom_nav_view);

        // first fragment view
        switchFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        switchFragment(new HomeFragment());
                        return true;
                    case R.id.menu_calculate_calory:
                        switchFragment(new CalculateCaloryFragment());
                        return true;
                    case R.id.menu_consume_calory:
                        switchFragment(new ConsumeCaloryFragment());
                        return true;
                    case R.id.menu_list_calory:
                        switchFragment(new ListCaloryFragment());
                        return true;
                }
                return false;
            }
        });
    }

    private void switchFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment_container, newFragment);
        fragmentTransaction.commit();
    }
}
