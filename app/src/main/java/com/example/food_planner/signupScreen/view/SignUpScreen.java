package com.example.food_planner.signupScreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.signupScreen.presenter.SignUpPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreen extends AppCompatActivity implements SignUpView {

    TextView goToLogin;
    Button btn_signUp;
    EditText email, password, username;
    ImageView signUpWithGoogle;
    SignUpPresenter presenter;
    public static String UID_KEY = "UID_LOGIN";
    private static final String TAG = "SignUpScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_screen);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        goToLogin = findViewById(R.id.tv_signIn);
        btn_signUp = findViewById(R.id.btn_signup);
        email = findViewById(R.id.et_signUp_email);
        password = findViewById(R.id.et_signup_password);
        username = findViewById(R.id.et_signup_username);
        signUpWithGoogle = findViewById(R.id.iv_singUp_google);
        presenter = new SignUpPresenter(this, Repo.getInstance(SignUpScreen.this));
    }

    private void setupListeners() {
        goToLogin.setOnClickListener(v -> {
            Intent goToLogin = new Intent(SignUpScreen.this, LoginScreen.class);
            startActivity(goToLogin);
        });

        signUpWithGoogle.setOnClickListener(v -> {
            if (NetworkUtils.isInternetAvailable(SignUpScreen.this)) {
                presenter.signInWithGoogle(SignUpScreen.this);
            } else {
                showToast("No Internet, please check your connection");
            }
        });

        btn_signUp.setOnClickListener(v -> handleSignUp());
    }

    private void handleSignUp() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String username_text = username.getText().toString().trim();

        if (NetworkUtils.isInternetAvailable(SignUpScreen.this)) {
            if (validateInputs(email_text, password_text, username_text)) {
                presenter.singUp(email_text, password_text, username_text);
            }
        } else {
            showToast("No connection, please check your internet");
        }
    }

    private boolean validateInputs(String email, String password, String username) {
        boolean isValid = true;

        if (email.isEmpty()) {
            this.email.setError("This field is required");
            isValid = false;
        } else if (!isValidEmail(email)) {
            this.email.setError("Invalid email address");
            isValid = false;
        }

        if (password.isEmpty()) {
            this.password.setError("This field is required");
            isValid = false;
        } else if (!isValidPassword(password)) {
            this.password.setError("Invalid password. Must be at least 8 characters long and contain a number.");
            isValid = false;
        }

        if (username.isEmpty()) {
            this.username.setError("This field is required");
            isValid = false;
        } else if (!isValidUsername(username)) {
            this.username.setError("Invalid username. Must be 3-20 characters long and contain only letters, numbers, and underscores.");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*\\d).{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean isValidUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9_]{3,20}$";
        return username.matches(usernameRegex);
    }

    @Override
    public void signUpSuccess(FirebaseUser user) {
        showToast("Registered successfully");
        saveUserIdToSharedPreferences(user.getUid());
        navigateToHomeScreen();
    }

    @Override
    public void signUpFailure(String errMessage) {
        runOnUiThread(() -> showToast(errMessage));
    }

    @Override
    public void startGoogleSignInActivity(Intent signInIntent, int requestCode) {
        startActivityForResult(signInIntent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignInResult(task);
        }
    }

    private void saveUserIdToSharedPreferences(String userId) {
        SharedPreferences sharedPreferences = getSharedPreferences(UID_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedIn", userId);
        editor.apply();
    }

    private void navigateToHomeScreen() {
        Intent intent = new Intent(SignUpScreen.this, HomePageScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}