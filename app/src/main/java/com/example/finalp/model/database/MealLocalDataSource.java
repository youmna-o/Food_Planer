package com.example.finalp.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.finalp.model.data_models.Meal;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;

    private static MealLocalDataSource repo = null;

    private MealLocalDataSource (Context _context) {
        this.context = _context;
        MealDataBase db = MealDataBase.getInstance (context.getApplicationContext ());
        mealDAO = db.getMeals();
        storedMeals = mealDAO.getAllMeals();

    }

    public LiveData<List<Meal>> getStoredData() {
        return storedMeals; }


    public static MealLocalDataSource getInstance (Context _context) {
        if (repo == null) {
            repo= new MealLocalDataSource (_context);
        }
        return repo;
    }
    public void delete (Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run () {
                mealDAO.deleteMeal(meal); }
        }).start();
    }
    public void insert(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
                }
        }).start();
    }
    public boolean isMealExist(Meal meal) {
        return mealDAO.isMealExist(meal.getIdMeal())>0;

    }

}
