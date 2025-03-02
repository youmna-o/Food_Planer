package com.example.finalp.home.presenter;

import com.example.finalp.utilities.NetworkChecker;
import com.example.finalp.home.view.HomeView;
import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

public class HomePresenter {
    private HomeView view;
    private Repo repo;
    private NetworkChecker networkChecker;

    public HomePresenter(HomeView view, Repo repo,NetworkChecker networkChecker){
        this.view=view;
        this.repo=repo;
        this.networkChecker=networkChecker;
    }


    public void addMeal(Meal meal) {
        new Thread(() -> repo.insert(meal)).start();
    }
    public void checkNetwork() {
        if (!networkChecker.isConnected()) {
            view.showOfflineFragment();
        }
    }
    public void getAllMeals(){
        repo.getAllMeals(new NetworkCallBack_meal() {
            @Override
            public void onSuccessIng(List<Ingredient> ingredients) {
            }

            @Override
            public void onSuccessCategory(List<Category> categorieslList) {
                view.setCategory(categorieslList);
            }

            @Override
            public void onSuccessArea(List<Area> areaList) {
                view.setArea(areaList);
            }

            @Override
            public void onSuccessRundom(List<Meal> rundomMealList) {
                view.setRundom(rundomMealList);
            }


            @Override
            public void onSuccessgetMeal(Meal meal) {

            }

            @Override
            public void onSuccessgetMealsOfCategory(List<Meal> mealList) {

                view.setMeal(mealList);
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }






}
