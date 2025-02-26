package com.example.finalp.planner.presenter;

import android.util.Log;

import com.example.finalp.model.Repo;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;
import com.example.finalp.planner.view.PlannerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenter {
    PlannerView view ;
    Repo repo;

    public PlannerPresenter(PlannerView view, Repo repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getMeals(String date) {
        repo.getMealIdsByDate(date)
                .flatMapPublisher(mealIds -> repo.getMealsByIds(mealIds))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.setMealsPlane(meals),
                        error -> Log.e("PlannerPresenter", "Error fetching meals: " + error)
                );
    }


    public void deletePlanMeals(PlanMeal meal) {
        repo.deletePlans(meal)
                .andThen(repo.getMealIdsByDate(meal.getDate())) // بعد الحذف، نحصل على mealId الخاصة بهذا اليوم
                .flatMapPublisher(mealIds -> repo.getMealsByIds(mealIds)) // ثم نحصل على قائمة الوجبات المرتبطة بهذه الوجبات
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        updatedMeals -> view.setMealsPlane(updatedMeals), // تحديث الواجهة بـ List<Meal>
                        error -> Log.e("PlannerPresenter", "Error deleting meal: " + error)
                );
    }

    public void getMealsByDate(String date) {
        repo.getMealIdsByDate(date)
                .doOnSuccess(mealIds -> Log.d("PlannerPresenter", "Meal IDs retrieved: " + mealIds))
                .flatMapPublisher(mealIds -> {
                    if (mealIds.isEmpty()) {
                        Log.d("PlannerPresenter", "⚠ No meal IDs found for this date!");
                        return Flowable.just(new ArrayList<SavedMeal>());
                    }
                    return repo.getMealsByIds(mealIds);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            Log.d("PlannerPresenter", "Meals retrieved: " + meals);
                            view.setMealsPlane(meals);
                        },
                        throwable -> Log.e("PlannerPresenter", "Error fetching meals by date", throwable)
                );
    }




}
