package com.example.food_planner.favoriteScreen.FavoritePresenter;

import androidx.lifecycle.LiveData;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.view.FavoriteView;
import com.example.food_planner.favoriteScreen.view.onFavClickListener;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.UserData;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlinx.coroutines.flow.Flow;

public class FavoritePresenter implements onFavClickListener , FavoriteView {
    Repo repo;
    FavoriteView view;


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
        new Thread(){
            @Override
            public void run() {
                repo.insert(mealDto);
            }
        }.start();
    }

    public void delete(MealDto mealDto){
        repo.delete(mealDto);
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
        repo.delete(mealDto);
        repo.getLocalData();
    }

    public void deleteTheMealFromFirebase(String mealId){
        repo.deleteItemFromFirebase(repo.getUidOfUser(),mealId);
    }

    public void fetchUserFavoriteMeals(String uid) {
        repo.getUserFavoriteMeals(uid, new Repo.OnFavoriteMealsCallback() {
            @Override
            public void onSuccess(List<MealDto> meals) {
                view.onFavoriteMealsRetrieved(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.onFavoriteMealsRetrievedFailure("Failed to retrieve favorite meals: " + errorMessage);
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
    public void onFavoriteMealsRetrieved(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFailure(String errMessage) {

    }

}
