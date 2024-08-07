package com.example.food_planner.detailsMealsByCategoryScreen.view;

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
import com.example.food_planner.detailsMealsByCategoryScreen.presenter.CategoryDetailsFragmentPresenter;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;

import java.util.ArrayList;


public class CategoryDetailsFragment extends Fragment implements CategoryDetailsView, onMealsCategoryHomeClickListener{

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
        presenter = new CategoryDetailsFragmentPresenter(Repo.getInstance(getContext()),this);
        presenter.getAllMealsByCategory(categoryTitle);
        presenter.getMealByName(categoryTitle);

    }

    @Override
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto) {
        adapter.setData(responseMealInfoDto.getMealInfoList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    @Override
    public void onFailure(String errMessage) {

    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}