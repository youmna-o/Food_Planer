package com.example.finalp.meals_of_category.presenter;

import com.example.finalp.meals_of_category.view.MealsOfCategoryView;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.data_models.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

public class MealsOfCategoryPresenter {

        private MealsOfCategoryView view;
        private Repo repo;

        public MealsOfCategoryPresenter (MealsOfCategoryView view, Repo repo){
            this.view=view;
            this.repo=repo;
        }

        public void addMeal(Meal meal) {

            new Thread(() -> repo.insert(meal)).start();
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




        public void onMealClick(Meal meal) {
            new Thread(() -> {
                if (repo.isMealExist(meal)) {
                    repo.delete(meal);

                } else {
                    repo.insert(meal);
                }
            }).start();
        }


    }


