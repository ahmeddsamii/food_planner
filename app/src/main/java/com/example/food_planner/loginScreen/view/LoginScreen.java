package com.example.food_planner.loginScreen.view;

import static com.example.food_planner.signupScreen.view.SignUpScreen.UID_KEY;

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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        goToSignUp = findViewById(R.id.tv_signup);
        btn_login = findViewById(R.id.btn_login);
        email = findViewById(R.id.et_mail_login_username);
        password = findViewById(R.id.et_login_password);
        guestMode = findViewById(R.id.tv_guestMode);
        signInWithGoogle = findViewById(R.id.iv_login_google);

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp = new Intent(LoginScreen.this, SignUpScreen.class);
                startActivity(goToSignUp);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isInternetAvailable(getApplicationContext())) {
                    String emailText = email.getText().toString().trim();
                    String passwordText = password.getText().toString().trim();
                    if (!emailText.isEmpty() && !passwordText.isEmpty()) {
                        if (isValidEmail(emailText)) {
                            if (isValidPassword(passwordText)) {
                                presenter.login(emailText, passwordText);
                            } else {
                                password.setError("Invalid password. Must be at least 8 characters long and contain a number.");
                            }
                        } else {
                            email.setError("Invalid email format");
                        }
                    } else {
                        if (emailText.isEmpty()) {
                            email.setError("This field is required");
                        }
                        if (passwordText.isEmpty()) {
                            password.setError("This field is required");
                        }
                    }
                } else {
                    Toast.makeText(LoginScreen.this, "No Internet, please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });




        guestMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtils.isInternetAvailable(getApplicationContext())){
                Intent signInAsGuest = new Intent(LoginScreen.this, HomePageScreen.class);
                signInAsGuest.putExtra("guest", "guest");
                startActivity(signInAsGuest);
                }else {
                    Toast.makeText(LoginScreen.this, "No Internet, please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtils.isInternetAvailable(getApplicationContext())){
                    presenter.setupGoogleSignIn(LoginScreen.this);
                    presenter.signInWithGoogle(LoginScreen.this);
                }else {
                    Toast.makeText(LoginScreen.this, "No Internet, please check your connection", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long and contain at least one number
        String passwordRegex = "^(?=.*\\d).{8,}$";
        return password.matches(passwordRegex);
    }

    @Override
    public void LoginSuccess(FirebaseUser user) {
        SharedPreferences sharedPreferences = getSharedPreferences(UID_KEY , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedIn", user.getUid());
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), HomePageScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void LoginFailure(String errMessage) {
        email.setError(errMessage.toString());
        password.setError(errMessage.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignInResult(task);

        }
    }
}