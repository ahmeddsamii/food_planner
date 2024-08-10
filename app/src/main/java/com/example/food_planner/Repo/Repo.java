package com.example.food_planner.Repo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.food_planner.model.dtos.MealData;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.UserData;
import com.example.food_planner.settingsScreen.settingsView.OnSignOutListener;
import com.google.firebase.auth.FirebaseUser;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo implements OnSignOutListener{
    FirebaseDataSource firebaseDataSource;
    MealsRemoteDataSource mealsRemoteDataSource;
    MealsLocalDataSource mealsLocalDataSource;
    OnSignOutListener listener;

    MealDao dao;
    private static Repo instance = null;
    private static final String TAG = "Repo";

    private Repo(Context context){
        firebaseDataSource = FirebaseDataSource.getInstance();
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
        mealsLocalDataSource = MealsLocalDataSource.getInstance(context);
        dao = mealsLocalDataSource.mealDao();
    }

    public static Repo getInstance(Context context){
        if (instance == null){
            instance = new Repo(context);
        }
        return instance;
    }

    public FirebaseDataSource getFirebaseDataSource(){
        return firebaseDataSource;
    }

    public void getRandomMeal(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForRandomMeal(callBack);
    }

    public void getAllCategories(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForCategories(callBack);
    }

//    public void getIngredient(NetworkCallBack networkCallBack){
//        mealsRemoteDataSource.makeNetworkCallForIngredients(networkCallBack);
//    }

    public void insert(MealDto mealDto){
        dao.insert(mealDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "Inserted Successfully: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void getItemByName(NetworkCallBack callBack,String name){
        mealsRemoteDataSource.makeNetworkCallToGetItemByName(callBack, name);
    }

    public void delete(MealDto mealDto){
        new Thread(){
            @Override
            public void run() {
                dao.delete(mealDto);
            }
        }.start();

    }
    public Flowable<List<MealDto>> getLocalData(){
       return dao.getAllMeals();
    }


    public void getAllCountries(NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForAllCountries( callBack);
    }

    public void getMealsByCategory(String category, NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByCategory(category, callBack);
    }

    public void getMealsByCountry(String country, NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallToGetMealsByCountry(country,callBack);
    }

    public void getSearchMealsByName(String name , NetworkCallBack networkCallBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByName(name,networkCallBack);
    }

    public void getAllIngredients(NetworkCallBack callBack){
        mealsRemoteDataSource.makeCallForAllIngredients(callBack);
    }

    public void getAllMealsByIngredients(String ingredient , NetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByIngredients(ingredient, callBack);
    }




    public void deleteAllMeals(){
        dao.deleteAllMeals();
    }

    public void saveMealToFireStore(String uId , MealDto mealDto){
        firebaseDataSource.saveMealToFirestore(uId, mealDto);
    }







    @Override
    public void onSignOutSuccess() {
        firebaseDataSource.getFirebaseAuth().signOut();
        listener.onSignOutSuccess();
    }

    public void getUserFavoriteMeals(String uid, OnFavoriteMealsCallback callback) {
        firebaseDataSource.getUserFavoriteMeals(uid)
                .addOnSuccessListener(meals -> callback.onSuccess(meals))
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    public void deleteItemFromFirebase(String uId , String mealId){
        firebaseDataSource.deleteMeal(uId, mealId);
    }

    public String getUidOfUser(){
        FirebaseUser user = getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
        return user.getUid();
    }

    public interface OnFavoriteMealsCallback {
    void onSuccess(List<MealDto> meals);
    void onFailure(String errorMessage);
}

    public interface OnUserDataCallback {
        void onSuccess(UserData userData);
        void onFailure(String errorMessage);
    }



    @Override
    public void onSignOutFailure(String errorMessage) {
        listener.onSignOutFailure(errorMessage);
    }
}
