package com.example.food_planner.detailsMealsByCategoryScreen.view;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder> {
    private List<MealInfoDto> mealInfoDtos;
    private List<MealInfoDto> filteredList;
    private Context context;
    onMealsCategoryHomeClickListener listener;


    public CategoryDetailsAdapter(List<MealInfoDto> mealInfoDtos, Context context, onMealsCategoryHomeClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mealInfoDtos = new ArrayList<>(mealInfoDtos);
        this.filteredList = new ArrayList<>(mealInfoDtos);
    }

    public void setData(List<MealInfoDto> updatedList) {
        this.mealInfoDtos = new ArrayList<>(updatedList);
        this.filteredList = new ArrayList<>(updatedList);
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
        MealInfoDto currentMeal = filteredList.get(position);
        Glide.with(context).load(currentMeal.getStrMealThumb()).into(holder.imageView);
        holder.title.setText(currentMeal.getStrMeal());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getMessage("You have to search on this meal to get more details");
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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