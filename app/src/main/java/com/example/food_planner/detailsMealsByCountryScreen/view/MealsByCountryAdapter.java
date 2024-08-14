package com.example.food_planner.detailsMealsByCountryScreen.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.dtos.MealInfoDto;

import java.util.List;

public class MealsByCountryAdapter extends RecyclerView.Adapter<MealsByCountryAdapter.MealHolder> {

    List<MealInfoDto> mealInfoDtos;
    Context context;
    onMealsByCountryClickListener onMealsByCountryClickListener;

    public MealsByCountryAdapter(List<MealInfoDto> mealInfoDtos, Context context, onMealsByCountryClickListener onMealsByCountryClickListener) {
        this.context = context;
        this.mealInfoDtos = mealInfoDtos;
        this.onMealsByCountryClickListener = onMealsByCountryClickListener;
    }

    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.meals_by_country_item, parent, false);
        MealHolder mealHolder = new MealHolder(v);
        return mealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {
        MealInfoDto meal = mealInfoDtos.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealsByCountryClickListener.getNameOfTheMeal(meal.getStrMeal());
                Log.i("TAG", "onClick: " + meal.getStrMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mealInfoDtos != null) ? mealInfoDtos.size() : 0;
    }

    class MealHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MealHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mealByCountryImageItem);
            title = itemView.findViewById(R.id.mealByCountryTextItem);
        }
    }
}
