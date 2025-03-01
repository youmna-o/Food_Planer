package com.example.finalp.model.database;

import android.content.Context;

import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private PlanMealDAO planeMealDAO;
    private SavedMealDAO savedMealDAO;

    private Flowable<List<Meal>> storedMeals;
    private Flowable<List<PlanMeal>> storedPlans;
    private Flowable<List<SavedMeal>> savedMeals;
    private static MealLocalDataSource repo = null;

    private MealLocalDataSource (Context _context) {
        this.context = _context;
        MealDataBase db = MealDataBase.getInstance (context.getApplicationContext ());
        mealDAO = db.getMeals();
        planeMealDAO=db.getPlans();
        savedMealDAO=db.getSavedMeals();


        storedMeals = mealDAO.getAllMeals();
        storedPlans=planeMealDAO.getAllMeals();
        savedMeals=savedMealDAO.getSavedMeals();


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
        return   mealDAO.insertMeal(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Single<Boolean> isMealExist(Meal meal) {
        return mealDAO.isMealExist(meal.getIdMeal())
                .map(count -> count > 0)
                .subscribeOn(Schedulers.io());
    }

    //////////////////////////////////////////////////


    public Flowable<List<SavedMeal>> getSavedData() {
        return savedMeals;
    }

    public Completable delete (SavedMeal meal) {
        return   savedMealDAO.deleteMeal(meal);
    }
    public Completable insert (SavedMeal meal) {
        return   savedMealDAO.insertMeal(meal);
    }
    public Single<Boolean> isMealExist(SavedMeal meal) {
        return savedMealDAO.isMealExist(meal.getIdMeal())
                .map(count -> count > 0)
                .subscribeOn(Schedulers.io());
    }
//////////////////////////////////////////////////////////

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
    public Flowable<List<SavedMeal>> getMealsByIds(List<String> mealIds) {
        return savedMealDAO.getMealsByIds(mealIds);
    }
    ///////////////////////////////////////////
    public Completable clearAllData() {
        return Completable.fromAction(() -> {
            mealDAO.deleteAllMeals().blockingAwait();
            planeMealDAO.deleteAllPlans().blockingAwait();
            savedMealDAO.deleteAllSavedMeals().blockingAwait();
        }).subscribeOn(Schedulers.io());
    }


}
