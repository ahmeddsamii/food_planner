package com.example.food_planner.searchScreen.view;

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
import android.widget.Toast;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.searchScreen.presenter.SearchPresenter;
import com.example.food_planner.model.dto_repos.ResponseCountry;
import com.example.food_planner.model.dtos.CategoryDto;
import com.example.food_planner.model.dtos.CountryDto;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements CategorySearchView, CountrySearchView, MealsSearchByNameView {

    RecyclerView mainRecyclerView;
    androidx.appcompat.widget.SearchView searchView;
    Chip categoryChip;
    Chip mealsChip;
    Chip ingredientsChip;
    Chip countriesChip;
    private static final String TAG = "SearchFragment";
    private CategoriesSearchAdapter categoriesAdapter;
    private SearchPresenter presenter;
    private CountrySearchAdapter countrySearchAdapter;
    MealsSearchAdapter mealsSearchAdapter;
    List<CategoryDto> categories;
    List<CountryDto> countries;
    List<MealDto> meals;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(Repo.getInstance(getContext()), this, this, this);
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
                    mainRecyclerView.setVisibility(View.VISIBLE);
                    if (countrySearchAdapter == null) {
                        countrySearchAdapter = new CountrySearchAdapter(new ArrayList<>(), getContext());
                        presenter.getAllCountriesSearchItems();
                    }
                    mainRecyclerView.setAdapter(countrySearchAdapter);
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        });

        mealsChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mealsSearchAdapter == null) {
                        mealsSearchAdapter = new MealsSearchAdapter(new ArrayList<>(), getContext());
                    }
                    mainRecyclerView.setAdapter(mealsSearchAdapter);
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    // Clear the adapter data when the chip is checked
                    mealsSearchAdapter.setData(new ArrayList<>());
                    // Only perform search if there's text in the SearchView
                    if (!searchView.getQuery().toString().isEmpty()) {
                        presenter.getMealSearchByName(searchView.getQuery().toString());
                    }
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

            @Override
            public boolean onQueryTextChange(String newText) {
                if (categoriesAdapter != null && categoryChip.isChecked()) {
                    filterCategories(newText);
                }
                if (countrySearchAdapter != null && countriesChip.isChecked()) {
                    filterCountries(newText);
                }
                if (mealsChip.isChecked()) {
                    if (!newText.isEmpty()) {
                        presenter.getMealSearchByName(newText);


                    } else {
                        // Clear the adapter data when the search text is empty
                        if (mealsSearchAdapter != null) {
                            mealsSearchAdapter.setData(new ArrayList<>());
                        }
                    }
                }
                return true;
            }
        });
    }

    private void filterCategories(String newText) {
        Observable.fromIterable(categories)
                .filter(categoryDto -> categoryDto.getStrCategory().toLowerCase().contains(newText.toLowerCase()))
                .toList()
                .subscribe(filteredlist -> categoriesAdapter.setData(filteredlist),
                        error -> Log.e(TAG, "Error filtering categories: " + error.getMessage()));
    }

    private void filterCountries(String newText) {
        Observable.fromIterable(countries)
                .filter(countryDto -> countryDto.getStrArea().toLowerCase().contains(newText.toLowerCase()))
                .toList()
                .subscribe(filteredlist -> countrySearchAdapter.setData(filteredlist),
                        error-> Log.e(TAG, "Error filtering countries: " + error.getMessage()));
    }

    private void filterMeals(String newText){
        Observable.fromIterable(meals)
                .filter(mealDto -> mealDto.getStrMeal().toLowerCase().contains(newText.toLowerCase()))
                .toList()
                .subscribe(filteredList -> mealsSearchAdapter.setData(filteredList),
                        error -> Log.e(TAG, "Error filtering meals: "+error.getMessage() ));
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
        Log.e(TAG, "Category search failed: " + errMessage);
        Toast.makeText(getContext(), "Failed to load categories: " + errMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountrySearchViewSuccess(ResponseCountry responseCountry) {
        if (countrySearchAdapter != null) {
            this.countries = responseCountry.getCountries();
            countrySearchAdapter.setData(responseCountry.getCountries());
        }
    }

    @Override
    public void onCountrySearchViewFailure(String errMessage) {
        Log.e(TAG, "Country search failed: " + errMessage);
        Toast.makeText(getContext(), "Failed to load countries: " + errMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealSearchByNameSuccess(List<MealDto> meals) {
        if (mealsSearchAdapter != null) {
            this.meals = meals;
            mealsSearchAdapter.setData(meals);
        }
    }

    @Override
    public void onMealSearchByNameFailure(String message) {
        Log.e(TAG, "Meal search failed: " + message);
        Toast.makeText(getContext(), "Failed to search meals: " + message, Toast.LENGTH_SHORT).show();
    }
}