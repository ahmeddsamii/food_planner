package com.example.food_planner.searchScreen.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.searchScreen.presenter.SearchPresenter;
import com.example.food_planner.homePageScreen.view.adapters.CategoriesSearchAdapter;
import com.example.food_planner.homePageScreen.view.adapters.CountryAdapter;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dtos.CategoryDto;
import com.example.food_planner.model.dtos.CountryDto;
import com.example.food_planner.searchScreen.view.CategorySearchView;
import com.example.food_planner.searchScreen.view.CountrySearchView;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements CategorySearchView, CountrySearchView {

    RecyclerView mainRecyclerView;
    androidx.appcompat.widget.SearchView searchView;
    Chip categoryChip;
    Chip mealsChip;
    Chip ingredientsChip;
    Chip countriesChip;
    private static final String TAG = "SearchFragment";
    private CategoriesSearchAdapter categoriesAdapter;
    private SearchPresenter presenter;
    private CountryAdapter countryAdapter;
    List<CategoryDto> categories;
    List<CountryDto> countries;


    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(Repo.getInstance(getContext()), this, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainRecyclerView = view.findViewById(R.id.categories_search_list);
        searchView = view.findViewById(R.id.searchView);
        categoryChip = view.findViewById(R.id.category_chip);
        countriesChip = view.findViewById(R.id.country_chip);
        mealsChip = view.findViewById(R.id.meals_chip);
        ingredientsChip = view.findViewById(R.id.ingredient_chip);

        categoryChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show categories
                    mainRecyclerView.setVisibility(View.VISIBLE);
                    if (categoriesAdapter == null) {
                        categoriesAdapter = new CategoriesSearchAdapter(new ArrayList<>(), getContext());
                        presenter.getAllCategoriesSearchItems();
                    }
                    mainRecyclerView.setAdapter(categoriesAdapter);
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        });

        countriesChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show countries
                    mainRecyclerView.setVisibility(View.VISIBLE);
                    if (countryAdapter == null) {
                        countryAdapter = new CountryAdapter(new ArrayList<>(), getContext());
                        presenter.getAllCountriesSearchItems();
                    }
                    mainRecyclerView.setAdapter(countryAdapter);
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        });

        mealsChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainRecyclerView.setVisibility(View.GONE);
                }
            }
        });

        ingredientsChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainRecyclerView.setVisibility(View.GONE);
                }
            }
        });

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("CheckResult")
            @Override
            public boolean onQueryTextChange(String newText) {
                if (categoriesAdapter != null) {
                    //categoriesAdapter.getFilter().filter(newText);
                    Observable.fromIterable(categories).
                            filter(categoryDto -> categoryDto.getStrCategory().toLowerCase().contains(newText.toString()))
                            .toList()
                            .subscribe(filteredlist -> categoriesAdapter.setData(filteredlist),
                                    error -> Log.e(TAG, "Error filtering categories: " + error.getMessage()));
                }
                if (countryAdapter != null) {
                    Observable.fromIterable(countries)
                            .filter(countryDto -> countryDto.getStrArea().toLowerCase().contains(newText))
                            .toList()
                            .subscribe(filteredlist -> countryAdapter.setData(filteredlist),
                                    error-> Log.e(TAG, "onQueryTextChange: "+error.getMessage() ));
                }
                return true;
            }
        });
    }

    @Override
    public void onCategorySearchViewSuccess(List<CategoryDto> categories) {
        if (categoriesAdapter != null) {
            this.categories = categories;
            categoriesAdapter.setData(categories);
        }
    }

    @Override
    public void onCategorySearchViewFailure(String errMessage) {
        Log.i(TAG, "onCategorySearchViewFailure: " + errMessage);
    }

    @Override
    public void onCountrySearchViewSuccess(ResponseCountry responseCountry) {
        Log.i(TAG, "onCountrySearchViewSuccess: ");
        if (countryAdapter != null) {
            this.countries = responseCountry.getCountries();
            countryAdapter.setData(responseCountry.getCountries());
        } else {
            countryAdapter = new CountryAdapter(responseCountry.getCountries(), getContext());
            mainRecyclerView.setAdapter(countryAdapter);
        }
    }

    @Override
    public void onCountrySearchViewFailure(String errMessage) {
        Log.i(TAG, "onCountrySearchViewFailure: " + errMessage);
    }
}
