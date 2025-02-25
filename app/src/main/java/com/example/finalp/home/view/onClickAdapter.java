package com.example.finalp.home.view;

import android.view.View;

import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;

public interface onClickAdapter {
void onMealClick(Meal meal,View view);
void onCategoryClick(Category category , View view);
void onAreaClick(Area area,View view);
void onIngClick(Ingredient ingredient,View view);
}
