package com.example.food_planner.Repo;

import android.content.Context;
import android.util.Log;

import com.example.food_planner.Repo.network.firebase.FirebaseDataSource;
import com.example.food_planner.Repo.local.MealDao;
import com.example.food_planner.Repo.local.MealsLocalDataSource;
import com.example.food_planner.Repo.local.PlanDao;
import com.example.food_planner.Repo.local.PlanLocalDataSource;
import com.example.food_planner.Repo.network.api.MealsRemoteDataSource;
import com.example.food_planner.Repo.network.api.callbacks.AllCountriesNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.AllIngredientsNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.CategoriesNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.ItemByNameNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByCategoriesNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByCountryNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.MealsByIngredientNetworkCallBack;
import com.example.food_planner.Repo.network.api.callbacks.RandomMealCallBack;
import com.example.food_planner.Repo.network.api.callbacks.SearchMealsByNameNetworkCallBack;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planView.OnPlanMealsCallback;
import com.example.food_planner.settingsScreen.settingsView.OnSignOutListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo implements OnSignOutListener{
    FirebaseDataSource firebaseDataSource;
    MealsRemoteDataSource mealsRemoteDataSource;
    MealsLocalDataSource mealsLocalDataSource;
    OnSignOutListener listener;
    PlanLocalDataSource planLocalDataSource;
    MealDao mealDao;
    PlanDao planDao;
    private static Repo instance = null;
    private static final String TAG = "Repo";

    private Repo(Context context){
        firebaseDataSource = FirebaseDataSource.getInstance();
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
        mealsLocalDataSource = MealsLocalDataSource.getInstance(context);
        planLocalDataSource = PlanLocalDataSource.getInstance(context);
        mealDao = mealsLocalDataSource.mealDao();
        planDao = planLocalDataSource.planDao();
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

    public void getRandomMeal(RandomMealCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForRandomMeal(callBack);
    }

    public void getAllCategories(CategoriesNetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForCategories(callBack);
    }


    public Flowable<List<PlanDto>> getPlansByDay(int day) {
        return planDao.getPlanByDay(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insert(MealDto mealDto){
      return mealDao.insert(mealDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertIntoPlans(PlanDto planDto){
        return planDao.insert(planDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getItemByName(ItemByNameNetworkCallBack callBack, String name){
        mealsRemoteDataSource.makeNetworkCallToGetItemByName(callBack, name);
    }

    public Completable delete(MealDto mealDto){
        return mealDao.delete(mealDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Completable deletePlanMeal(PlanDto planDto){
        return planDao.deletePlanMeal(planDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Flowable<List<MealDto>> getLocalData(){
       return mealDao.getAllMeals();
    }


    public void getAllCountries(AllCountriesNetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForAllCountries( callBack);
    }

    public void getMealsByCategory(String category, MealsByCategoriesNetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByCategory(category, callBack);
    }

    public void getMealsByCountry(String country, MealsByCountryNetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallToGetMealsByCountry(country,callBack);
    }

    public void getSearchMealsByName(String name , SearchMealsByNameNetworkCallBack networkCallBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByName(name,networkCallBack);
    }

    public void getAllIngredients(AllIngredientsNetworkCallBack callBack){
        mealsRemoteDataSource.makeCallForAllIngredients(callBack);
    }

    public void getAllMealsByIngredients(String ingredient , MealsByIngredientNetworkCallBack callBack){
        mealsRemoteDataSource.makeNetworkCallForMealsByIngredients(ingredient, callBack);
    }



    public Completable deleteAllMeals(){
        return mealDao.deleteAllMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveMealToFireStore(String uId , MealDto mealDto){
        firebaseDataSource.saveMealToFirestore(uId, mealDto);
    }



    public Completable deleteAllLocalPlans(){
        return planDao.deleteAllPlans().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Task<Void> savePlanToFirebase(String uId, PlanDto plan) {
            return firebaseDataSource.savePlanToFirestore(uId, plan);
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

    public void getUserPlanMealsFromFirebase(String uId, int dayOfWeek, OnPlanMealsCallback callback){
        firebaseDataSource.getUserPlanMeals(uId,dayOfWeek).addOnSuccessListener(
                plans-> callback.onPlanMealsByDaySuccess(plans))
                .addOnFailureListener(e -> callback.onPlanMealsByFailure(e.getMessage()));
    }


    public void deletePlanFromFirebase(String uid, String planId, int dayOfWeek){
        firebaseDataSource.deletePlanFromFirebase(uid, planId , dayOfWeek);
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

public Task<Void> saveMealsToFirebase(String uid, List<MealDto> mealDtos){
        return firebaseDataSource.saveMealsToFirestore(uid, mealDtos);
}

    @Override
    public void onSignOutFailure(String errorMessage) {
        listener.onSignOutFailure(errorMessage);
    }
}
