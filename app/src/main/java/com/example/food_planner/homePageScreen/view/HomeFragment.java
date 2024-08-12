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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.FavoritePresenter.FavoritePresenter;
import com.example.food_planner.favoriteScreen.view.FavoriteView;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.homePageScreen.presenter.HomePresenter;
import com.example.food_planner.homePageScreen.view.adapters.CategoriesAdapter;
import com.example.food_planner.model.dto_repos.ResponseCategory;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planPresenter.PlanPresenter;
import com.example.food_planner.planFragment.planView.OnPlansView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;


public class HomeFragment extends Fragment implements RandomMealView , AllCategoriesView, OnPlansView , FavoriteView {

    CardView cardView;
    ImageView imageView;
    TextView title, mealOfDayTv, tv_categories , tv_country;
    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;
    PlanPresenter planPresenter;
   FavoritePresenter favoritePresenter;


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

         cardView= view.findViewById(R.id.country_search_screen_cardview);
         imageView= view.findViewById(R.id.image_meal);
         title= view.findViewById(R.id.tv_meal_title);
         recyclerView = view.findViewById(R.id.category_recyclerview);
         mealOfDayTv = view.findViewById(R.id.tv_meal_of_the_day);
         tv_categories = view.findViewById(R.id.tv_categories);
        tv_country = view.findViewById(R.id.tv_country);
        if(!NetworkUtils.isInternetAvailable(getContext())){
            Toast.makeText(getContext(), "No internet, please check your connection", Toast.LENGTH_SHORT).show();
            imageView.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            mealOfDayTv.setVisibility(View.GONE);
            tv_categories.setVisibility(View.GONE);
            tv_country.setVisibility(View.GONE);
        }

        final Calendar c = java.util.Calendar.getInstance();

        int day = c.get(java.util.Calendar.DAY_OF_MONTH);

        HomePresenter homePresenter = new HomePresenter(this, Repo.getInstance(getContext()),this);
        homePresenter.getRandomMeal();
        homePresenter.getAllCategories();
        planPresenter = new PlanPresenter(Repo.getInstance(getContext()), this);
        planPresenter.fetchDataForPlanMealsFromFirebase(FirebaseAuth.getInstance().getUid(), day);
        favoritePresenter = new FavoritePresenter(Repo.getInstance(getContext()), this);
        favoritePresenter.fetchUserFavoriteMeals(FirebaseAuth.getInstance().getUid());


    }

    @Override
    public void onRandomMealSuccess(ResponseMeals randomMeal) {
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
        Toast.makeText(getContext(), "failed to fetch the data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlansSuccess(List<PlanDto> planDtos) {

    }

    @Override
    public void onPlansFailure(String errMessage) {

    }

    @Override
    public void onPlansSuccessFromFirebase(List<PlanDto> planDtos) {
        for (PlanDto planDto : planDtos){
            planPresenter.insertIntoPlans(planDto);
            Log.i(TAG, "inserted from Firebase to LocalDatabase: "+planDto.getStrMeal());
        }
    }

    @Override
    public void onPlansFailureFromFirebase(String errMessage) {

    }

    @Override
    public void getAllFavMeals(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseSuccess(List<MealDto> meals) {
        for(MealDto mealDto: meals){
            favoritePresenter.insert(mealDto);
            Log.i(TAG, "inserted from Firebase to LocalDatabase: "+ mealDto.getStrMeal()+"meal");
        }
    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseFailure(String errMessage) {

    }
}