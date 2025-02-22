package com.example.finalp.model.network;



import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;

import java.util.List;

public interface NetworkCallBack_meal {
    public void onSuccess(List<Meal> mealList);
    public void onSuccessgetMealsOfCategory(List<Meal> mealList);
    public  void onSuccessIng(List<Ingredient>ingredients);
    public void onSuccessCategory(List<Category> categorieslList);
    public void onSuccessArea(List<Area> areaList);
    public void onSuccessRundom(List<Meal> rundomMealList);
    public void onFailure(String error);
}

