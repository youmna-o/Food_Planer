package com.example.finalp.meal_details.presenter;
import android.util.Log;

import com.example.finalp.meal_details.view.DetailsView;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {
        private DetailsView view;
        private Repo repo;

        public DetailsPresenter(DetailsView view, Repo repo) {
            this.view = view;
            this.repo = repo;
        }

        public void getMealDetails(String mealId) {
            repo.getAllMeaByID(new NetworkCallBack_meal() {

                @Override
                public void onSuccessgetMeal(Meal meal) {
                    view.showMealDetailsById(meal);
                }

                @Override
                public void onSuccessgetMealsOfCategory(List<Meal> mealList) {

                }

                @Override
                public void onSuccessIng(List<Ingredient> ingredients) {

                }

                @Override
                public void onSuccessCategory(List<Category> categorieslList) {

                }

                @Override
                public void onSuccessArea(List<Area> areaList) {

                }

                @Override
                public void onSuccessRundom(List<Meal> rundomMealList) {

                }

                @Override
                public void onFailure(String error) {

                }
            },mealId);
    }

    public void onMealClick(Meal meal) {
        repo.isMealExist(meal)
                .observeOn(AndroidSchedulers.mainThread()) // العودة إلى الـ UI Thread
                .subscribe(exists -> {
                    if (exists) {
                        repo.delete(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                    } else {
                        repo.insert(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                    }
                }, throwable -> {
                });
    }
}



