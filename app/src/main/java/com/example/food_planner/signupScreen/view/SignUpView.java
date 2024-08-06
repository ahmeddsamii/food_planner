package com.example.food_planner.signupScreen.view;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpView {
    void signUpSuccess(FirebaseUser user);
    void signUpFailure(String errMessage);
}
