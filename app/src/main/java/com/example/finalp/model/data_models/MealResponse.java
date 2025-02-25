package com.example.finalp.model.data_models;

import java.util.List;

public class MealResponse {
   //must same name of json object:
    private List<Meal> meals;
    public List<Meal> getMeals(){
        return meals;
    }

    public  Meal getMealDetails(int id){
        return meals.get(id);
    }

}