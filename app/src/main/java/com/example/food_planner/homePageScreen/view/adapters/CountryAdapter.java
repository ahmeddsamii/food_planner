package com.example.food_planner.homePageScreen.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_planner.R;
import com.example.food_planner.model.dtos.CountryDto;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<CountryDto> countries;
    private List<CountryDto> countriesFull;
    private Context context;

    public CountryAdapter(List<CountryDto> countries, Context context){
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

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_search_country_item);
            country_flag = itemView.findViewById(R.id.iv_country_search_item);
        }
    }
}
