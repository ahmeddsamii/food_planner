package com.example.food_planner.detailsMealScreen.view;

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
import com.example.food_planner.model.dtos.IngredientDto;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientHolder> {
    //List<IngredientWithMeasure> ingredientWithMeasureList;
    List<IngredientDto> responseIngredient;
    Context context;
    public IngredientsAdapter(List<IngredientDto> responseIngredient, Context context){
        this.responseIngredient = responseIngredient;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.ingredient_item,parent,false);
        IngredientHolder holder = new IngredientHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHolder holder, int position) {
        IngredientDto ingredient = responseIngredient.get(position);
        if(ingredient.getStrIngredient1() != null){
            holder.ingredient_details.setText(ingredient.getStrIngredient1());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient1()+"-Small.png")
                    .into(holder.imageView1);
        }else {
            holder.ingredient_details.setVisibility(View.GONE);
            holder.imageView1.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient1());
        }

        if(ingredient.getStrIngredient2() != null){
            holder.ingredient_details2.setText(ingredient.getStrIngredient2());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient2()+"-Small.png")
                    .into(holder.imageView2);
        }else {
            holder.ingredient_details2.setVisibility(View.GONE);
            holder.imageView2.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient2());
        }

        if(ingredient.getStrIngredient3() != null){
            holder.ingredient_details3.setText(ingredient.getStrIngredient3());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient3()+"-Small.png")
                    .into(holder.imageView3);
        }else {
            holder.ingredient_details3.setVisibility(View.GONE);
            holder.imageView3.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient3());
        }

        if(ingredient.getStrIngredient4() != null){
            holder.ingredient_details4.setText(ingredient.getStrIngredient4());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient4()+"-Small.png")
                    .into(holder.imageView4);
        }else {
            holder.ingredient_details4.setVisibility(View.GONE);
            holder.imageView4.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient4());
        }

        if(ingredient.getStrIngredient5() != null){
            holder.ingredient_details5.setText(ingredient.getStrIngredient5());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient5()+"-Small.png")
                    .into(holder.imageView5);
        }else {
            holder.ingredient_details5.setVisibility(View.GONE);
            holder.imageView5.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient5());
        }

        if(ingredient.getStrIngredient6() != null){
            holder.ingredient_details6.setText(ingredient.getStrIngredient6());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient6()+"-Small.png")
                    .into(holder.imageView6);
        }else {
            holder.ingredient_details6.setVisibility(View.GONE);
            holder.imageView6.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient6());
        }

        if(ingredient.getStrIngredient7() != null){
            holder.ingredient_details7.setText(ingredient.getStrIngredient7());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient7()+"-Small.png")
                    .into(holder.imageView7);
        }else {
            holder.ingredient_details7.setVisibility(View.GONE);
            holder.imageView7.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient7());
        }


        if(ingredient.getStrIngredient8() != null){
            holder.ingredient_details8.setText(ingredient.getStrIngredient8());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient8()+"-Small.png")
                    .into(holder.imageView8);
        }else {
            holder.ingredient_details8.setVisibility(View.GONE);
            holder.imageView8.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient8());
        }

        if(ingredient.getStrIngredient9() != null){
            holder.ingredient_details9.setText(ingredient.getStrIngredient9());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient9()+"-Small.png")
                    .into(holder.imageView9);
        }else {
            holder.ingredient_details9.setVisibility(View.GONE);
            holder.imageView9.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient9());
        }


        if(ingredient.getStrIngredient10() != null){
            holder.ingredient_details10.setText(ingredient.getStrIngredient10());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient10()+"-Small.png")
                    .into(holder.imageView10);
        }else {
            holder.ingredient_details10.setVisibility(View.GONE);
            holder.imageView10.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient10());
        }

        if(ingredient.getStrIngredient11() != null){
            holder.ingredient_details11.setText(ingredient.getStrIngredient11());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient11()+"-Small.png")
                    .into(holder.imageView11);
        }else {
            holder.ingredient_details11.setVisibility(View.GONE);
            holder.imageView11.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient11());
        }

        if(ingredient.getStrIngredient12() != null){
            holder.ingredient_details12.setText(ingredient.getStrIngredient12());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient12()+"-Small.png")
                    .into(holder.imageView12);
        }else {
            holder.ingredient_details12.setVisibility(View.GONE);
            holder.imageView12.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient12());
        }

        if(ingredient.getStrIngredient13() != null){
            holder.ingredient_details13.setText(ingredient.getStrIngredient13());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient13()+"-Small.png")
                    .into(holder.imageView13);
        }else {
            holder.ingredient_details13.setVisibility(View.GONE);
            holder.imageView13.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient13());
        }


        if(ingredient.getStrIngredient14() != null){
            holder.ingredient_details14.setText(ingredient.getStrIngredient14());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient14()+"-Small.png")
                    .into(holder.imageView14);
        }else {
            holder.ingredient_details14.setVisibility(View.GONE);
            holder.imageView14.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient14());
        }

        if(ingredient.getStrIngredient15() != null){
            holder.ingredient_details15.setText(ingredient.getStrIngredient15());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient15()+"-Small.png")
                    .into(holder.imageView15);
        }else {
            holder.ingredient_details14.setVisibility(View.GONE);
            holder.imageView15.setVisibility(View.GONE);
            responseIngredient.remove(ingredient.getStrIngredient15());
        }





    }

    @Override
    public int getItemCount() {
        return responseIngredient.size();
    }

    class IngredientHolder extends RecyclerView.ViewHolder{
        //ImageView ingredientImage;
        TextView ingredient_details,ingredient_details2,ingredient_details3,ingredient_details4,ingredient_details5,ingredient_details6,ingredient_details7,ingredient_details8,ingredient_details9,ingredient_details10, ingredient_details11, ingredient_details12,ingredient_details13, ingredient_details14,ingredient_details15;

        ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15;
        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_details = itemView.findViewById(R.id.ingredient_details);
            ingredient_details2 = itemView.findViewById(R.id.ingredient_details2);
            ingredient_details3 = itemView.findViewById(R.id.ingredient_details3);
            ingredient_details4 = itemView.findViewById(R.id.ingredient_details4);
            ingredient_details5 = itemView.findViewById(R.id.ingredient_details5);
            ingredient_details6 = itemView.findViewById(R.id.ingredient_details6);
            ingredient_details7 = itemView.findViewById(R.id.ingredient_details7);
            ingredient_details8 = itemView.findViewById(R.id.ingredient_details8);
            ingredient_details9 = itemView.findViewById(R.id.ingredient_details9);
            ingredient_details10 = itemView.findViewById(R.id.ingredient_details10);
            ingredient_details11 = itemView.findViewById(R.id.ingredient_details11);
            ingredient_details12 = itemView.findViewById(R.id.ingredient_details12);
            ingredient_details13 = itemView.findViewById(R.id.ingredient_details13);
            ingredient_details14 = itemView.findViewById(R.id.ingredient_details14);
            ingredient_details15 = itemView.findViewById(R.id.ingredient_details15);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            imageView4 = itemView.findViewById(R.id.imageView4);
            imageView5 = itemView.findViewById(R.id.imageView5);
            imageView6 = itemView.findViewById(R.id.imageView6);
            imageView7 = itemView.findViewById(R.id.imageView7);
            imageView8 = itemView.findViewById(R.id.imageView8);
            imageView9 = itemView.findViewById(R.id.imageView9);
            imageView10 = itemView.findViewById(R.id.imageView10);
            imageView11 = itemView.findViewById(R.id.imageView11);
            imageView12 = itemView.findViewById(R.id.imageView12);
            imageView13 = itemView.findViewById(R.id.imageView13);
            imageView14 = itemView.findViewById(R.id.imageView14);
            imageView15 = itemView.findViewById(R.id.imageView15);
        }
    }

}
