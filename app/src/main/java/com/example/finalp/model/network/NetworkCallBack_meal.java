package com.example.finalp.model.network;



import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;

import java.util.List;

public interface NetworkCallBack_meal {
    public void onSuccessgetMeal(Meal meal);
    public void onSuccessgetMealsOfCategory(List<Meal> mealList);
    public  void onSuccessIng(List<Ingredient>ingredients);
    public void onSuccessCategory(List<Category> categorieslList);
    public void onSuccessArea(List<Area> areaList);
    public void onSuccessRundom(List<Meal> rundomMealList);
    public void onFailure(String error);
}

