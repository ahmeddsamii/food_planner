package com.example.food_planner.Repo.network.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataSource {

    private static FirebaseDataSource instance = null;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private static final String TAG = "FirebaseDataSource";

    private FirebaseDataSource(){
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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

    public Task<Void> saveMealsToFirestore(String uid, List<MealDto> meals) {
        WriteBatch batch = db.batch();
        for (MealDto meal : meals) {
            DocumentReference docRef = db.collection("users")
                    .document(uid)
                    .collection("meals")
                    .document(meal.getIdMeal());
            batch.set(docRef, meal);
        }
        return batch.commit();
    }


    public Task<Void> saveMealToFirestore(String uid, MealDto meal) {
        DocumentReference docRef = db.collection("users")
                .document(uid)
                .collection("meals")
                .document(meal.getIdMeal());

        return docRef.set(meal);
    }

    public Task<Void> savePlanToFirestore(String uid, PlanDto plan) {
        // The implementation here is already correct, but we'll add some logging
        DocumentReference docRef = db.collection("plans")
                .document(uid)
                .collection("plans")
                .document(String.valueOf(plan.getDayOfWeek()))
                .collection("meals")
                .document(plan.getId());

        return docRef.set(plan)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Plan saved successfully to Firestore"))
                .addOnFailureListener(e -> Log.e(TAG, "Error saving plan to Firestore", e));
    }


    public Task<List<MealDto>> getUserFavoriteMeals(String uid) {
        return db.collection("users")
                .document(uid)
                .collection("meals")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        List<MealDto> meals = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            MealDto meal = document.toObject(MealDto.class);
                            if (meal != null) {
                                meals.add(meal);
                            }
                        }
                        return meals;
                    } else {
                        throw task.getException();
                    }
                });
    }

    public Task<List<PlanDto>> getUserPlanMeals(String uid, int dayOfWeek) {
        if (uid == null || uid.isEmpty()) {
            Log.e(TAG, "getUserPlanMeals: UID is null or empty");
            return Tasks.forException(new IllegalArgumentException("UID cannot be null or empty"));
        }

        Log.d(TAG, "getUserPlanMeals: Fetching plans for UID: " + uid + ", Day: " + dayOfWeek);

        return db.collection("plans")
                .document(uid)
                .collection("plans")
                .document(String.valueOf(dayOfWeek))
                .collection("meals")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        List<PlanDto> plans = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            PlanDto plan = document.toObject(PlanDto.class);
                            if (plan != null) {
                                plans.add(plan);
                            }
                        }
                        Log.d(TAG, "getUserPlanMeals: Successfully fetched " + plans.size() + " plans");
                        return plans;
                    } else {
                        Log.e(TAG, "getUserPlanMeals: Error fetching plans", task.getException());
                        throw task.getException();
                    }
                });
    }

    public void deletePlanFromFirebase(String uId, String planId, int dayOfWeek){
        db.collection("plans")
                .document(uId)
                .collection("plans")
                .document(String.valueOf(dayOfWeek))
                .collection("meals")
                .document(planId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Plan deleted Successfully From Firebase ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "There was an error while deleting Plan from Firebase" + e.getMessage() );
                    }
                });
    }
    public void deleteMeal(String userId, String mealId) {
        db.collection("users")
                .document(userId)
                .collection("meals")
                .document(mealId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("DELETE", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("DELETE", "Error deleting document", e);
                    }
                });
    }


    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }
}
