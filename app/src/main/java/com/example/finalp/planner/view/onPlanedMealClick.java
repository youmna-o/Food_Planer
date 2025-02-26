package com.example.finalp.planner.view;

import android.view.View;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.SavedMeal;

public interface onPlanedMealClick {
    void onPlanedMealClick(SavedMeal meal, View view);
}
