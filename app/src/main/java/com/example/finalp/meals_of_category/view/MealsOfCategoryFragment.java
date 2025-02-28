package com.example.finalp.meals_of_category.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.example.finalp.R;
import com.example.finalp.search.view.onClickAdapter;
import com.example.finalp.meals_of_category.presenter.MealsOfCategoryPresenter;
import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;


public class MealsOfCategoryFragment extends Fragment implements onClickAdapter, MealsOfCategoryView {
    EditText search ;
    TextView textView;
   MealsOfCategoryPresenter mealsOfCategoryPresenter;
   RecyclerView recyclerView ;
   MealsOfCategoryAdapter mealsOfCategoryAdapter ;
    List<Meal> allMeals = new ArrayList<>();;
    PublishSubject<String> searchSubject = PublishSubject.create();
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
        String filterType = MealsOfCategoryFragmentArgs.fromBundle(getArguments()).getFilter();
        String filter =MealsOfCategoryFragmentArgs.fromBundle(getArguments()).getType();
         search=view.findViewById(R.id.searchcategoryEditText);
        Log.d("//////////////////////////FilterCheck", "Filter Type: " + filterType + ", Filter Value: " + filter);

        recyclerView = view.findViewById(R.id.recycleofmeals);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        mealsOfCategoryAdapter = new MealsOfCategoryAdapter(getContext(), new ArrayList<>(),  this);
        recyclerView.setAdapter(mealsOfCategoryAdapter);

        mealsOfCategoryPresenter = new MealsOfCategoryPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        if (filterType.equals("category")) {
            mealsOfCategoryPresenter.getMealsOfThisCat(filter);
        } else if (filterType.equals("ingredient")) {
            mealsOfCategoryPresenter.getMealsOfThisIng(filter);
        } else if (filterType.equals("area")) {
            mealsOfCategoryPresenter.getMealsOfThisArea(filter);
        }
        Disposable disposable = searchSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> filterMeals(query));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchSubject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }
    @Override
    public void onMealClick(Meal meal,View view) {
        navigateToMealList(meal.getIdMeal(), view);
    }

    private void navigateToMealList(String id, View view) {
        MealsOfCategoryFragmentDirections.ActionMealsOfCategoryFragmentToDetailsFragment action =
                MealsOfCategoryFragmentDirections.actionMealsOfCategoryFragmentToDetailsFragment(id);
        Navigation.findNavController(view).navigate(action);
    }




    @Override
    public void onCategoryClick(Category category,View view) {

    }

    @Override
    public void onAreaClick(Area area,View view) {

    }

    @Override
    public void onIngClick(Ingredient ingredient,View view) {

    }
    private void filterMeals(String query) {
        if (query.isEmpty()) {
            mealsOfCategoryAdapter.setMealList(allMeals);
            mealsOfCategoryAdapter.notifyDataSetChanged();
        } else {
            mealsOfCategoryPresenter.getMealsByName(query);
        }
    }
    @Override
    public void setMeal(List<Meal> mealList) {
        allMeals.clear();
        allMeals.addAll(mealList);
        mealsOfCategoryAdapter.setMealList(allMeals);
        mealsOfCategoryAdapter.notifyDataSetChanged();

    }
}