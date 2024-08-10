package com.example.food_planner.planFragment.planView;

import com.example.food_planner.model.dtos.PlanDto;

import java.util.List;

public interface OnPlansView {
    void onPlansSuccess(List<PlanDto> planDtos);
    void onPlansFailure(String errMessage);
}
