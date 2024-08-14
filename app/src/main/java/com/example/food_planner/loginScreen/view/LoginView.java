package com.example.food_planner.loginScreen.view;

import android.content.IntentSender;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {
    void LoginSuccess(FirebaseUser user);
    void LoginFailure(String errMessage);
}
