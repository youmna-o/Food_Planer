package com.example.finalp.favorites.view;



import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Meal;

import java.util.List;

public interface MyView {
    void setMeal(List<Meal> mealList);
    void setCategory(List<Category> categoryList);
    void setArea(List<Area> areaList);
    void setRundom(List<Meal> rundomList);
}
