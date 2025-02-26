package com.example.finalp.meal_details.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.model.pojos.Ingredient;

import java.util.ArrayList;
import java.util.List;


    public class IngredientOfMealAdapter extends RecyclerView.Adapter<IngredientOfMealAdapter.ViewHolder> {
        private final Context context;
        private List<Ingredient> ingredientList = new ArrayList<>();
        private static final String TAG = "Recycle";

        public IngredientOfMealAdapter(Context context, List<Ingredient> ingredientList) {
            this.context = context;
            if (ingredientList != null) {
                this.ingredientList.addAll(ingredientList);
            }
        }

        public void setIngredientList(List<Ingredient> newList) {
            if (newList == null) {
                Log.e("AdapterUpdate", "New ingredient list is null!");
                return;
            }

            Log.d("AdapterUpdate", "Updating ingredient list...");
            for (Ingredient ing : newList) {
                Log.d("AdapterUpdate", "Ingredient: " + ing.getStrIngredient());
            }

            this.ingredientList.clear();
            this.ingredientList.addAll(newList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.single_ingredient, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (ingredientList == null || ingredientList.isEmpty()) {
                Log.e("AdapterError", "ingredientList is null or empty in onBindViewHolder!");
                return;
            }

            Ingredient ingredient = ingredientList.get(position);
            if (ingredient == null || ingredient.getStrIngredient() == null) {
                Log.e("AdapterError", "Null ingredient at position: " + position);
                return;
            }

            holder.title.setText(ingredient.getStrIngredient());
            Log.d("inadapter", "********************************************Binding: " + ingredient.getStrIngredient());

        }

        @Override
        public int getItemCount() {
            return (ingredientList != null) ? ingredientList.size() : 0;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView title;
            ConstraintLayout constraintLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.ingredImage);
                title = itemView.findViewById(R.id.ingredname);
                constraintLayout = itemView.findViewById(R.id.singleIngred);
            }
        }
}
