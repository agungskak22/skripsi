package intant.call.MainFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import intant.call.LoginActivity;
import intant.call.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private MaterialButton mbtnLogout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initView(v);

        return v;
    }

    private void initView(View rootView) {
        mbtnLogout = rootView.findViewById(R.id.mbtn_logout);

        mbtnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mbtn_logout:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setTitle("Pemberitahuan");
                alertBuilder.setMessage("Keluar dari akun?");
                alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finishAffinity();
                    }
                });
                alertBuilder.setNegativeButton("Tidak", null);
                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();
                break;
        }
    }
}
