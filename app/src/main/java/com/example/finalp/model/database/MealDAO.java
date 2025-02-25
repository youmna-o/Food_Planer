package com.example.finalp.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.finalp.model.data_models.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meal_table")
    LiveData<List<Meal>> getAllMeals();
    @Insert
    void insertMeal(Meal meal);
    @Delete
    void deleteMeal(Meal meal);
    @Query("SELECT COUNT(*) FROM meal_table WHERE idMeal = :mealID")
    int isMealExist(String mealID);
}

