package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsPresenter.detailsMealsByIngredientsView.DetailsMealsByIngredientsPresenter;
import com.example.food_planner.model.dto_repos.ResponseMealByIngredientDto;
import com.example.food_planner.model.dtos.AllIngredientDto;


public class DetailsMealsByIngredientsFragment extends Fragment implements DetailsMealsByIngredientsView{

   RecyclerView mealsByIngredientsRv;
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
        DetailsMealsByIngredientsPresenter presenter = new DetailsMealsByIngredientsPresenter(Repo.getInstance(getContext()),this);
        presenter.getAllMealsByIngredients(ingredientDto.getStrIngredient());
    }

    @Override
    public void onAllMealsByIngredientsSuccess(ResponseMealByIngredientDto responseMealByIngredientDto) {
        DetailsMealsByIngredientsAdapter adapter = new DetailsMealsByIngredientsAdapter(responseMealByIngredientDto.getMeals(), getContext());
        mealsByIngredientsRv.setAdapter(adapter);
        mealsByIngredientsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onAllMealsByIngredientsFailure(String errMessage) {
        Toast.makeText(getContext(), errMessage, Toast.LENGTH_SHORT).show();
    }
}