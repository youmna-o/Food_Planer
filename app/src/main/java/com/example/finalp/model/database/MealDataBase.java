package com.example.finalp.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;


@Database(entities = {Meal.class, PlanMeal.class, SavedMeal.class},version = 3)
public abstract class MealDataBase extends  RoomDatabase{
    private  static MealDataBase instance = null ;
    public abstract  MealDAO getMeals();
    public abstract SavedMealDAO getSavedMeals();
    public abstract PlanMealDAO getPlans();
    public static synchronized  MealDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),MealDataBase.class, "mealsdb").build();
        }
        return instance;
    }

}


