package com.example.food_planner.planFragment.planView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.helpers.converters.ConvertPlanDtoToMealDto;
import com.example.food_planner.model.dtos.MealDto;
import com.example.food_planner.model.dtos.PlanDto;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlanMealHolder> {

    List<PlanDto> planDtos;
    Context context;
    OnPlanMealDeleteListener listener;
    public PlansAdapter(List<PlanDto> planDtos , Context context, OnPlanMealDeleteListener listener){
        this.context = context;
        this.planDtos = planDtos;
        this.listener = listener;
    }

    public void setUpdateList(List<PlanDto> planDtos){
        this.planDtos = planDtos;
        Log.d("PlansAdapter", "Updated list size: " + planDtos.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanMealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.plans_meal_item, parent, false);
        PlanMealHolder holder = new PlanMealHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealHolder holder, int position) {
        PlanDto planDto = planDtos.get(position);
        holder.title.setText(planDto.getStrMeal());
        Glide.with(context).load(planDto.getStrMealThumb()).into(holder.imageView);

        holder.deletePlanMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(planDto);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealDto mealDto = new MealDto();
                mealDto =  ConvertPlanDtoToMealDto.convertMealDtoToPlanDto(planDto,mealDto);
                PlanFragmentDirections.ActionPlanFragmentToDetailsFragment action =
                        PlanFragmentDirections.actionPlanFragmentToDetailsFragment(mealDto);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (planDtos != null) ? planDtos.size() : 0;
    }

    class PlanMealHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        CardView cardView;
        ImageButton deletePlanMeal;

        public PlanMealHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_planMealItem);
            imageView= itemView.findViewById(R.id.iv_planMealItem);
            cardView = itemView.findViewById(R.id.cv_planMealItem);
            deletePlanMeal = itemView.findViewById(R.id.delete_from_plans);
        }
    }
}
