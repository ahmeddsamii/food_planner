package com.example.food_planner.loginScreen.presenter;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.loginScreen.view.LoginView;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenter {
    LoginView view;
    Repo repo;

    private GoogleSignInClient mGoogleSignInClient;
    private  static final int RC_SIGN_IN = 9001;


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

    public void setupGoogleSignIn(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public void signInWithGoogle(LoginScreen activity) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    public void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("LoginPresenter", "Google sign in succeeded");
            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {
            Log.e("LoginPresenter", "Google sign in failed", e);
            Log.e("LoginPresenter", "Error code: " + e.getStatusCode());
            Log.e("LoginPresenter", "Error message: " + e.getMessage());
            view.LoginFailure("Google sign in failed: " + e.getStatusCode() + " - " + e.getMessage());
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        repo.getFirebaseDataSource().getFirebaseAuth().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = repo.getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
                            view.LoginSuccess(user);
                        } else {
                            view.LoginFailure("Authentication failed: " + task.getException().getMessage());
                        }
                    }
                });
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
