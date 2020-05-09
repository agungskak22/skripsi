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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;

    private TextView tvLogin;
    private TextInputEditText tietName, tietEmail, tietPassword, tietPasswordConfirmation;
    private TextInputLayout tilName, tilEmail, tilPassword, tilPasswordConfirmation;
    private MaterialButton mbtnRegister;
    private FrameLayout flProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        initView();
    }

    private void initView() {
        tvLogin = findViewById(R.id.tv_login);
        tietEmail = findViewById(R.id.tiet_email);
        tietName = findViewById(R.id.tiet_name);
        tietPassword = findViewById(R.id.tiet_password);
        tietPasswordConfirmation = findViewById(R.id.tiet_password_confirmation);
        tilEmail = findViewById(R.id.til_email);
        tilName = findViewById(R.id.til_name);
        tilPassword = findViewById(R.id.til_password);
        tilPasswordConfirmation = findViewById(R.id.til_password_confirmation);
        mbtnRegister = findViewById(R.id.button_register);
        flProgressBar = findViewById(R.id.fl_progress_bar);

        tvLogin.setOnClickListener(this);
        mbtnRegister.setOnClickListener(this);

        tietName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

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
                tilPasswordConfirmation.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        tietPasswordConfirmation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPassword.setError(null);
                tilPasswordConfirmation.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                createAccount();
                break;
            case R.id.tv_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
    }

    private void createAccount() {
        if(!isValidForm()) return;

        String name = tietName.getText().toString();
        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();

        flProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            // go to MainActivity
                            Toast.makeText(getApplicationContext(), "Berhasil daftar", Toast.LENGTH_LONG).show();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Tidak bisa membuat akun. Check email dan password kamu", Toast.LENGTH_LONG).show();
                        }
                        flProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private boolean isValidForm() {
        boolean isValid = true;
        if (tietEmail.getText().toString().isEmpty()) {
            tilEmail.setError(getString(R.string.input_empty_error));
            isValid = false;
        }
        if (tietName.getText().toString().isEmpty()) {
            tilName.setError(getString(R.string.input_empty_error));
            isValid = false;
        }
        if (tietPassword.getText().toString().isEmpty()) {
            tilPassword.setError(getString(R.string.input_empty_error));
            isValid = false;
        }
        if (tietPasswordConfirmation.getText().toString().isEmpty()) {
            tilPasswordConfirmation.setError(getString(R.string.input_empty_error));
            isValid = false;
        }
        if (!tietPassword.getText().toString().equals(tietPasswordConfirmation.getText().toString())) {
            tilPasswordConfirmation.setError("Password tidak sesuai!");
            isValid = false;
        }

        if (tietPassword.getText().toString().length() < 6) {
            tilPassword.setError("Password minimal 6 karakter");
            isValid = false;
        }

        return isValid;
    }
}
