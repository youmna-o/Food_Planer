package com.example.finalp.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.home.view.AreaAdapter;
import com.example.finalp.home.view.CategoryAdapter;
import com.example.finalp.home.view.onClickAdapter;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView, onClickAdapter {

    private RecyclerView searchRecyclerView;
    private CategoryAdapter catadapter;
    private AreaAdapter areaAdapter ;
    private SearchPresenter presenter;
    private IngredientAdapter ingredientAdapter;
    ChipGroup chipGroup ;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter, container, false);

        chipGroup=view.findViewById(R.id.chipGroup);
        chipGroup.setSingleSelection(true);
         setUpFilter();

        searchRecyclerView = view.findViewById(R.id.searchrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        searchRecyclerView.setLayoutManager(gridLayoutManager);
         catadapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);
        searchRecyclerView.setAdapter(catadapter);

        presenter = new SearchPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenter.getAllMeals();
        Log.i("TAG", "onCreateView: Fragment Created////////////");

        setUpFilter();
        Log.i("TAG", "onCreateView: Fragment Created****************");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setMeal(List<Meal> mealList) {

    }

    @Override
    public void setCategory(List<Category> categoryList) {
        if (searchRecyclerView.getAdapter() instanceof CategoryAdapter) {
            ((CategoryAdapter) searchRecyclerView.getAdapter()).setCategoryList(categoryList);
            searchRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setArea(List<Area> areaList) {
        if (searchRecyclerView.getAdapter() instanceof AreaAdapter) {
            ((AreaAdapter) searchRecyclerView.getAdapter()).setAreaList(areaList);
            searchRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setIngredient(List<Ingredient> ingredientList) {
        if (searchRecyclerView.getAdapter() instanceof IngredientAdapter) {
            ((IngredientAdapter) searchRecyclerView.getAdapter()).setIngredientList(ingredientList);
            searchRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onMealClick(Meal meal,View view) {

    }

    @Override
    public void onCategoryClick(Category category, View view) {
        navigateToMealList("category",category.getStrCategory(), view);

    }

    @Override
    public void onAreaClick(Area area, View view) {
        navigateToMealList("area",area.getStrArea(), view);
    }

    @Override
    public void onIngClick(Ingredient ingredient, View view) {
        navigateToMealList("ingredient",ingredient.getStrIngredient(), view);
    }

    private void navigateToMealList(String type ,String value, View view) {
        SearchFragmentDirections.ActionSearchFragment2ToMealsOfCategoryFragment myaction =
                SearchFragmentDirections.actionSearchFragment2ToMealsOfCategoryFragment(type , value);
       // SearchFragmentDirections.ActionSearchFragment2ToBlankFragment action =
         //       SearchFragmentDirections.actionSearchFragment2ToBlankFragment(filterValue);
        Navigation.findNavController(view).navigate(myaction);
    }
    private void setUpFilter() {
        Log.i("TAG", "setUpFilter: Function Called*******");
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    String selectedFilter = chip.getText().toString();
                    updateAdapter(selectedFilter);
                }
            });
        }
    }

    private void updateAdapter(String filter) {
        switch (filter) {
            case "Category":
                catadapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);
                searchRecyclerView.setAdapter(catadapter);
                presenter.getAllMeals();
                break;

            case "Area":
                areaAdapter = new AreaAdapter(getContext(), new ArrayList<>(), this);
                searchRecyclerView.setAdapter(areaAdapter);
                presenter.getAllMeals();
                break;

            case "Ingredient":
                ingredientAdapter = new IngredientAdapter(getContext(), new ArrayList<>(), this);
                searchRecyclerView.setAdapter(ingredientAdapter);
                presenter.getAllMeals();
                break;
        }
    }

}
