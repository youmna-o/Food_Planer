package com.example.finalp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.SavedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface SavedMealDAO {
    @Query("SELECT * FROM savedMeal_table")
    Flowable<List<SavedMeal>> getSavedMeals();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(SavedMeal meal);
    @Delete
    Completable deleteMeal(SavedMeal meal);
    @Query("SELECT COUNT(*) FROM savedMeal_table WHERE idMeal = :mealID")
    Single<Integer> isMealExist(String mealID);

    @Query("SELECT * FROM savedMeal_table WHERE idMeal IN (:mealIds)")
    Flowable<List<SavedMeal>> getMealsByIds(List<String> mealIds);

    @Query("DELETE FROM savedMeal_table")
    Completable deleteAllSavedMeals();
}
