package com.example.finalp.model.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.finalp.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface FavMealDAO {
    @Query("SELECT * FROM favmeal_table")
    Flowable<List<Meal>> getFavMeals();
    @Insert
    Completable insertMeal(Meal meal);
    @Delete
    Completable deleteMeal(Meal meal);
    @Query("SELECT COUNT(*) FROM meal_table WHERE idMeal = :mealID")
    Single<Integer> isMealExist(String mealID);

}
