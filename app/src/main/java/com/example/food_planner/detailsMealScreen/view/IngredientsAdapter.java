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
import com.example.food_planner.model.dtos.MealDto;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientHolder> {
    List<MealDto> responseIngredient;
    private static final String TAG = "IngredientsAdapter";
    Context context;

    public IngredientsAdapter(List<MealDto> responseIngredient, Context context) {
        this.responseIngredient = responseIngredient;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.ingredient_item, parent, false);
        IngredientHolder holder = new IngredientHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHolder holder, int position) {
        MealDto currentMeal = responseIngredient.get(position);

        // Ingredient 1
        if (currentMeal.getStrIngredient1() != null && !currentMeal.getStrIngredient1().isEmpty()) {
            holder.ingredient_details.setText(currentMeal.getStrIngredient1());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient1() + "-Small.png")
                    .into(holder.imageView1);
            holder.cardView_1.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_1.setVisibility(View.GONE);
        }

        // Ingredient 2
        if ( currentMeal.getStrIngredient2() != null && !currentMeal.getStrIngredient2().isEmpty()) {
            holder.ingredient_details2.setText(currentMeal.getStrIngredient2());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient2() + "-Small.png")
                    .into(holder.imageView2);
            holder.cardView_2.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_2.setVisibility(View.GONE);
        }

        // Ingredient 3
        if ( currentMeal.getStrIngredient3() != null && !currentMeal.getStrIngredient3().isEmpty()) {
            holder.ingredient_details3.setText(currentMeal.getStrIngredient3());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient3() + "-Small.png")
                    .into(holder.imageView3);
            holder.cardView_3.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_3.setVisibility(View.GONE);
        }

        // Ingredient 4
        if ( currentMeal.getStrIngredient4() != null && !currentMeal.getStrIngredient4().isEmpty()) {
            holder.ingredient_details4.setText(currentMeal.getStrIngredient4());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient4() + "-Small.png")
                    .into(holder.imageView4);
            holder.cardView_4.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_4.setVisibility(View.GONE);
        }

        // Ingredient 5
        if (currentMeal.getStrIngredient5() != null && !currentMeal.getStrIngredient5().isEmpty()) {
            holder.ingredient_details5.setText(currentMeal.getStrIngredient5());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient5() + "-Small.png")
                    .into(holder.imageView5);
            holder.cardView_5.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_5.setVisibility(View.GONE);
        }

        // Ingredient 6
        if ( currentMeal.getStrIngredient6() != null && !currentMeal.getStrIngredient6().isEmpty()) {
            holder.ingredient_details6.setText(currentMeal.getStrIngredient6());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient6() + "-Small.png")
                    .into(holder.imageView6);
            holder.cardView_6.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_6.setVisibility(View.GONE);
        }

        // Ingredient 7
        if (currentMeal.getStrIngredient7() != null && !currentMeal.getStrIngredient7().isEmpty()) {
            holder.ingredient_details7.setText(currentMeal.getStrIngredient7());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient7() + "-Small.png")
                    .into(holder.imageView7);
            holder.cardView_7.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_7.setVisibility(View.GONE);
        }

        // Ingredient 8
        if (currentMeal.getStrIngredient8() != null && !currentMeal.getStrIngredient8().isEmpty()) {
            holder.ingredient_details8.setText(currentMeal.getStrIngredient8());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient8() + "-Small.png")
                    .into(holder.imageView8);
            holder.cardView_8.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_8.setVisibility(View.GONE);
        }

        // Ingredient 9
        if (currentMeal.getStrIngredient9() != null&&!currentMeal.getStrIngredient9().isEmpty()) {
            holder.ingredient_details9.setText(currentMeal.getStrIngredient9());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient9() + "-Small.png")
                    .into(holder.imageView9);
            holder.cardView_9.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_9.setVisibility(View.GONE);
        }

        // Ingredient 10
        if ( currentMeal.getStrIngredient10() != null && !currentMeal.getStrIngredient10().isEmpty()) {
            holder.ingredient_details10.setText(currentMeal.getStrIngredient10());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient10() + "-Small.png")
                    .into(holder.imageView10);
            holder.cardView_10.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_10.setVisibility(View.GONE);
        }

        // Ingredient 11
        if ( currentMeal.getStrIngredient11() != null && !currentMeal.getStrIngredient11().isEmpty()) {
            holder.ingredient_details11.setText(currentMeal.getStrIngredient11());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient11() + "-Small.png")
                    .into(holder.imageView11);
            holder.cardView_11.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_11.setVisibility(View.GONE);
        }

        // Ingredient 12
        if ( currentMeal.getStrIngredient12() != null && !currentMeal.getStrIngredient12().isEmpty()) {
            holder.ingredient_details12.setText(currentMeal.getStrIngredient12());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient12() + "-Small.png")
                    .into(holder.imageView12);
            holder.cardView_12.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_12.setVisibility(View.GONE);
        }

        // Ingredient 13
        if ( currentMeal.getStrIngredient13() != null && !currentMeal.getStrIngredient13().isEmpty()) {
            holder.ingredient_details13.setText(currentMeal.getStrIngredient13());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient13() + "-Small.png")
                    .into(holder.imageView13);
            holder.cardView_13.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_13.setVisibility(View.GONE);
        }

        // Ingredient 14
        if ( currentMeal.getStrIngredient14() != null && !currentMeal.getStrIngredient14().isEmpty()) {
            holder.ingredient_details14.setText(currentMeal.getStrIngredient14());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient14() + "-Small.png")
                    .into(holder.imageView14);
            holder.cardView_14.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_14.setVisibility(View.GONE);
        }

        // Ingredient 15
        if ( currentMeal.getStrIngredient15() != null && !currentMeal.getStrIngredient15().isEmpty()) {
            holder.ingredient_details15.setText(currentMeal.getStrIngredient15());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient15() + "-Small.png")
                    .into(holder.imageView15);
            holder.cardView_15.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_15.setVisibility(View.GONE);
        }

        // Ingredient 16
        if ( currentMeal.getStrIngredient16() != null && !currentMeal.getStrIngredient16().isEmpty()) {
            holder.ingredient_details16.setText(currentMeal.getStrIngredient16());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient16() + "-Small.png")
                    .into(holder.imageView16);
            holder.cardView_16.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_16.setVisibility(View.GONE);
        }

        // Ingredient 17
        if ( currentMeal.getStrIngredient17() != null && !currentMeal.getStrIngredient17().isEmpty()) {
            holder.ingredient_details17.setText(currentMeal.getStrIngredient17());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient17() + "-Small.png")
                    .into(holder.imageView17);
            holder.cardView_17.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_17.setVisibility(View.GONE);
        }

        // Ingredient 18
        if ( currentMeal.getStrIngredient18() != null && !currentMeal.getStrIngredient18().isEmpty()) {
            holder.ingredient_details18.setText(currentMeal.getStrIngredient18());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient18() + "-Small.png")
                    .into(holder.imageView18);
            holder.cardView_18.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_18.setVisibility(View.GONE);
        }

        // Ingredient 19
        if ( currentMeal.getStrIngredient19() != null && !currentMeal.getStrIngredient19().isEmpty()) {
            holder.ingredient_details19.setText(currentMeal.getStrIngredient19());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient19() + "-Small.png")
                    .into(holder.imageView19);
            holder.cardView_19.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_19.setVisibility(View.GONE);
        }

        // Ingredient 20
        if ( currentMeal.getStrIngredient20() != null && !currentMeal.getStrIngredient20().isEmpty()) {
            holder.ingredient_details20.setText(currentMeal.getStrIngredient20());
            Glide.with(context)
                    .load("https://www.themealdb.com/images/ingredients/" + currentMeal.getStrIngredient20() + "-Small.png")
                    .into(holder.imageView20);
            holder.cardView_20.setVisibility(View.VISIBLE);
        } else {
            holder.cardView_20.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (responseIngredient != null) ? responseIngredient.size() : 0;
    }

    class IngredientHolder extends RecyclerView.ViewHolder {
        CardView cardView_1, cardView_2, cardView_3, cardView_4, cardView_5, cardView_6, cardView_7, cardView_8, cardView_9, cardView_10, cardView_11, cardView_12, cardView_13, cardView_14, cardView_15, cardView_16, cardView_17, cardView_18, cardView_19, cardView_20;
        TextView ingredient_details, ingredient_details2, ingredient_details3, ingredient_details4, ingredient_details5, ingredient_details6, ingredient_details7, ingredient_details8, ingredient_details9, ingredient_details10, ingredient_details11, ingredient_details12, ingredient_details13, ingredient_details14, ingredient_details15,ingredient_details16, ingredient_details17,ingredient_details18,ingredient_details19,ingredient_details20 ;
        ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, imageView18,imageView19,imageView20 ;

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
            ingredient_details16 = itemView.findViewById(R.id.ingredient_details16);
            ingredient_details17 = itemView.findViewById(R.id.ingredient_details17);
            ingredient_details18 = itemView.findViewById(R.id.ingredient_details18);
            ingredient_details19 = itemView.findViewById(R.id.ingredient_details19);
            ingredient_details20 = itemView.findViewById(R.id.ingredient_details20);
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
            imageView16 = itemView.findViewById(R.id.imageView16);
            imageView17 = itemView.findViewById(R.id.imageView17);
            imageView18 = itemView.findViewById(R.id.imageView18);
            imageView19 = itemView.findViewById(R.id.imageView19);
            imageView20 = itemView.findViewById(R.id.imageView20);
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
            cardView_11 = itemView.findViewById(R.id.cardView_11);
            cardView_12 = itemView.findViewById(R.id.cardView_12);
            cardView_13 = itemView.findViewById(R.id.cardView_13);
            cardView_14 = itemView.findViewById(R.id.cardView_14);
            cardView_15 = itemView.findViewById(R.id.cardView_15);
            cardView_16 = itemView.findViewById(R.id.cardView_16);
            cardView_17 = itemView.findViewById(R.id.cardView_17);
            cardView_18 = itemView.findViewById(R.id.cardView_18);
            cardView_19 = itemView.findViewById(R.id.cardView_19);
            cardView_20 = itemView.findViewById(R.id.cardView_20);
        }
    }
}