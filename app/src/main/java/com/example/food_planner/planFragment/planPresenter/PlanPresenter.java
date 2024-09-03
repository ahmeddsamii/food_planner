package com.example.food_planner.planFragment.planPresenter;

import android.util.Log;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planView.OnPlanMealsCallback;
import com.example.food_planner.planFragment.planView.OnPlansView;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter{
    Repo repo;
    OnPlansView onPlansView;
    private static final String TAG = "PlanPresenter";

    public PlanPresenter(Repo repo , OnPlansView onPlansView){
        this.repo = repo;
        this.onPlansView = onPlansView;
    }


    public void deletePlanMeal(PlanDto planDto){
        repo.deletePlanMeal(planDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.i("TAG", "onComplete: deleted successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void deletePlanFromFirebase(String uid, String planId, int dayOfWeek){
        repo.deletePlanFromFirebase(uid, planId, dayOfWeek);
    }

    public Completable savePlanToFirestore(String uId, PlanDto planDto) {
        // Convert the Firebase Task to an RxJava Completable
        return Completable.create(emitter -> {
            repo.savePlanToFirebase(uId, planDto)
                    .addOnSuccessListener(aVoid -> {
                        Log.d("PlanPresenter", "Plan saved successfully to Firestore");
                        emitter.onComplete();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("PlanPresenter", "Error saving plan to Firestore", e);
                        emitter.onError(e);
                    });
        });
    }

    public void fetchDataForPlanMealsFromFirebase(String uId, int dayOfWeek){
        repo.getUserPlanMealsFromFirebase(uId, dayOfWeek, new OnPlanMealsCallback() {
            @Override
            public void onPlanMealsByDaySuccess(List<PlanDto> plans) {
                onPlansView.onPlansSuccessFromFirebaseByDay(plans);
            }

            @Override
            public void onPlanMealsByFailure(String errMessage) {
                onPlansView.onPlansFailure(errMessage);
            }

            @Override
            public void onAllPlanMealsSuccess(List<PlanDto> planDtos) {

            }

            @Override
            public void onAllPlanMealsFailure(String errMessage) {

            }
        });
    }

    public void insertIntoPlans(PlanDto planDto){
        repo.insertIntoPlans(planDto).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: insterted successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void getMealsByDay(int day){
         repo.getPlansByDay(day).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<PlanDto>>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(List<PlanDto> planDtos) {
                Log.i("TAG", "onNext: "+planDtos.size());
                onPlansView.onPlansSuccess(planDtos);
            }

            @Override
            public void onError(Throwable t) {
                onPlansView.onPlansFailure(t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
