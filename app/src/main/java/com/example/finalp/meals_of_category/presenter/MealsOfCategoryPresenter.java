package com.example.finalp.meals_of_category.presenter;

import com.example.finalp.meals_of_category.view.MealsOfCategoryView;
import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsOfCategoryPresenter {

        private MealsOfCategoryView view;
        private Repo repo;

        public MealsOfCategoryPresenter (MealsOfCategoryView view, Repo repo){
            this.view=view;
            this.repo=repo;
        }

        public void getMealsOfThisCat(String cat){
            repo.getAllMealsofThisCategory(new NetworkCallBack_meal() {


                @Override
                public void onSuccessgetMeal(Meal meal) {

                }

                @Override
                public void onSuccessgetMealsOfCategory(List<Meal> mealList) {
                    view.setMeal(mealList);
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
            }, cat);
        }
        public void getMealsOfThisArea(String area){
        repo.getAllMealsofThisArea(new NetworkCallBack_meal() {


            @Override
            public void onSuccessgetMeal(Meal meal) {

            }

            @Override
            public void onSuccessgetMealsOfCategory(List<Meal> mealList) {
                view.setMeal(mealList);
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
        }, area);
    }
        public void getMealsOfThisIng(String ing){
        repo.getAllMealsofThisIng(new NetworkCallBack_meal() {

            @Override
            public void onSuccessgetMeal(Meal meal) {

            }

            @Override
            public void onSuccessgetMealsOfCategory(List<Meal> mealList) {

                view.setMeal(mealList);
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
        }, ing);
    }
    public void getMealsByName(String name){
            repo.getMealsByName(name).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(mealResponse -> {
                        view.setMeal(mealResponse.getMeals());
                    });
    }

    }


