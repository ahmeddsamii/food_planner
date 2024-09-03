package com.example.food_planner.favoriteScreen.FavoritePresenter;

import android.util.Log;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.view.FavoriteView;
import com.example.food_planner.favoriteScreen.view.onFavClickListener;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter implements onFavClickListener , FavoriteView {
    Repo repo;
    FavoriteView view;
    private static final String TAG = "FavoritePresenter";


    public FavoritePresenter(Repo repo, FavoriteView view){
        this.repo = repo;
        this.view = view;
    }

    public void getLocalData() {
        repo.getLocalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealDtos -> view.getAllFavMeals(mealDtos),
                        throwable -> {
                            // Handle error
                        }
                );
    }


    public void insert(MealDto mealDto){
        repo.insert(mealDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: inserted successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void delete(MealDto mealDto){
        repo.delete(mealDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: deleted successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    @Override
    public void onFavItemClicked(MealDto mealDto) {
        new Thread(){
            @Override
            public void run() {
                repo.insert(mealDto);
            }
        }.start();
    }

    @Override
    public void onFavItemDelete(MealDto mealDto) {
        repo.delete(mealDto).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: deleted successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        repo.getLocalData();
    }

    public void deleteTheMealFromFirebase(String mealId){
        repo.deleteItemFromFirebase(repo.getUidOfUser(),mealId);
    }

    public void fetchUserFavoriteMeals(String uid) {

        repo.getUserFavoriteMeals(uid, new Repo.OnFavoriteMealsCallback() {
            @Override
            public void onSuccess(List<MealDto> meals) {
                view.onFavoriteMealsRetrievedFromFirebaseSuccess(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.onFavoriteMealsRetrievedFromFirebaseFailure("Failed to retrieve favorite meals: " + errorMessage);
            }
        });
    }

    public void insertMealIntoFirebase(String uId, MealDto mealDto){
        repo.saveMealToFireStore(uId, mealDto);
    }


    public void setData(Flowable<List<MealDto>> meals){

    }

    @Override
    public void getAllFavMeals(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseSuccess(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseFailure(String errMessage) {

    }

}
