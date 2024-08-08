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
import com.example.food_planner.model.dtos.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoriesSearchAdapter extends RecyclerView.Adapter<CategoriesSearchAdapter.CategoryViewHolder> {

    private List<CategoryDto> categories;
    private List<CategoryDto> filteredCategories;
    private Context context;

    public CategoriesSearchAdapter(List<CategoryDto> categories, Context context) {
        this.categories = categories;
        this.filteredCategories = new ArrayList<>(categories); // Initialize with all categories
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_search_item, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryDto currentCategory = filteredCategories.get(position);
        holder.categoryTitle.setText(currentCategory.getStrCategory());
        Glide.with(context).load(currentCategory.getStrCategoryThumb()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToCategoryDetailsFragment action =
                        SearchFragmentDirections.actionSearchFragmentToCategoryDetailsFragment(currentCategory);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredCategories.size();
    }


    // Method to update data set externally
    public void setData(List<CategoryDto> categories) {
        this.categories = categories;
        this.filteredCategories = new ArrayList<>(categories);
        notifyDataSetChanged();
    }

    // ViewHolder class
    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView categoryTitle;
        CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_category_search_item);
            categoryTitle = itemView.findViewById(R.id.tv_search_category_item);
            cardView = itemView.findViewById(R.id.country_search_screen_cardview);
        }
    }
}
