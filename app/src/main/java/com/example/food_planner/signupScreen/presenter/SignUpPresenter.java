package com.example.food_planner.signupScreen.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.signupScreen.view.SignUpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenter {
    SignUpView view;
    Repo repo;

    public SignUpPresenter(SignUpView view, Repo repo){
        this.view = view;
        this.repo = repo;
    }

    public void singUp(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            view.signUpFailure("All fields must be filled!");
            return;
        }

        Log.d("SignUpPresenter", "Attempting signUp with email: " + email);
        repo.getFirebaseDataSource().getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = repo.getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
                            view.signUpSuccess(user);
                            Log.d("SignUpPresenter", email + " is registered successfully");
                        } else {
                            view.signUpFailure(task.getException().toString());
                            Log.w("SignUpPresenter", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }
}
