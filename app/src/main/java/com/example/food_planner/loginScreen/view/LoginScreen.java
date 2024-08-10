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
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.presenter.LoginPresenter;
import com.example.food_planner.signupScreen.view.SignUpScreen;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        goToSignUp = findViewById(R.id.tv_signup);
        btn_login = findViewById(R.id.btn_login);
        email = findViewById(R.id.et_mail_login_username);
        password = findViewById(R.id.et_login_password);
        guestMode = findViewById(R.id.tv_guestMode);
        LoginPresenter presenter = LoginPresenter.getInstance(this, Repo.getInstance(LoginScreen.this));

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
                Log.i("LoginScreen", "onClick: clicked on login button");
                if(isValidEmail(email.getText().toString())){
                presenter.login(email.getText().toString(),password.getText().toString());
                }else {
                    runOnUiThread(() -> Toast.makeText(LoginScreen.this, "Email is not valid", Toast.LENGTH_SHORT).show());
                }
            }
        });

        guestMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInAsGuest = new Intent(LoginScreen.this, HomePageScreen.class);
                signInAsGuest.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                signInAsGuest.putExtra("guest", "guest");
                startActivity(signInAsGuest);
            }
        });



    }


    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void LoginSuccess(FirebaseUser user) {
        runOnUiThread(() -> Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show());
        SharedPreferences sharedPreferences = getSharedPreferences(UID_KEY , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedIn", user.getUid());
        editor.apply();
        Intent intent = new Intent(LoginScreen.this, HomePageScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void LoginFailure(String errMessage) {
        Log.e("LoginScreen", "Failure method called: " + errMessage);
        runOnUiThread(() -> Toast.makeText(this, errMessage, Toast.LENGTH_LONG).show());
    }
}