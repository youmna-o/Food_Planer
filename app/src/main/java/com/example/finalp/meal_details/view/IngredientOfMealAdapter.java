package com.example.finalp.meal_details.view;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalp.R;
import com.example.finalp.model.pojos.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class IngredientOfMealAdapter extends RecyclerView.Adapter<IngredientOfMealAdapter.ViewHolder> {
    Context context;
    ArrayList<Pair<String, String>> ingredients;

    public IngredientOfMealAdapter(Context context, ArrayList<Pair<String, String>> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    public void setIngredientList(List<Pair<String, String>> newIngredients) {
        this.ingredients.clear();
        this.ingredients.addAll(newIngredients);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> ingredientPair = ingredients.get(position);
        holder.ingredientName.setText(ingredientPair.first);
        holder.measurementName.setText(ingredientPair.second);

        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientPair.first + "-Small.png";
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName, measurementName;
        ImageView ingredientImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            measurementName = itemView.findViewById(R.id.measurementName);
            ingredientImage = itemView.findViewById(R.id.ingredientImg);
        }
    }
}
