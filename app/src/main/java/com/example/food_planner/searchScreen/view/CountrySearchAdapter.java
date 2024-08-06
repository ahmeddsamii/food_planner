package com.example.food_planner.searchScreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_planner.R;
import com.example.food_planner.model.dtos.CountryDto;

import java.util.ArrayList;
import java.util.List;

public class CountrySearchAdapter extends RecyclerView.Adapter<CountrySearchAdapter.CountryViewHolder> {

    private List<CountryDto> countries;
    private List<CountryDto> countriesFull;
    private Context context;

    public CountrySearchAdapter(List<CountryDto> countries, Context context){
        this.countries = countries;
        this.countriesFull = new ArrayList<>(countries); // Initialize the full list
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.country_search_item, parent, false);
        return new CountryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryDto currentCountry = countries.get(position);
        holder.title.setText(currentCountry.getStrArea());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealsByCountryScreenFragment action=
                        SearchFragmentDirections.actionSearchFragmentToMealsByCountryScreenFragment(currentCountry);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public void setData(List<CountryDto> newCountries) {
        this.countries = new ArrayList<>(newCountries); // Update the current list
        this.countriesFull = new ArrayList<>(newCountries); // Update the full list for filtering
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView country_flag;
        CardView cardView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_search_country_item);
            country_flag = itemView.findViewById(R.id.iv_country_search_item);
            cardView = itemView.findViewById(R.id.country_search_screen_cardview);
        }
    }
}
