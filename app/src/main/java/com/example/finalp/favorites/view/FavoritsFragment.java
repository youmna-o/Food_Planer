package com.example.finalp.favorites.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalp.R;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

public class FavoritsFragment extends Fragment implements NetworkCallBack_meal {

    public FavoritsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadData();
    }

    private void loadData() {
       // Repo repo = Repo.getInstance(MealRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(getContext()));
        //repo.getAllproducts(this);
    }

    @Override
    public void onSuccessIng(List<Ingredient> ingredients) {
        if (isAdded() && !isDetached()) {
            for (Ingredient ingredient : ingredients) {
                Log.i(TAG, "Product****************************: " + ingredient.getStrIngredient());
            }
        }
    }

    @Override
    public void onSuccessCategory(List<Category> categorieslList) {

    }

    @Override
    public void onSuccessArea(List<Area> areaList) {

    }

    @Override
    public void onSuccessRundom(List<Meal> rundomMealList) {

    }


    @Override
    public void onSuccessgetMeal(Meal meal) {

    }

    @Override
    public void onSuccessgetMealsOfCategory(List<Meal> mealList) {
        for (Meal meal: mealList) {
            Log.i(TAG, "Product****************************: " + meal.getStrInstructions());
        }
    }

    @Override
    public void onFailure(String error) {
        if (isAdded() && !isDetached()) { // تأكد أن الـ Fragment لا يزال موجودًا
            Log.e("Retrofit", "Error fetching meals: " + error);
        }
    }
}