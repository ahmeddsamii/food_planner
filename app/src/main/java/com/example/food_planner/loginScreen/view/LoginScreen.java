package com.example.food_planner.loginScreen.view;

import static com.example.food_planner.signupScreen.view.SignUpScreen.UID_KEY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.presenter.LoginPresenter;
import com.example.food_planner.signupScreen.view.SignUpScreen;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity implements LoginView {
    TextView goToSignUp;
    Button btn_login;
    EditText email;
    EditText password;
    TextView guestMode;
    ImageView signInWithGoogle;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        presenter = LoginPresenter.getInstance(this, Repo.getInstance(LoginScreen.this));

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        goToSignUp = findViewById(R.id.tv_signup);
        btn_login = findViewById(R.id.btn_login);
        email = findViewById(R.id.et_mail_login_username);
        password = findViewById(R.id.et_login_password);
        guestMode = findViewById(R.id.tv_guestMode);
        signInWithGoogle = findViewById(R.id.iv_login_google);
    }

    private void setupClickListeners() {
        goToSignUp.setOnClickListener(v -> startActivity(new Intent(LoginScreen.this, SignUpScreen.class)));

        btn_login.setOnClickListener(v -> attemptLogin());

        guestMode.setOnClickListener(v -> signInAsGuest());

        signInWithGoogle.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                signInWithGoogle();
            }
            return true;
        });
    }

    private void attemptLogin() {
        if (NetworkUtils.isInternetAvailable(getApplicationContext())) {
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();
            if (validateInputs(emailText, passwordText)) {
                presenter.login(emailText, passwordText);
            }
        } else {
            showNoInternetToast();
        }
    }

    private boolean validateInputs(String emailText, String passwordText) {
        if (emailText.isEmpty() || passwordText.isEmpty()) {
            if (emailText.isEmpty()) email.setError("This field is required");
            if (passwordText.isEmpty()) password.setError("This field is required");
            return false;
        }

        if (!isValidEmail(emailText)) {
            email.setError("Invalid email format");
            return false;
        }

        if (!isValidPassword(passwordText)) {
            password.setError("Invalid password. Must be at least 8 characters long and contain a number.");
            return false;
        }

        return true;
    }

    private void signInAsGuest() {
        if (NetworkUtils.isInternetAvailable(getApplicationContext())) {
            Intent signInAsGuest = new Intent(LoginScreen.this, HomePageScreen.class);
            signInAsGuest.putExtra("guest", "guest");
            startActivity(signInAsGuest);
        } else {
            showNoInternetToast();
        }
    }

    private void signInWithGoogle() {
        if (NetworkUtils.isInternetAvailable(getApplicationContext())) {
            presenter.setupGoogleSignIn(LoginScreen.this);
            presenter.signInWithGoogle(LoginScreen.this);
        } else {
            showNoInternetToast();
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*\\d).{8,}$");
    }

    private void showNoInternetToast() {
        Toast.makeText(LoginScreen.this, "No Internet, please check your connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginSuccess(FirebaseUser user) {
        SharedPreferences sharedPreferences = getSharedPreferences(UID_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedIn", user.getUid());
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), HomePageScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void LoginFailure(String errMessage) {
        email.setError(errMessage);
        password.setError(errMessage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LoginPresenter.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignInResult(task);
        }
    }
}