package com.example.finalp.search.view;

import android.view.View;

import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;

public interface onClickAdapter {
void onMealClick(Meal meal,View view);
void onCategoryClick(Category category , View view);
void onAreaClick(Area area,View view);
void onIngClick(Ingredient ingredient,View view);
}
