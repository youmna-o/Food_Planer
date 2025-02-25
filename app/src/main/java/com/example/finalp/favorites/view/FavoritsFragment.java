package com.example.finalp.favorites.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;

import com.example.finalp.favorites.presenter.FavPresenter;
import com.example.finalp.home.view.MealAdapter;
import com.example.finalp.search.view.onClickAdapter;
import com.example.finalp.model.Repo;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class FavoritsFragment extends Fragment implements FavView, OnClickFavAdapter {

    FavPresenter presenter ;
    FavMealsAdapter adapter ;
    RecyclerView recyclerView ;
    public FavoritsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView =view.findViewById(R.id.favrecycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        adapter = new FavMealsAdapter(getContext(), new ArrayList<>(),  this);
        recyclerView.setAdapter(adapter);

        presenter = new FavPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        LiveData<List<Meal>> liveData = presenter.getMeals();
        liveData.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealList) {
                adapter.setMealList(mealList);
                adapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public void setFavMeal(List<Meal> mealList) {
        adapter.setMealList(mealList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFavMealClick(Meal meal, View view) {
        presenter.deleteMeals(meal);

    }
}