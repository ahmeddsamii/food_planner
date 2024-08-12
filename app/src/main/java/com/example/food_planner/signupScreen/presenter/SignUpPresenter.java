    package com.example.food_planner.signupScreen.presenter;

    import android.content.Context;
    import android.content.Intent;
    import android.util.Log;
    import android.widget.Toast;

    import androidx.annotation.NonNull;

    import com.example.food_planner.R;
    import com.example.food_planner.Repo.Repo;
    import com.example.food_planner.signupScreen.view.SignUpView;
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
    import com.google.firebase.auth.FirebaseAuthUserCollisionException;
    import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.auth.GoogleAuthProvider;

    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    public class SignUpPresenter {
        SignUpView view;
        Repo repo;

        private GoogleSignInClient mGoogleSignInClient;
        private static final int RC_SIGN_IN = 9001;

        public SignUpPresenter(SignUpView view, Repo repo){
            this.view = view;
            this.repo = repo;
        }


        public void signInWithGoogle(Context context) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            view.startGoogleSignInActivity(signInIntent, RC_SIGN_IN);
        }

        public void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
            try {
                GoogleSignInAccount account = completedTask.getResult(ApiException.class);
                Log.d("SignUpPresenter", "Google sign in succeeded");
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.e("SignUpPresenter", "Google sign in failed", e);
                view.signUpFailure("Google sign in failed: " + e.getStatusCode() + " - " + e.getMessage());
            }
        }

        private void firebaseAuthWithGoogle(String idToken) {
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            FirebaseAuth auth = repo.getFirebaseDataSource().getFirebaseAuth();

            auth.fetchSignInMethodsForEmail(credential.toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                            if (isNewUser) {
                                // This is a new user, proceed with sign up
                                createUserWithGoogle(credential);
                            } else {
                                // This user already exists
                                view.signUpFailure("An account already exists with this email. Please sign in instead.");
                            }
                        } else {
                            view.signUpFailure("Error checking existing account: " + task.getException().getMessage());
                        }
                    });
        }

        private void createUserWithGoogle(AuthCredential credential) {
            FirebaseAuth auth = repo.getFirebaseDataSource().getFirebaseAuth();
            auth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                view.signUpSuccess(user);
                            } else {
                                view.signUpFailure("Authentication failed: " + task.getException().getMessage());
                            }
                        }
                    });
        }

        public void singUp(String email, String password, Context context) {
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
                                if(isValidEmail(email)){
                                    FirebaseUser user = repo.getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
                                    view.signUpSuccess(user);
                                    Log.d("SignUpPresenter", email + " is registered successfully");
                                }else {
                                    view.signUpFailure("Please enter a valid email address!");
                                }
                            } else

                            {   Exception exception = task.getException();
                                if(exception instanceof FirebaseAuthWeakPasswordException){
                                    view.signUpFailure("Weak password!");
                                } else if (exception instanceof FirebaseAuthUserCollisionException) {
                                    view.signUpFailure("This email is already registered");
                                }else {
                                    view.signUpFailure(exception.getMessage());
                                }

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


//        public void signOut() {
//            repo.signOut(new Repo.OnSignOutListener() {
//                @Override
//                public void onSignOutSuccess() {
//                    view.onSignOutSuccess();
//                }
//
//                @Override
//                public void onSignOutFailure(String errorMessage) {
//                    view.onSignOutFailure(errorMessage);
//                }
//            });
    }
