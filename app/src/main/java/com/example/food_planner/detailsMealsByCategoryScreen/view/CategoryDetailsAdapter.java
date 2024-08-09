package com.example.food_planner.detailsMealsByCategoryScreen.view;

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
import com.example.food_planner.model.dto_repos.ResponseMeals;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.MealInfoDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder> {
    private List<MealDto> mealInfoDtos;
    private Context context;
    onMealsCategoryHomeClickListener listener;


    public CategoryDetailsAdapter(List<MealDto> mealInfoDtos, Context context, onMealsCategoryHomeClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mealInfoDtos = mealInfoDtos;
    }

    public void setData(List<MealDto> updatedList) {
        this.mealInfoDtos = updatedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_details_item, parent, false);
        return new CategoryDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsViewHolder holder, int position) {
        MealDto currentMeal = mealInfoDtos.get(position);
        Glide.with(context).load(currentMeal.getStrMealThumb()).into(holder.imageView);
        holder.title.setText(currentMeal.getStrMeal());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getNameOfTheMeal(currentMeal.getStrMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealInfoDtos.size();
    }

    static class CategoryDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public CategoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_category_details_title);
            imageView = itemView.findViewById(R.id.image_category_details);
        }
    }
}