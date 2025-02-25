package com.example.finalp.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.finalp.model.data_models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meal_table")
    Flowable<List<Meal>> getAllMeals();
    @Insert
    Completable insertMeal(Meal meal);
    @Delete
    Completable deleteMeal(Meal meal);
    @Query("SELECT COUNT(*) FROM meal_table WHERE idMeal = :mealID")
    Single<Integer> isMealExist(String mealID);
}

