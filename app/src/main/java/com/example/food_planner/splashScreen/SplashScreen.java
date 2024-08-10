package com.example.food_planner.splashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_planner.R;
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.signupScreen.view.SignUpScreen;
import com.google.firebase.FirebaseApp;

public class SplashScreen extends AppCompatActivity {
    Intent intent;
    private static final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences preferences = getSharedPreferences(SignUpScreen.UID_KEY, Context.MODE_PRIVATE);
                    String uId =preferences.getString("LoggedIn","error");
                    Log.i(TAG, "onCreate: SplashActivity UID = "+uId);

                    if (uId.equals("error"))
                    {
                        intent = new Intent(SplashScreen.this, LoginScreen.class);
                    }else{
                        intent = new Intent(SplashScreen.this, HomePageScreen.class);
                        ;

                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, 3000);
    }
}