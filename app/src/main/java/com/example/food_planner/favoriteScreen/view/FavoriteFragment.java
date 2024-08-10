package com.example.food_planner.favoriteScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.favoriteScreen.FavoritePresenter.FavoritePresenter;
import com.example.food_planner.model.dtos.MealDto;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteView, onFavClickListener {
    RecyclerView recyclerView;

    FavoritePresenter presenter;
    FirebaseUser user ;
    private static final String TAG = "FavoriteFragment";


    public FavoriteFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         recyclerView = view.findViewById(R.id.favorite_recyclerview);
        Log.i(TAG, "onViewCreated: "+Repo.getInstance(getContext()).getLocalData());

        presenter = new FavoritePresenter(Repo.getInstance(getContext()), this);
        //presenter.getLocalData();
        user = Repo.getInstance(getContext()).getFirebaseDataSource().getFirebaseAuth().getCurrentUser();
        presenter.fetchUserFavoriteMeals(user.getUid());


    }


    @Override
    public void getAllFavMeals(List<MealDto> meals) {
        FavoriteAdapter adapter = new FavoriteAdapter(meals, getContext() , this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onFavoriteMealsRetrieved(List<MealDto> meals) {
        FavoriteAdapter adapter = new FavoriteAdapter(meals, getContext() , this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onFavoriteMealsRetrievedFailure(String errMessage) {
        Log.i(TAG, "onFavoriteMealsRetrievedFailure: "+errMessage);
    }

    @Override
    public void onFavItemClicked(MealDto mealDto) {
        FavoriteFragmentDirections.ActionFavoriteFragmentToDetailsFragment action =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(mealDto);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void onFavItemDelete(MealDto mealDto) {
        presenter.delete(mealDto);
        presenter.deleteTheMealFromFirebase(mealDto.getIdMeal());
        presenter.fetchUserFavoriteMeals(user.getUid());

    }
}