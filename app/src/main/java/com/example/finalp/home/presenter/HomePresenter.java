package com.example.finalp.home.presenter;

import com.example.finalp.favorites.view.MyView;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

public class HomePresenter {
    private MyView view;
    private Repo repo;

    public HomePresenter(MyView view, Repo repo){
        this.view=view;
        this.repo=repo;
    }


    public void addMeal(Meal meal) {
        new Thread(() -> repo.insert(meal)).start();
    }
    public void getAllMeals(){
        repo.getAllMeals(new NetworkCallBack_meal() {
            @Override
            public void onSuccessIng(List<Ingredient> ingredients) {
                /*for (Ingredient ingredient : ingredients) {
                    Log.i(TAG, "Product****************************: " +ingredient.getStrIngredient());
                }*/
            }

            @Override
            public void onSuccessCategory(List<Category> categorieslList) {
               /* for (Category category : categorieslList) {
                    Log.i(TAG, "categorieslList////////: " +category.strCategory);
                }*/
                view.setCategory(categorieslList);

            }

            @Override
            public void onSuccessArea(List<Area> areaList) {
               /* for (Area area : areaList) {
                    Log.i(TAG, "////////: " +area.strArea);
                }*/
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
