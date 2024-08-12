package com.example.food_planner.homePageScreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.food_planner.R;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.signupScreen.view.SignUpScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;

public class HomePageScreen extends AppCompatActivity {

    NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page_screen);
        FirebaseApp.initializeApp(this);
        bottomNavigationView = findViewById(R.id.bottomNavBar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        SharedPreferences sharedPreferences = getSharedPreferences(SignUpScreen.UID_KEY, MODE_PRIVATE);
        String uId = sharedPreferences.getString("LoggedIn", "error");
        if(!NetworkUtils.isInternetAvailable(HomePageScreen.this)){
            Toast.makeText(this, "No internet, please check your connection", Toast.LENGTH_SHORT).show();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.favoriteFragment || itemId == R.id.settingsFragment || itemId == R.id.searchFragment || itemId == R.id.planFragment) {
                if ("error".equals(uId)) {
                    showSignInSnackbar();
                    return false; // prevent navigation
                }
            }
            return NavigationUI.onNavDestinationSelected(item, navController);
        });
    }

    private void showSignInSnackbar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "Please sign in to access this feature", Snackbar.LENGTH_LONG);
        snackbar.setAction("Sign In", v -> {

            navigateToSignInScreen();
        });
        snackbar.show();
    }

    private void navigateToSignInScreen() {
        Intent intent = new Intent(this, LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}