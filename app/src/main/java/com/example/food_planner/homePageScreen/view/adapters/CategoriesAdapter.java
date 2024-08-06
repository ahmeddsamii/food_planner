package com.example.food_planner.homePageScreen.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.example.food_planner.R;
import com.example.food_planner.homePageScreen.view.HomeFragmentDirections;
import com.example.food_planner.model.dtos.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> implements Filterable {

    private List<CategoryDto> categories;
    private List<CategoryDto> filteredCategories;
    private Context context;

    public CategoriesAdapter(List<CategoryDto> categories, Context context) {
        this.categories = categories;
        this.filteredCategories = new ArrayList<>(categories); // Initialize with all categories
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryDto currentCategory = filteredCategories.get(position);
        holder.categoryTitle.setText(currentCategory.getStrCategory());
        Glide.with(context).load(currentCategory.getStrCategoryThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToCategoryDetailsFragment action =
                        HomeFragmentDirections.actionHomeFragmentToCategoryDetailsFragment(currentCategory);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredCategories.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                List<CategoryDto> filteredList = new ArrayList<>();

                if (filterPattern.isEmpty()) {
                    filteredList.addAll(categories);
                } else {
                    for (CategoryDto category : categories) {
                        if (category.getStrCategory().toLowerCase().contains(filterPattern)) {
                            filteredList.add(category);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredCategories.clear();
                filteredCategories.addAll((List<CategoryDto>) results.values);
                notifyDataSetChanged();
            }
        };
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

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categoryItemImage);
            categoryTitle = itemView.findViewById(R.id.categoryItemTv);
        }
    }
}