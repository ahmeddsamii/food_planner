package com.example.food_planner.searchScreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class MealsSearchAdapter extends RecyclerView.Adapter<MealsSearchAdapter.MealHolder> {

    List<MealDto> meals;
    Context context;

    public MealsSearchAdapter(List<MealDto> meals, Context context){
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.meals_search_item, parent, false);
        MealHolder holder = new MealHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {
        MealDto mealDto = meals.get(position);
        holder.title.setText(mealDto.getStrMeal());
        Glide.with(context).load(mealDto.getStrMealThumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    // New method to update the data
    public void setData(List<MealDto> newMeals) {
        this.meals = newMeals;
        notifyDataSetChanged();
    }

    class MealHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        ImageView imageView;

        public MealHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.meals_search_screen_cardview);
            title = itemView.findViewById(R.id.tv_search_meals_item);
            imageView = itemView.findViewById(R.id.iv_meals_search_item);
        }
    }
}