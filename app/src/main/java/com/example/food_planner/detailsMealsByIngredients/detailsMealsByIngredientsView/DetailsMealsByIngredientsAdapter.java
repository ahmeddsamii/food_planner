package com.example.food_planner.detailsMealsByIngredients.detailsMealsByIngredientsView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.dtos.MealByIngredientDto;

import java.util.List;

public class DetailsMealsByIngredientsAdapter extends RecyclerView.Adapter<DetailsMealsByIngredientsAdapter.MealsByIngredientsHolder> {

    List<MealByIngredientDto> meals;
    Context context;
    onMealsByIngredientsClickListener listener;
    public DetailsMealsByIngredientsAdapter(List<MealByIngredientDto> meals , Context context ,onMealsByIngredientsClickListener listener){
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealsByIngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.meals_by_ingredients_item, parent, false);
        MealsByIngredientsHolder holder = new MealsByIngredientsHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByIngredientsHolder holder, int position) {
        MealByIngredientDto meal = meals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getNameOfTheMeal(meal.getStrMeal());
            }
        });
    }

    public void setData(List<MealByIngredientDto> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MealsByIngredientsHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public MealsByIngredientsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.meals_by_ingredients_iv);
            title = itemView.findViewById(R.id.meals_by_ingredients_tv);
        }
    }
}
