package com.example.finalp.search.view;

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

import com.bumptech.glide.Glide;
import com.example.finalp.R;
import com.example.finalp.model.pojos.Ingredient;

import java.util.ArrayList;
import java.util.List;



public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private final Context context;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private final onClickAdapter listener;
    private static final String TAG = "Recycle";

    public IngredientAdapter(Context context, List<Ingredient> ingredientList, onClickAdapter listener) {
        this.context = context;
        this.listener = listener;
        if (ingredientList != null) {
            this.ingredientList.addAll(ingredientList);
        }
    }

    public void setIngredientList(List<Ingredient> newList) {

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
        Ingredient ingredient = ingredientList.get(position);
        holder.title.setText(ingredient.getStrIngredient());
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + "-Small.png";
        Glide.with(context)
                .load(imageUrl)
                .into(holder.image);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                listener.onIngClick(ingredientList.get(position),view);
            }

        });
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
