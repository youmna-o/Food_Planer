package com.example.finalp.meal_details.presenter;
import com.example.finalp.meal_details.view.DetailsView;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
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
}



