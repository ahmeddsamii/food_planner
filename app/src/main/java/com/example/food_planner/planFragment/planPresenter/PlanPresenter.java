package com.example.food_planner.planFragment.planPresenter;

import android.util.Log;

import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planView.OnPlansView;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;

public class PlanPresenter {
    Repo repo;
    OnPlansView onPlansView;

    public PlanPresenter(Repo repo , OnPlansView onPlansView){
        this.repo = repo;
        this.onPlansView = onPlansView;
    }


    public void deletePlanMeal(PlanDto planDto){
        repo.deletePlanMeal(planDto).subscribe(new CompletableObserver() {
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

    public void insertIntoPlans(PlanDto planDto){
        repo.insertIntoPlans(planDto);
    }

    public void getMealsByDay(int day){
        repo.getPlansByDay(day).subscribe(new FlowableSubscriber<List<PlanDto>>() {
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
