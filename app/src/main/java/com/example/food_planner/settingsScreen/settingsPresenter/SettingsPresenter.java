package com.example.food_planner.settingsScreen.settingsPresenter;

import android.util.Log;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.settingsScreen.settingsView.OnSignOutListener;
import com.example.food_planner.settingsScreen.settingsView.SettingsView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SettingsPresenter {
    private Repo repo;
    private SettingsView view;
    OnSignOutListener listener;
    private static final String TAG = "SettingsPresenter";

    public SettingsPresenter(Repo repo, OnSignOutListener listener) {
        this.repo = repo;
        this.listener = listener;
    }


    public void deleteAllLocalData(){
        repo.deleteAllLocalPlans().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> Log.i("TAG", "deleteAllLocalData: deleted successfully"));
        repo.deleteAllMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> Log.i("TAG", "deleteAllLocalData: deleted successfully"));
    }


    public void signOut() {
        repo.getLocalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<MealDto>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<MealDto> mealDtos) {
                        FirebaseUser currentUser = repo.getFirebaseDataSource().getCurrentUser();
                        if (currentUser != null) {
                            String uid = currentUser.getUid();
                            Log.i("TAG", "onNext: " + uid);
                            repo.saveMealsToFirebase(uid, mealDtos).addOnSuccessListener(
                                    new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.i(TAG, "onSuccess: ");
                                            listener.onSignOutSuccess();
                                        }
                                    }
                            ).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@androidx.annotation.NonNull Exception e) {
                                    Log.e(TAG, "onFailure: "+ e.getMessage() );
                                }
                            });

                        } else {
                            listener.onSignOutFailure("No user currently signed in");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        listener.onSignOutFailure("Error retrieving local data: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // This won't be called in case of success because we've already called the listener in onNext
                    }
                });
    }

//    @Override
//    public void onSignOutSuccess() {
//        if (view != null) {
//            view.onSignOutSuccess();
//        }
//    }
//
//    @Override
//    public void onSignOutFailure(String errorMessage) {
//        if (view != null) {
//            view.onSignOutFailure(errorMessage);
//        }
//    }
}