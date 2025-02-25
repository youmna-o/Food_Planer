package com.example.finalp.model;

import androidx.lifecycle.LiveData;

import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.network.NetworkCallBack_meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

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
    public void  getAllMeaByID(NetworkCallBack_meal networkCallBackMeal,String id){
        remoteDataSource.getMealByID(networkCallBackMeal,id);
    }


    public Flowable<List<Meal>> getStore(){
        return  localDataSource.getStoredData();

    }
    public Completable insert(Meal meal){
        return    localDataSource.insert(meal);
    }
    public  Completable  delete(Meal meal){
        return   localDataSource.delete(meal);
    }

    public Single<Boolean> isMealExist(Meal meal) {
       return localDataSource.isMealExist(meal);
    }
}
