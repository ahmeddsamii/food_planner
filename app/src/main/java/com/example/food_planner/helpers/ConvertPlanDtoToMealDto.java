package com.example.food_planner.helpers;

import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;

public class ConvertPlanDtoToMealDto {
    public static MealDto convertMealDtoToPlanDto(PlanDto planDto, MealDto mealDto){
        mealDto.setIdMeal(planDto.getIdMeal());
        mealDto.setStrMealThumb(planDto.getStrMealThumb());
        mealDto.setStrCategory(planDto.getStrCategory());
        mealDto.setStrMeal(planDto.getStrMeal());
        mealDto.setStrArea(planDto.getStrArea());
        mealDto.setStrInstructions(planDto.getStrInstructions());
        //mealDto.setStrTags(planDto.getStrTags());
        mealDto.setStrYoutube(planDto.getStrYoutube());
        mealDto.setStrIngredient1(planDto.getStrIngredient1());
        mealDto.setStrIngredient2(planDto.getStrIngredient2());
        mealDto.setStrIngredient3(planDto.getStrIngredient3());
        mealDto.setStrIngredient4(planDto.getStrIngredient4());
        mealDto.setStrIngredient5(planDto.getStrIngredient5());
        mealDto.setStrIngredient6(planDto.getStrIngredient6());
        mealDto.setStrIngredient7(planDto.getStrIngredient7());
        mealDto.setStrIngredient8(planDto.getStrIngredient8());
        mealDto.setStrIngredient9(planDto.getStrIngredient9());
        mealDto.setStrIngredient10(planDto.getStrIngredient10());
        mealDto.setStrIngredient11(planDto.getStrIngredient11());
        mealDto.setStrIngredient12(planDto.getStrIngredient12());
        mealDto.setStrIngredient13(planDto.getStrIngredient13());
        mealDto.setStrIngredient14(planDto.getStrIngredient14());
        mealDto.setStrIngredient15(planDto.getStrIngredient15());
        return mealDto;
    }
}
