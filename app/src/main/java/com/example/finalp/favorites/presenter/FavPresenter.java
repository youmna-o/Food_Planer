package com.example.finalp.favorites.presenter;

import com.example.finalp.favorites.view.FavView;
import com.example.finalp.model.Repo;
import com.example.finalp.model.pojos.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter {
    private FavView view ;
    private Repo repo ;

    public FavPresenter(FavView view, Repo repo) {
        this.view = view;
        this.repo = repo;
    }

   public void getMeals() {
       repo.getStore()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread()).subscribe( mealList -> {
                   view.setFavMeal(mealList);
               } );
   }
    public void deleteMeals(Meal meal) {
        repo.delete(meal)
                .andThen(repo.getStore())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        updatedList -> view.setFavMeal(updatedList),
                        error -> System.out.println("Error deleting product: " + error)
                );

    }


}