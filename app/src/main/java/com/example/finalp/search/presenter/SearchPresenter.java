package com.example.finalp.search.presenter;

import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;
import com.example.finalp.search.view.SearchView;

import java.util.List;

public class SearchPresenter {
    private SearchView view;
    private Repo repo;

    public SearchPresenter (SearchView view, Repo repo){
        this.view=view;
        this.repo=repo;
    }

    public void addMeal(Meal meal) {
        new Thread(() -> repo.insert(meal)).start();
    }
    public void getAllMeals(){
        repo.getAllMeals(new NetworkCallBack_meal() {
            @Override
            public void onSuccessIng(List<Ingredient> ingredientList) {
              /*  for (Ingredient ingredient : ingredients) {
                    Log.i(TAG, "Product****************************: " +ingredient.getStrIngredient());
                }*/
                view.setIngredient(ingredientList);
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
                view.setArea(areaList);
            }

            @Override
            public void onSuccessRundom(List<Meal> rundomMealList) {
                view.setMeal(rundomMealList);
            }


            @Override
            public void onSuccessgetMeal(Meal meal) {

            }

            @Override
            public void onSuccessgetMealsOfCategory(List<Meal> mealList) {

            }

            @Override
            public void onFailure(String error) {
            }
        });
    }




}
