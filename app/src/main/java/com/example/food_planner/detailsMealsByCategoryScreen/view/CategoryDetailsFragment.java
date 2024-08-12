package com.example.food_planner.detailsMealsByCategoryScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCategoryScreen.presenter.CategoryDetailsFragmentPresenter;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;

import java.util.ArrayList;


public class CategoryDetailsFragment extends Fragment implements CategoryDetailsView, onMealsCategoryHomeClickListener , onMealByNameView{

    RecyclerView recyclerView;
    CategoryDetailsAdapter adapter;

    CategoryDetailsFragmentPresenter presenter;

    public CategoryDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.details_category_recyclerview);
        adapter = new CategoryDetailsAdapter(new ArrayList<>(),getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String categoryTitle = CategoryDetailsFragmentArgs.fromBundle(getArguments()).getCategoryDto().getStrCategory();
        presenter = new CategoryDetailsFragmentPresenter(Repo.getInstance(getContext()),this , this);
        presenter.getAllMealsByCategory(categoryTitle);


    }

    @Override
    public void onSuccess(ResponseMeals responseMealInfoDto) {
        recyclerView.setAdapter(new CategoryDetailsAdapter(responseMealInfoDto.getMeals(),getContext(), this));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    @Override
    public void onFailure(String errMessage) {
        Log.e("TAG", "onFailure: " + errMessage );
    }



    @Override
    public void getNameOfTheMeal(String message) {
        presenter.getMealByName(message);
        Log.i("TAG", "the name is: "+message);

    }


    @Override
    public void onMealByNameSuccess(MealDto mealDto) {
        CategoryDetailsFragmentDirections.ActionCategoryDetailsFragmentToDetailsFragment action =
                CategoryDetailsFragmentDirections.actionCategoryDetailsFragmentToDetailsFragment(mealDto);
        Navigation.findNavController(getView()).navigate(action);


    }

    @Override
    public void onMealByNameFailure(String errMessage) {
        Log.i("TAG", "onMealByNameFailure: " +errMessage);
    }
}