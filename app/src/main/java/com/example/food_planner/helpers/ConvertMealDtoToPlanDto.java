package com.example.food_planner.helpers;

import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;

public class ConvertMealDtoToPlanDto {
    public static PlanDto convertMealDtoToPlanDto(PlanDto planDto, MealDto mealDto){
        planDto.setId(mealDto.getIdMeal());
        planDto.setStrMealThumb(mealDto.getStrMealThumb());
        planDto.setStrCategory(mealDto.getStrCategory());
        planDto.setStrMeal(mealDto.getStrMeal());
        planDto.setStrArea(mealDto.getStrArea());
        planDto.setStrInstructions(mealDto.getStrInstructions());
        planDto.setStrTags(mealDto.getStrTags());
        planDto.setStrYoutube(mealDto.getStrYoutube());
        planDto.setStrIngredient1(mealDto.getStrIngredient1());
        planDto.setStrIngredient2(mealDto.getStrIngredient2());
        planDto.setStrIngredient3(mealDto.getStrIngredient3());
        planDto.setStrIngredient4(mealDto.getStrIngredient4());
        planDto.setStrIngredient5(mealDto.getStrIngredient5());
        planDto.setStrIngredient6(mealDto.getStrIngredient6());
        planDto.setStrIngredient7(mealDto.getStrIngredient7());
        planDto.setStrIngredient8(mealDto.getStrIngredient8());
        planDto.setStrIngredient9(mealDto.getStrIngredient9());
        planDto.setStrIngredient10(mealDto.getStrIngredient10());
        planDto.setStrIngredient11(mealDto.getStrIngredient11());
        planDto.setStrIngredient12(mealDto.getStrIngredient12());
        planDto.setStrIngredient13(mealDto.getStrIngredient13());
        planDto.setStrIngredient14(mealDto.getStrIngredient14());
        planDto.setStrIngredient15(mealDto.getStrIngredient15());
        return planDto;
    }
}
