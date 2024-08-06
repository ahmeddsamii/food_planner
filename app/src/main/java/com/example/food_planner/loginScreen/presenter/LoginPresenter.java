package com.example.food_planner.loginScreen.presenter;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.loginScreen.view.LoginView;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    LoginView view;
    Repo repo;


    private static LoginPresenter instance = null;


    private  LoginPresenter(LoginView view, Repo repo){
        this.view = view;
        this.repo = repo;
    }

    public static LoginPresenter getInstance(LoginView view, Repo repo){
        if (instance == null){
            instance = new LoginPresenter(view, repo);
        }
        return instance;
    }


    public void login(String email, String password) {
        Log.d("LoginPresenter", "Attempting login with email: " + email);
        repo.getFirebaseDataSource().getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = repo.getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
                            if (user != null) {
                                Log.d("LoginPresenter", "Login successful for user: " + user.getEmail());
                                view.LoginSuccess(user);
                            }
                        } else {
                            Exception exception = task.getException();
                            if (exception instanceof FirebaseAuthInvalidUserException) {
                                Log.e("LoginPresenter", "Login failed: Email is not registered.");
                                view.LoginFailure("Email is not registered. Please sign up.");
                            } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                view.LoginFailure("username or password is incorrect, please try again.");
                            } else {
                                Log.e("LoginPresenter", "Login failed: " + exception.getMessage());
                                view.LoginFailure("Error: " + exception.getMessage());
                            }
                        }
                    }
                });
    }




   



}
