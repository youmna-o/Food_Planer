package com.example.finalp.planner.view;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;

import java.util.List;

public interface PlannerView {
    void setMealsPlane(List<SavedMeal> mealList);
}
