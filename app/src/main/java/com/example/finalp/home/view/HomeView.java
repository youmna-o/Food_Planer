package com.example.finalp.home.view;



import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Meal;

import java.util.List;

public interface HomeView {
    void setMeal(List<Meal> mealList);
    void setCategory(List<Category> categoryList);
    void setArea(List<Area> areaList);
    void setRundom(List<Meal> rundomList);
    void showOfflineFragment();
}
