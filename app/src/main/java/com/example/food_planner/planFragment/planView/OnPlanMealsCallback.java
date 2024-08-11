package com.example.food_planner.planFragment.planView;

import com.example.food_planner.model.dtos.PlanDto;

import java.util.List;

public interface OnPlanMealsCallback {
    void onPlanMealsSuccess(List<PlanDto> plans);
    void onPlanMealsFailure(String errMessage);
}
