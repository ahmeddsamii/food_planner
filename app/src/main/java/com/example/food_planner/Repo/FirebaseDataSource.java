package com.example.food_planner.Repo;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseDataSource {

    private static FirebaseDataSource instance = null;
    private FirebaseAuth firebaseAuth;

    private FirebaseDataSource(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseDataSource getInstance(){
        if (instance == null){
            instance = new FirebaseDataSource();
        }
        return instance;
    }

    public FirebaseAuth getFirebaseAuth()
    {
        return firebaseAuth;
    }

    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }
}
