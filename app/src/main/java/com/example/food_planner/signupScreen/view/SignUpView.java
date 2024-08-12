package com.example.food_planner.signupScreen.view;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpView {
    void signUpSuccess(FirebaseUser user);
    void signUpFailure(String errMessage);
    void startGoogleSignInActivity(Intent signInIntent, int requestCode);
}
