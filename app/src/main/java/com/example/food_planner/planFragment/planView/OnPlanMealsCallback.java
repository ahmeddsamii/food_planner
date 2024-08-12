package com.example.food_planner.planFragment.planView;

import com.example.food_planner.model.dtos.PlanDto;

import java.util.List;

public interface OnPlanMealsCallback {
    void onPlanMealsByDaySuccess(List<PlanDto> plans);
    void onPlanMealsByFailure(String errMessage);

    void onAllPlanMealsSuccess(List<PlanDto> planDtos);
    void onAllPlanMealsFailure(String errMessage);
}
