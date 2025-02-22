package com.example.finalp.search.view;

import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;

import java.util.List;

public interface SearchView {
        void setMeal(List<Meal> mealList);
        void setCategory(List<Category> categoryList);
        void setArea(List<Area> areaList) ;
        void setIngredient(List<Ingredient> ingredientList);

}
