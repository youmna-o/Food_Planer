package com.example.finalp.model;

import android.util.Log;

import com.example.finalp.model.database.SavedMealDAO;
import com.example.finalp.model.network.MealService;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.MealResponse;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.network.NetworkCallBack_meal;
import com.example.finalp.model.pojos.SavedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    ///////////////////////////////////////////////
    public Flowable<List<SavedMeal>> geSaved(){
        return  localDataSource.getSavedData();

    }
    public Completable insertSaved(SavedMeal meal){
        return    localDataSource.insert(meal);
    }
    public  Completable  deleteSaved(SavedMeal meal){

        return   localDataSource.delete(meal);
    }

    public Single<Boolean> isSavedMealExist(SavedMeal meal) {
        return localDataSource.isMealExist(meal);
    }
////////////////////////////////////////////////
    public Flowable<List<PlanMeal>> getStorePlans(){
        return  localDataSource.getStoredPlans();

    }
    public Completable insertPlans(PlanMeal meal){
        return    localDataSource.insert(meal);
    }
    public  Completable  deletePlans(PlanMeal meal){

        return   localDataSource.delete(meal);
    }

    public Single<Boolean> isPlanExist(PlanMeal meal) {

        return localDataSource.isMealExist(meal);
    }
    public Single<List<String>> getMealIdsByDate(String date) {
        return localDataSource.getMealIdsByDate(date);
    }

    public Flowable<List<SavedMeal>> getMealsByIds(List<String> mealIds) {
        Log.d("Repo", "Fetching meals for IDs: " + mealIds);
        return localDataSource.getMealsByIds(mealIds).doOnNext(meals -> Log.d("Repo", "Meals retrieved from DB: " + meals));
    }
   public  Completable ClearAll(){
        return  localDataSource.clearAllData();
    }
   /*public Completable ClearAll() {
       return Completable.fromAction(() -> {
           localDataSource.clearAllData().blockingAwait();
       }).subscribeOn(Schedulers.io());
   }*/

    public Flowable<MealResponse> getMealsByName(String name){
        return  remoteDataSource.getMealByName(name);

    }


}
