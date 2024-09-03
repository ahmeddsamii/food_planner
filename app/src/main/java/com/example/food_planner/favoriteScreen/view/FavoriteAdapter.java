package com.example.food_planner.favoriteScreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteItemHolder>  {
    List<MealDto> meals;
    Context context;
    onFavClickListener listener;

    public FavoriteAdapter(List<MealDto> meals, Context context, onFavClickListener listener){
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.favorite_item,parent, false);
        FavoriteItemHolder holder = new FavoriteItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteItemHolder holder, int position) {
        MealDto currentMeal = meals.get(position);
        holder.title.setText(currentMeal.getStrMeal());
        Glide.with(context).load(currentMeal.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavItemClicked(currentMeal);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavItemDelete(currentMeal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (meals != null) ? meals.size() : 0;

    }


    class FavoriteItemHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        ImageButton btnDelete;

        public FavoriteItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favorite_imageView);
            title = itemView.findViewById(R.id.tv_favorite_title);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
