package com.example.finalp.search.view;

import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;

import java.util.List;

public interface SearchView {
        void setMeal(List<Meal> mealList);
        void setCategory(List<Category> categoryList);
        void setArea(List<Area> areaList) ;
        void setIngredient(List<Ingredient> ingredientList);
}
