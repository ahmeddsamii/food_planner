package com.example.food_planner.homePageScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.homePageScreen.presenter.HomePresenter;
import com.example.food_planner.homePageScreen.view.adapters.CategoriesAdapter;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseRandomMeal;


public class HomeFragment extends Fragment implements RandomMealView , AllCategoriesView{

    CardView cardView;
    ImageView imageView;
    TextView title;
    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;


    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         cardView= view.findViewById(R.id.cardview);
         imageView= view.findViewById(R.id.image_meal);
         title= view.findViewById(R.id.tv_meal_title);
         recyclerView = view.findViewById(R.id.category_recyclerview);


        HomePresenter homePresenter = new HomePresenter(this, Repo.getInstance(getContext()),this);
        homePresenter.getRandomMeal();
        homePresenter.getAllCategories();
    }

    @Override
    public void onRandomMealSuccess(ResponseRandomMeal randomMeal) {
        Glide.with(this).load(randomMeal.getMeals().get(0).getStrMealThumb()).into(imageView);
        title.setText(randomMeal.getMeals().get(0).getStrMeal());
        Log.i(TAG, "onSuccess: "+randomMeal.getMeals().get(0).getStrMeal());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToDetailsFragment action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(randomMeal.getMeals().get(0));
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public void onAllCategoriesSuccess(ResponseCategory category) {
        Log.i(TAG, "onAllCategoriesSuccess: "+category.getCategories().get(0).getStrCategory());
        CategoriesAdapter adapter = new CategoriesAdapter(category.getCategories(),getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onAllCategoriesFailure(String errMessage) {
        Log.i(TAG, "onAllCategoriesFailure: "+errMessage);
    }

    @Override
    public void onRandomMealFailure(String errMessage) {
        Toast.makeText(getContext(), errMessage, Toast.LENGTH_SHORT).show();
    }
}