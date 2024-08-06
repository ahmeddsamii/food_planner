package com.example.food_planner.signupScreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.signupScreen.presenter.SignUpPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreen extends AppCompatActivity implements SignUpView{

    TextView goToLogin;
    Button btn_signUp;
    EditText email,password, username;
    public static String UID_KEY = "UID_LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_screen);

        goToLogin = findViewById(R.id.tv_signIn);
        btn_signUp = findViewById(R.id.btn_signup);
        email = findViewById(R.id.et_signUp_email);
        password = findViewById(R.id.et_signup_password);
        username = findViewById(R.id.et_signup_username);
        SignUpPresenter presenter = new SignUpPresenter(this, Repo.getInstance(SignUpScreen.this));
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(SignUpScreen.this, LoginScreen.class);
                startActivity(goToLogin);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                String username_text = username.getText().toString();
                if(email_text.isEmpty() || password_text.isEmpty() || username_text.isEmpty()){
                    Toast.makeText(SignUpScreen.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                }else if (!isValidEmail(email_text)){
                    Toast.makeText(SignUpScreen.this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show();
                }

                else{
                    presenter.singUp(email_text,password_text);
                }

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
    public void signUpSuccess(FirebaseUser user) {
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences(UID_KEY , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedIn", user.getUid());
        editor.apply();
        Intent intent = new Intent(SignUpScreen.this, HomePageScreen.class);
        startActivity(intent);
    }

    @Override
    public void signUpFailure(String errMessage) {
        Toast.makeText(this, errMessage, Toast.LENGTH_SHORT).show();
    }
}