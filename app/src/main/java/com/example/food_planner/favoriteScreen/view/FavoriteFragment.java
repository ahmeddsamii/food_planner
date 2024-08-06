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

import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteView, onFavClickListener {
    RecyclerView recyclerView;

    FavoritePresenter presenter;
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
        Repo.getInstance(getContext()).getAllMeals().observe(getViewLifecycleOwner(), mealDtos -> {
            recyclerView.setAdapter(new FavoriteAdapter(mealDtos, getContext(),FavoriteFragment.this));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }


    @Override
    public void getAllFavMeals(LiveData<List<MealDto>> meals) {
        presenter.setData(meals);

        meals.observe(this, new Observer<List<MealDto>>() {
            @Override
            public void onChanged(List<MealDto> mealDtos) {
                recyclerView.setAdapter(new FavoriteAdapter(mealDtos,getContext(),FavoriteFragment.this));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
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

    }
}