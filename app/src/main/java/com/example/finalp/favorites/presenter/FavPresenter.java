package com.example.finalp.favorites.presenter;

import androidx.lifecycle.LiveData;

import com.example.finalp.favorites.view.FavView;
import com.example.finalp.model.Repo;
import com.example.finalp.model.data_models.Meal;

import java.util.List;

public class FavPresenter {
    private FavView view ;
    private Repo repo ;

    public FavPresenter(FavView view, Repo repo) {
        this.view = view;
        this.repo = repo;
    }
    public LiveData<List<Meal>> getMeals() {

        return repo.getStore();
    }
    public void deleteMeals(Meal meal) {

        new Thread(() -> repo.delete(meal)).start();
    }


}