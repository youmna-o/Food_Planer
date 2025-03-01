package com.example.finalp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface PlanMealDAO {
        @Query("SELECT * FROM plan_table")
        Flowable<List<PlanMeal>> getAllMeals();
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        Completable insertMeal(PlanMeal meal);
        @Delete
        Completable deleteMeal(PlanMeal meal);
        @Query("SELECT COUNT(*) FROM plan_table WHERE mealId = :mealID AND date = :date")
        Single<Integer> isMealExist(String mealID, String date);

        @Query("SELECT mealId FROM plan_table WHERE date = :date")
        Single<List<String>> getMealIdsByDate(String date);

        @Query("DELETE FROM plan_table")
        Completable deleteAllPlans();

}
