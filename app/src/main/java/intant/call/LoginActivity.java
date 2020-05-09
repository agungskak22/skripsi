package intant.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    private TextView tvRegister;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private MaterialButton mbtnLogin;
    private FrameLayout flProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        initView();
    }

    private void initView() {
        tvRegister = findViewById(R.id.tv_register);
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        tietEmail = findViewById(R.id.tiet_email);
        tietPassword = findViewById(R.id.tiet_password);
        flProgressBar = findViewById(R.id.fl_progress_bar);
        mbtnLogin = findViewById(R.id.button_login);

        tvRegister.setOnClickListener(this);
        mbtnLogin.setOnClickListener(this);

        tietEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        tietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
                break;
        }
    }

    private void login() {
        if (!isValidForm()) return;

        flProgressBar.setVisibility(View.VISIBLE);

        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Bisa", Toast.LENGTH_LONG).show();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Tidak bisa membuat akun. Check email dan password kamu", Toast.LENGTH_LONG).show();
                        }
                        flProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private boolean isValidForm() {
        Boolean isValid = true;

        if (tietEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.input_empty_error));
            isValid = false;
        }
        if (tietPassword.getText().toString().isEmpty()) {
            tilPassword.setError(getString(R.string.input_empty_error));
            isValid = false;
        }

        return isValid;
    }
}
