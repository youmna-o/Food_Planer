package com.example.finalp.home.view;

import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Meal;

public interface onClickAdapter {
void onMealClick(Meal meal);
void onCategoryClick(Category category);
void onAreaClick(Area area);
}
