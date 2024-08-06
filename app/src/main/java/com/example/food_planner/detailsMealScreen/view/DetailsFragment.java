package com.example.food_planner.detailsMealScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import com.example.food_planner.homePageScreen.presenter.IngredientPresenter;
import com.example.food_planner.model.dto_repos.ResponseIngredient;
import com.example.food_planner.model.dtos.MealDto;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;


public class DetailsFragment extends Fragment implements AllIngredientsView, FavoriteView {
    private static final String TAG = "DetailsFragment";

    // UI Components
    private RecyclerView ingredientsRecyclerView;
    private TextView titleTextView, stepsTextView;
    private ImageView mealImageView, favoriteButton;
    YouTubePlayerView youTubePlayerView;

    // Presenters
    private IngredientPresenter ingredientPresenter;
    private FavoritePresenter favoritePresenter;

    // Data
    private MealDto currentMeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupPresenters();
        loadData();
        setupListeners();
    }

    private void initializeViews(View view) {
        ingredientsRecyclerView = view.findViewById(R.id.ingredients_rv);
        titleTextView = view.findViewById(R.id.tv_title_details);
        mealImageView = view.findViewById(R.id.details_imageView);
        favoriteButton = view.findViewById(R.id.iv_addToFav);
        stepsTextView = view.findViewById(R.id.tv_steps_implementation);
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
    }

    private void setupPresenters() {
        Repo repo = Repo.getInstance(requireContext());
        ingredientPresenter = new IngredientPresenter(this, repo);
        favoritePresenter = new FavoritePresenter(repo, this);
    }

    private void loadData() {
        currentMeal = DetailsFragmentArgs.fromBundle(getArguments()).getMealDto();
        ingredientPresenter.getAllIngredients();
    }

    private void setupListeners() {
        favoriteButton.setOnClickListener(v -> addToFavorites());
    }

    private void addToFavorites() {
        favoritePresenter.insert(currentMeal);
        Toast.makeText(requireContext(), "Added to your favorites successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAllIngredientsSuccess(ResponseIngredient ingredients) {
        setupIngredientsAdapter(ingredients);
        displayMealDetails();
    }

    @Override
    public void onAllIngredientsFailure(String errMessage) {
        Log.e(TAG, "Failed to load ingredients: " + errMessage);
        // Consider showing an error message to the user
    }

    private void setupIngredientsAdapter(ResponseIngredient ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients.getIngredients(), requireContext());
        ingredientsRecyclerView.setAdapter(adapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void displayMealDetails() {
        titleTextView.setText(currentMeal.getStrMeal());
        stepsTextView.setText(currentMeal.getStrInstructions());
        Glide.with(requireContext())
                .load(currentMeal.getStrMealThumb())
                .into(mealImageView);
        String url = currentMeal.getStrYoutube();
        String[] parts = url.split("\\?", 2);
        if (parts.length > 1) {
            String result = parts[1];
            System.out.println(result); // Outputs: v=4aZr5hZXP_s
        }


        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(parts[1], 0);
            }
        });
    }

    @Override
    public void getAllFavMeals(LiveData<List<MealDto>> meals) {
        favoritePresenter.getAllFavMeals(meals);
    }
}