package com.example.food_planner.Repo;

import android.content.Context;
import android.util.Log;

import com.example.food_planner.Repo.network.firebase.FirebaseDataSource;
import com.example.food_planner.Repo.local.MealDao;
import com.example.food_planner.Repo.local.MealsLocalDataSource;
import com.example.food_planner.Repo.local.PlanDao;
import com.example.food_planner.Repo.local.PlanLocalDataSource;
import com.example.food_planner.Repo.network.api.MealsRemoteDataSource;
import com.example.food_planner.model.dto_repos.ResponseAllIngredients;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planView.OnPlanMealsCallback;
import com.example.food_planner.settingsScreen.settingsView.OnSignOutListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
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

    public Single<ResponseMeals> getRandomMeal(){
        return mealsRemoteDataSource.makeNetworkCallForRandomMeal();
    }

    public Single<ResponseCategory> getAllCategories(){
        return mealsRemoteDataSource.makeNetworkCallForCategories();
    }


    public Flowable<List<PlanDto>> getPlansByDay(int day) {
        return planDao.getPlanByDay(day);
    }

    public Completable insert(MealDto mealDto){
      return mealDao.insert(mealDto);
    }

    public Completable insertIntoPlans(PlanDto planDto){
        return planDao.insert(planDto);
    }

    public Single<ResponseMeals> getItemByName(String name){
        return mealsRemoteDataSource.makeNetworkCallToGetItemByName(name);
    }

    public Completable delete(MealDto mealDto){
        return mealDao.delete(mealDto);

    }

    public Completable deletePlanMeal(PlanDto planDto){
        return planDao.deletePlanMeal(planDto);
    }
    public Flowable<List<MealDto>> getLocalData(){
       return mealDao.getAllMeals();
    }


    public Single<ResponseCountry> getAllCountries(){
        return mealsRemoteDataSource.makeNetworkCallForAllCountries();
    }

    public Single<ResponseMeals> getMealsByCategory(String category){
        return mealsRemoteDataSource.makeNetworkCallForMealsByCategory(category);
    }

    public Single<ResponseMealInfoDto> getMealsByCountry(String country){
        return mealsRemoteDataSource.makeNetworkCallToGetMealsByCountry(country);
    }

    public Single<ResponseMeals> getSearchMealsByName(String name){
        return mealsRemoteDataSource.makeNetworkCallForMealsByName(name);
    }

    public Single<ResponseAllIngredients> getAllIngredients(){
        return mealsRemoteDataSource.makeCallForAllIngredients();
    }

    public Single<ResponseMealByIngredientDto> getAllMealsByIngredients(String ingredient){
        return mealsRemoteDataSource.makeNetworkCallForMealsByIngredients(ingredient);
    }



    public Completable deleteAllMeals(){
        return mealDao.deleteAllMeals();
    }

    public void saveMealToFireStore(String uId , MealDto mealDto){
        firebaseDataSource.saveMealToFirestore(uId, mealDto);
    }



    public Completable deleteAllLocalPlans(){
        return planDao.deleteAllPlans();
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
