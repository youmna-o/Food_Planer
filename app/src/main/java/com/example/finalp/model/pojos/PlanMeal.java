package com.example.finalp.model.pojos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plan_table", primaryKeys = {"mealId", "date"})
public class PlanMeal {
    @NonNull
    private String mealId;

    @NonNull
    private String date;

    public PlanMeal(@NonNull String mealId, @NonNull String date) {
        this.mealId = mealId;
        this.date = date;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PlanMeal() {

    }
}
