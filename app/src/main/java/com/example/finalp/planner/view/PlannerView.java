package com.example.finalp.planner.view;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;

import java.util.List;

public interface PlannerView {
    void setMealsPlane(List<Meal> mealList);
}
