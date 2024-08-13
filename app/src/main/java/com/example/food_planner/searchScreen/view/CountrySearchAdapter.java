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

import com.bumptech.glide.Glide;
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
        holder.country_flag.setImageResource(getImage(currentCountry.getStrArea()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealsByCountryScreenFragment action=
                        SearchFragmentDirections.actionSearchFragmentToMealsByCountryScreenFragment(currentCountry);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    int getImage(String cuisine)
    {
        int flag;
        switch (cuisine)
        {
            case "American":
                return R.drawable.us;
            case "British":
                return R.drawable.uk;
            case "Canadian":
                return R.drawable.canada;
            case "Chinese":
                return R.drawable.china;
            case "Croatian":
                return R.drawable.croatian;
            case "Dutch":
                return R.drawable.netherlands;
            case "Egyptian":
                return R.drawable.egypt;
            case "Filipino":
                return R.drawable.philippines;
            case "French":
                return R.drawable.france;
            case "Greek":
                return R.drawable.greek;
            case "Indian":
                return R.drawable.indian;
            case "Irish":
                return R.drawable.irish;
            case "Italian":
                return R.drawable.italy;
            case "Jamaican":
                return R.drawable.jamaican;
            case "Japanese":
                return R.drawable.japan;
            case "Kenyan":
                return R.drawable.kenya;
            case "Malaysian":
                return R.drawable.malaysia;
            case "Mexican":
                return R.drawable.mexican;
            case "Moroccan":
                return R.drawable.morocco;
            case "Polish":
                return R.drawable.polish;
            case "Portuguese":
                return R.drawable.portuguese;
            case "Russian":
                return R.drawable.russia;
            case "Spanish":
                return R.drawable.spanish;
            case "Thai":
                return R.drawable.thailand;
            case "Tunisian":
                return R.drawable.tunisian;
            case "Turkish":
                return R.drawable.turkey;
            case "Vietnamese":
                return R.drawable.vietnam;
            default:
                return R.drawable.palestine;
        }
    }

    @Override
    public int getItemCount() {
        return (countries != null) ? countries.size() : 0;
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
