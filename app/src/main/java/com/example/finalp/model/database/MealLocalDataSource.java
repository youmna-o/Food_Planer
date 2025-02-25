package com.example.finalp.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.finalp.model.data_models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
   // private LiveData<List<Meal>> storedMeals;
   private Flowable<List<Meal>> storedMeals;
    private static MealLocalDataSource repo = null;

    private MealLocalDataSource (Context _context) {
        this.context = _context;
        MealDataBase db = MealDataBase.getInstance (context.getApplicationContext ());
        mealDAO = db.getMeals();
        storedMeals = mealDAO.getAllMeals();

    }

    public Flowable<List<Meal>> getStoredData() {
        return storedMeals; }

    public static MealLocalDataSource getInstance (Context _context) {
        if (repo == null) {
            repo= new MealLocalDataSource (_context);
        }
        return repo;
    }
    public Completable delete (Meal meal) {
              return   mealDAO.deleteMeal(meal);
    }
    public Completable insert (Meal meal) {
        return   mealDAO.insertMeal(meal);
    }
    public Single<Boolean> isMealExist(Meal meal) {
        return mealDAO.isMealExist(meal.getIdMeal())
                .map(count -> count > 0)
                .subscribeOn(Schedulers.io());
    }

}
