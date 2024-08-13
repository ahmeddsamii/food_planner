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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.model.dtos.AllIngredientDto;

import java.util.List;

public class AllIngredientsAdapter extends RecyclerView.Adapter<AllIngredientsAdapter.AllIngredientViewHolder> {
    List<AllIngredientDto> allIngredientDtoList;
    Context context;
    public AllIngredientsAdapter(List<AllIngredientDto> allIngredientDtoList , Context context){
        this.allIngredientDtoList = allIngredientDtoList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.ingredient_search_item, parent, false);
        AllIngredientViewHolder holder = new AllIngredientViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllIngredientViewHolder holder, int position) {
        AllIngredientDto ingredientDto = allIngredientDtoList.get(position);

        if (ingredientDto != null) {
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredientDto.getStrIngredient()+".png").into(holder.ingredientImage);
            holder.ingredientText.setText(ingredientDto.getStrIngredient());
            holder.ingredientCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchFragmentDirections.ActionSearchFragmentToDetailsMealsByIngredientsFragment action =
                            SearchFragmentDirections.actionSearchFragmentToDetailsMealsByIngredientsFragment(ingredientDto);
                    Navigation.findNavController(v).navigate(action);
                }
            });
        }else{
            holder.ingredientText.setVisibility(View.GONE);
            holder.ingredientImage.setVisibility(View.GONE);
            holder.ingredientCardView.setVisibility(View.GONE);
        }


    }

    public void setData(List<AllIngredientDto> allIngredients){
        this.allIngredientDtoList = allIngredients;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (allIngredientDtoList != null) ? allIngredientDtoList.size() : 0;
    }

    class AllIngredientViewHolder extends RecyclerView.ViewHolder{
        CardView ingredientCardView;
        ImageView ingredientImage;
        TextView ingredientText;
        public AllIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.iv_search_ingredient_item);
            ingredientText = itemView.findViewById(R.id.tv_search_ingredient_item);
            ingredientCardView = itemView.findViewById(R.id.cardview_search_ingredient_item);
        }

    }
}
