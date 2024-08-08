package com.example.food_planner.detailsMealScreen.view;

import android.content.Context;
import android.util.Log;
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
import com.example.food_planner.model.dtos.IngredientDto;
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientHolder> {
    //List<IngredientWithMeasure> ingredientWithMeasureList;
    List<MealDto> responseIngredient;
    private static final String TAG = "IngredientsAdapter";
    Context context;
    public IngredientsAdapter(List<MealDto> responseIngredient, Context context){
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



        holder.ingredient_details.setText(responseIngredient.get(position).getStrIngredient1());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + responseIngredient.get(position).getStrIngredient1() + "-Small.png")
                    .into(holder.imageView1);

        Log.i(TAG, "Ingredient1: " + responseIngredient.get(position).getStrIngredient1());

        if(!responseIngredient.get(position).getStrIngredient2().isEmpty()){
            holder.ingredient_details2.setText(responseIngredient.get(position).getStrIngredient2());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient2()+"-Small.png")
                    .into(holder.imageView2);
        }


        if(!responseIngredient.get(position).getStrIngredient3().isEmpty()){
            holder.ingredient_details3.setText(responseIngredient.get(position).getStrIngredient3());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient3()+"-Small.png")
                    .into(holder.imageView3);
        }

        if(!responseIngredient.get(position).getStrIngredient4().isEmpty()){
            holder.ingredient_details4.setText(responseIngredient.get(position).getStrIngredient4());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient4()+"-Small.png")
                    .into(holder.imageView4);
        }

        if(!responseIngredient.get(position).getStrIngredient5().isEmpty()){
            holder.ingredient_details5.setText(responseIngredient.get(position).getStrIngredient5());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient5()+"-Small.png")
                    .into(holder.imageView5);
        }

        if(!responseIngredient.get(position).getStrIngredient6().isEmpty()){
            holder.ingredient_details6.setText(responseIngredient.get(position).getStrIngredient6());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient6()+"-Small.png")
                    .into(holder.imageView6);
        }

        if(!responseIngredient.get(position).getStrIngredient7().isEmpty()){
            holder.ingredient_details7.setText(responseIngredient.get(position).getStrIngredient7());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient7()+"-Small.png")
                    .into(holder.imageView7);
        }


        if(!responseIngredient.get(position).getStrIngredient8().isEmpty()){
            holder.ingredient_details8.setText(responseIngredient.get(position).getStrIngredient8());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient8()+"-Small.png")
                    .into(holder.imageView8);
        }

        if(!responseIngredient.get(position).getStrIngredient9().isEmpty()){
            holder.ingredient_details9.setText(responseIngredient.get(position).getStrIngredient9());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient9()+"-Small.png")
                    .into(holder.imageView9);
        }


        if(!responseIngredient.get(position).getStrIngredient10().isEmpty()){
            holder.ingredient_details10.setText(responseIngredient.get(position).getStrIngredient10());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient10()+"-Small.png")
                    .into(holder.imageView10);
        }


        if(!responseIngredient.get(position).getStrIngredient11().isEmpty()){
            holder.ingredient_details11.setText(responseIngredient.get(position).getStrIngredient11());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient11()+"-Small.png")
                    .into(holder.imageView11);
        }

        if(!responseIngredient.get(position).getStrIngredient12().isEmpty()){
            holder.ingredient_details12.setText(responseIngredient.get(position).getStrIngredient12());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient12()+"-Small.png")
                    .into(holder.imageView12);
        }


        if(!responseIngredient.get(position).getStrIngredient13().isEmpty()){
            holder.ingredient_details13.setText(responseIngredient.get(position).getStrIngredient13());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient13()+"-Small.png")
                    .into(holder.imageView13);
        }


        if(!responseIngredient.get(position).getStrIngredient14().isEmpty()){
            holder.ingredient_details14.setText(responseIngredient.get(position).getStrIngredient14());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+responseIngredient.get(position).getStrIngredient14()+"-Small.png")
                    .into(holder.imageView14);
        }

        if(!responseIngredient.get(position).getStrIngredient15().isEmpty()) {
            holder.ingredient_details15.setText(responseIngredient.get(position).getStrIngredient15());
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + responseIngredient.get(position).getStrIngredient15() + "-Small.png")
                    .into(holder.imageView15);
        }





    }

    @Override
    public int getItemCount() {
        return responseIngredient.size();
    }

    class IngredientHolder extends RecyclerView.ViewHolder{
        //ImageView ingredientImage;
        CardView cardView_1,cardView_2,cardView_3,cardView_4,cardView_5,cardView_6,cardView_7,cardView_8,cardView_9,cardView_10,cardView_11,cardView_12,cardView_13,cardView_14,cardView_15;
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

            cardView_1 = itemView.findViewById(R.id.cardView_1);
            cardView_2 = itemView.findViewById(R.id.cardView_2);
            cardView_3 = itemView.findViewById(R.id.cardView_3);
            cardView_4 = itemView.findViewById(R.id.cardView_4);
            cardView_5 = itemView.findViewById(R.id.cardView_5);
            cardView_6 = itemView.findViewById(R.id.cardView_6);
            cardView_7 = itemView.findViewById(R.id.cardView_7);
            cardView_8 = itemView.findViewById(R.id.cardView_8);
            cardView_9 = itemView.findViewById(R.id.cardView_9);
            cardView_10 = itemView.findViewById(R.id.cardView_10);
            cardView_11 = itemView.findViewById(R.id.cardView_1);
            cardView_12 = itemView.findViewById(R.id.cardView_1);
            cardView_13 = itemView.findViewById(R.id.cardView_1);
            cardView_14 = itemView.findViewById(R.id.cardView_1);
            cardView_15 = itemView.findViewById(R.id.cardView_1);
        }
    }

}
