package com.example.finalp.meals_of_category.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalp.R;
import com.example.finalp.home.view.onClickAdapter;
import com.example.finalp.meals_of_category.presenter.MealsOfCategoryPresenter;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;


public class MealsOfCategoryFragment extends Fragment implements onClickAdapter, MealsOfCategoryView {

   MealsOfCategoryPresenter mealsOfCategoryPresenter;
RecyclerView recyclerView ;
MealsOfCategoryAdapter mealsOfCategoryAdapter ;
    public MealsOfCategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.meal_of_category, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleofmeals);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager3);
        mealsOfCategoryAdapter = new MealsOfCategoryAdapter(getContext(), new ArrayList<>(),  this);
        recyclerView.setAdapter(mealsOfCategoryAdapter);

     mealsOfCategoryPresenter = new MealsOfCategoryPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        mealsOfCategoryPresenter.getAllMeals();
    }

    @Override
    public void onMealClick(Meal meal) {

    }

    @Override
    public void onCategoryClick(Category category) {

    }

    @Override
    public void onAreaClick(Area area) {

    }

    @Override
    public void onIngClick(Ingredient ingredient) {

    }

    @Override
    public void setMeal(List<Meal> mealList) {
        mealsOfCategoryAdapter.setMealList(mealList);
        mealsOfCategoryAdapter.notifyDataSetChanged();

    }
}