package com.example.finalp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.finalp.model.pojos.Meal;

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

    @Query("SELECT * FROM meal_table WHERE idMeal IN (:mealIds)")
    Flowable<List<Meal>> getMealsByIds(List<String> mealIds);

    @Query("DELETE FROM meal_table")
    Completable deleteAllMeals();


}

