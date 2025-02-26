package com.example.finalp.model.database;

import android.content.Context;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private PlanMealDAO planeMealDAO;

    private Flowable<List<Meal>> storedMeals;
    private Flowable<List<PlanMeal>> storedPlans;
    private static MealLocalDataSource repo = null;

    private MealLocalDataSource (Context _context) {
        this.context = _context;
        MealDataBase db = MealDataBase.getInstance (context.getApplicationContext ());
        mealDAO = db.getMeals();
        planeMealDAO=db.getPlans();
        storedMeals = mealDAO.getAllMeals();
        storedPlans=planeMealDAO.getAllMeals();


    }
    public static MealLocalDataSource getInstance (Context _context) {
        if (repo == null) {
            repo= new MealLocalDataSource (_context);
        }
        return repo;
    }

    public Flowable<List<Meal>> getStoredData() {
        return storedMeals;
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

    public Flowable<List<PlanMeal>> getStoredPlans() {
        return storedPlans;
    }


    public Completable delete (PlanMeal meal) {
        return   planeMealDAO.deleteMeal(meal);
    }
    public Completable insert (PlanMeal meal) {
        return   planeMealDAO.insertMeal(meal);
    }

    public Single<Boolean> isMealExist(PlanMeal meal) {
        return planeMealDAO.isMealExist(meal.getMealId(),meal.getDate())
                .map(count -> count > 0)
                .subscribeOn(Schedulers.io());
    }
    public Single<List<String>> getMealIdsByDate(String date) {
        return planeMealDAO.getMealIdsByDate(date);
    }
    public Flowable<List<Meal>> getMealsByIds(List<String> mealIds) {
        return mealDAO.getMealsByIds(mealIds);
    }


}
