package com.example.food_planner.detailsMealScreen.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.FavoritePresenter.FavoritePresenter;
import com.example.food_planner.favoriteScreen.view.FavoriteView;
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.signupScreen.view.SignUpScreen;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class DetailsFragment extends Fragment implements IngredientsView, FavoriteView {
    private static final String TAG = "DetailsFragment";

    // UI Components
    private RecyclerView ingredientsRecyclerView;
    private TextView titleTextView, stepsTextView;
    private ImageView mealImageView, favoriteButton;

    IngredientsAdapter adapter;

    // Presenters
    private FavoritePresenter favoritePresenter;

    // Data
    private MealDto currentMeal;
    private YouTubePlayerView youTubePlayerView;

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
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences(SignUpScreen.UID_KEY, Context.MODE_PRIVATE);
                String uId = sharedPreferences.getString("LoggedIn", "error");
                if("error".equals(uId)){
                    Toast.makeText(getContext(), "You have to login to get this feature", Toast.LENGTH_SHORT).show();
                }else{
                    favoritePresenter.insert(currentMeal);
                    FirebaseUser user = Repo.getInstance(getContext()).getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
                    favoritePresenter.insertMealIntoFirebase(user.getUid(), currentMeal);
                    Toast.makeText(requireContext(), "Added to your favorites successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }

    private void initializeViews(View view) {
        ingredientsRecyclerView = view.findViewById(R.id.ingredients_rv);
        titleTextView = view.findViewById(R.id.tv_title_details);
        mealImageView = view.findViewById(R.id.details_imageView);
        favoriteButton = view.findViewById(R.id.iv_addToFav);
        stepsTextView = view.findViewById(R.id.tv_steps_implementation);
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

    }

    private void setupPresenters() {
        Repo repo = Repo.getInstance(requireContext());
        favoritePresenter = new FavoritePresenter(repo, this);
    }

    private void loadData() {
        currentMeal = DetailsFragmentArgs.fromBundle(getArguments()).getMealDto();
        String youtubeId = getYoutubeId(currentMeal.getStrYoutube());
        setupYouTubePlayer(youtubeId);
        displayMealDetails();
    }

    private void setupYouTubePlayer(String videoId) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }


    private void setupIngredientsAdapter(MealDto meal) {
        List<MealDto> ingredients = createIngredientsListFromMeal(meal);
        adapter = new IngredientsAdapter(ingredients, getContext());
        ingredientsRecyclerView.setAdapter(adapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Log.i(TAG, "setupIngredientsAdapter: "+ adapter.getItemCount());
    }

    private List<MealDto> createIngredientsListFromMeal(MealDto meal) {
        List<MealDto> ingredients = new ArrayList<>();
        MealDto ingredient1 = new MealDto();


        ingredient1.setStrIngredient1(meal.getStrIngredient1());
        ingredient1.setStrIngredient2(meal.getStrIngredient2());
        ingredient1.setStrIngredient3(meal.getStrIngredient3());
        ingredient1.setStrIngredient4(meal.getStrIngredient4());
        ingredient1.setStrIngredient5(meal.getStrIngredient5());
        ingredient1.setStrIngredient6(meal.getStrIngredient6());
        ingredient1.setStrIngredient7(meal.getStrIngredient7());
        ingredient1.setStrIngredient8(meal.getStrIngredient8());
        ingredient1.setStrIngredient9(meal.getStrIngredient9());
        ingredient1.setStrIngredient10(meal.getStrIngredient10());
        ingredient1.setStrIngredient11(meal.getStrIngredient11());
        ingredient1.setStrIngredient12(meal.getStrIngredient12());
        ingredient1.setStrIngredient13(meal.getStrIngredient13());
        ingredient1.setStrIngredient14(meal.getStrIngredient14());
        ingredient1.setStrIngredient15(meal.getStrIngredient15());
        ingredients.add(ingredient1);
        Log.i(TAG, "Ingredient1: " + ingredients.get(0).getStrIngredient1());
        return ingredients;
    }

    private void displayMealDetails() {
        titleTextView.setText(currentMeal.getStrMeal());
        stepsTextView.setText(currentMeal.getStrInstructions());
        Glide.with(requireContext())
                .load(currentMeal.getStrMealThumb())
                .into(mealImageView);


         setupIngredientsAdapter(currentMeal);
    }

    @Override
    public void getAllFavMeals(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseSuccess(List<MealDto> meals) {

    }

    @Override
    public void onFavoriteMealsRetrievedFromFirebaseFailure(String errMessage) {

    }

    @Override
    public void onAllIngredientsSuccess(ResponseMeals ingredients) {

    }

    @Override
    public void onAllIngredientsFailure(String errMessage) {

    }

    private String getYoutubeId(String link) {
        if (link != null && link.split("\\?v=").length > 1)
            return link.split("\\?v=")[1];
        else return "";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        youTubePlayerView.release();
    }
}