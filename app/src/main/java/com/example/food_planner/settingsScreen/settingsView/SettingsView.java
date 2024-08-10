package com.example.food_planner.settingsScreen.settingsView;

public interface SettingsView {
    void onSignOutSuccess();
    void onSignOutFailure(String errorMessage);
}