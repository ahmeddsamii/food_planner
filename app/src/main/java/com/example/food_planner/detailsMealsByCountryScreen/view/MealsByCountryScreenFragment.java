package com.example.food_planner.detailsMealsByCountryScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.detailsMealsByCountryScreen.presenter.MealsByCountryPresenter;
import com.example.food_planner.model.dto_repos.ResponseMealInfoDto;

import java.util.ArrayList;


public class MealsByCountryScreenFragment extends Fragment implements onMealsByCountryView{

    RecyclerView recyclerView;
    MealsByCountryPresenter presenter;

    public MealsByCountryScreenFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_by_country_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mealsByCountryRv);
        MealsByCountryAdapter adapter = new MealsByCountryAdapter(new ArrayList<>(), getContext());
        String countryName = MealsByCountryScreenFragmentArgs.fromBundle(getArguments()).getCountryDto().getStrArea();
        presenter = new MealsByCountryPresenter(Repo.getInstance(getContext()), this);
        presenter.getMealsByCountry(countryName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public void onSuccess(ResponseMealInfoDto responseMealInfoDto) {
        recyclerView.setAdapter(new MealsByCountryAdapter(responseMealInfoDto.getMealInfoList(),getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public void onFailure(String errMessage) {

    }
}