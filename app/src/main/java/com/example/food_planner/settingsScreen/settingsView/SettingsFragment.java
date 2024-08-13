package com.example.food_planner.settingsScreen.settingsView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.homePageScreen.view.HomePageScreen;
import com.example.food_planner.loginScreen.view.LoginScreen;
import com.example.food_planner.settingsScreen.settingsPresenter.SettingsPresenter;
import com.example.food_planner.signupScreen.view.SignUpScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment implements SettingsView , OnSignOutListener {
    Button btn_signOut;
    SettingsPresenter presenter;
    TextView username;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_signOut = view.findViewById(R.id.btn_signOut);
        username = view.findViewById(R.id.tv_setting_username);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username.setText(user.getEmail());
        presenter = new SettingsPresenter(Repo.getInstance(getContext()), this);
        //btn_signOut.setOnClickListener(new  presenter.signOut());
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isInternetAvailable(getContext())){
                presenter.signOut();
                }else{
                    Toast.makeText(getContext(), "Failed to sign out, check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSignOutSuccess() {
        if (isAdded() && getActivity() != null) {
            Intent intent = new Intent(getActivity(), LoginScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Context context = getActivity().getApplicationContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences(SignUpScreen.UID_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LoggedIn", "error");
            editor.apply();

            Toast.makeText(context, "logged out successfully", Toast.LENGTH_SHORT).show();
            presenter.deleteAllLocalData();
        }
    }

    @Override
    public void onSignOutFailure(String errorMessage) {
        Toast.makeText(getContext(), "Sign out failed: " + errorMessage, Toast.LENGTH_LONG).show();
        Log.i("TAG", "onSignOutFailure: "+errorMessage);
    }
}