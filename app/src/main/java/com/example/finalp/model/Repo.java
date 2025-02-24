package com.example.finalp.model;

import androidx.lifecycle.LiveData;

import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

public class Repo {
    MealLocalDataSource localDataSource ;
    MealRemoteDataSource remoteDataSource ;

    private  static  Repo  repo = null ;
    public static Repo getInstance(MealRemoteDataSource remoteDataSource ,  MealLocalDataSource localDataSource ){
        if(repo==null) {
            repo = new Repo(remoteDataSource, localDataSource);
        }
        return repo ;

    }
    /*public void getAllMealsByCategory(NetworkCallBack_meal networkCallBackMeal, String categoryName) {
        remoteDataSource.(networkCallBackMeal, categoryName);
    }

    public void getAllMealsByArea(NetworkCallBack_meal networkCallBackMeal, String areaName) {
        remoteDataSource.getMealsByArea(networkCallBackMeal, areaName);
    }

    public void getAllMealsByIngredient(NetworkCallBack_meal networkCallBackMeal, String ingredientName) {
        remoteDataSource.getMealsByIngredient(networkCallBackMeal, ingredientName);
    }*/
    private  Repo(MealRemoteDataSource remoteDataSource ,  MealLocalDataSource localDataSource){
        this.localDataSource=localDataSource;
        this.remoteDataSource=remoteDataSource;
    }
    public void  getAllMeals(NetworkCallBack_meal networkCallBackMeal){
        remoteDataSource.getDataOverNetwork(networkCallBackMeal);
    }
    public void  getAllMealsofThisCategory(NetworkCallBack_meal networkCallBackMeal,String cat){
        remoteDataSource.filterByCategory(networkCallBackMeal,cat);
    }
    public void  getAllMealsofThisArea(NetworkCallBack_meal networkCallBackMeal,String area){
        remoteDataSource.filterByArea(networkCallBackMeal,area);
    }
    public void  getAllMealsofThisIng(NetworkCallBack_meal networkCallBackMeal,String ing){
        remoteDataSource.filterByIng(networkCallBackMeal,ing);
    }

    public LiveData<List<Meal>> getStore(){
        return  localDataSource.getStoredData();

    }

    public void insert(Meal meal){
      localDataSource.insert(meal);
    }
    public  void  delete(Meal meal){
        localDataSource.delete(meal);
    }
    public boolean isMealExist(Meal meal) {
       return localDataSource.isMealExist(meal);
    }
}
