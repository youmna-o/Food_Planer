package com.example.finalp.home.view;

import android.view.View;

import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;

public interface onClickAdapter {
void onMealClick(Meal meal,View view);
void onCategoryClick(Category category , View view);
void onAreaClick(Area area,View view);
void onIngClick(Ingredient ingredient,View view);
}
