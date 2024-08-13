package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsPresenter.DetailsMealsByIngredientsPresenter;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dtos.AllIngredientDto;
import com.example.food_planner.model.dtos.MealDto;


public class DetailsMealsByIngredientsFragment extends Fragment implements DetailsMealsByIngredientsView , onMealsByIngredientsClickListener{

   RecyclerView mealsByIngredientsRv;
    DetailsMealsByIngredientsPresenter presenter;
    private static final String TAG = "DetailsMealsByIngredien";
    public DetailsMealsByIngredientsFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_meals_by_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsByIngredientsRv = view.findViewById(R.id.details_meals_by_ingredients_rv);
        AllIngredientDto ingredientDto= DetailsMealsByIngredientsFragmentArgs.fromBundle(getArguments()).getIngredientDto();
        presenter = new DetailsMealsByIngredientsPresenter(Repo.getInstance(getContext()),this);
        presenter.getAllMealsByIngredients(ingredientDto.getStrIngredient());
    }

    @Override
    public void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto responseMealByIngredientDto) {
        DetailsMealsByIngredientsAdapter adapter = new DetailsMealsByIngredientsAdapter(responseMealByIngredientDto.getMeals(), getContext(), this);
        mealsByIngredientsRv.setAdapter(adapter);
        mealsByIngredientsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onAllMealsByIngredientsFailure(String errMessage) {
        Toast.makeText(getContext(), errMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemByNameSuccess(MealDto mealDto) {
        DetailsMealsByIngredientsFragmentDirections.ActionDetailsMealsByIngredientsFragmentToDetailsFragment action =
                DetailsMealsByIngredientsFragmentDirections.actionDetailsMealsByIngredientsFragmentToDetailsFragment(mealDto);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onItemByNameFailure(String errMessage) {
        Log.e(TAG, "onItemByNameFailure: " + errMessage );
    }

    @Override
    public void getNameOfTheMeal(String mealName) {
        presenter.getMealByName(mealName);
    }
}