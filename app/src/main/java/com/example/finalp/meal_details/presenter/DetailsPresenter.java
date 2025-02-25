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
        new Thread(() -> {
            boolean exists = repo.isMealExist(meal);
            Log.d("MealDatabase", "Meal exists? " + exists + " -> " + meal.getIdMeal());

            if (exists) {
                repo.delete(meal);
                Log.d("MealDatabase", "Meal deleted: " + meal.getIdMeal());
            } else {
                repo.insert(meal);
                Log.d("MealDatabase", "Meal inserted: " + meal.getIdMeal());
            }
        }).start();
    }
    public void addMeal(Meal meal) {
        new Thread(() -> repo.insert(meal)).start();
    }
}



