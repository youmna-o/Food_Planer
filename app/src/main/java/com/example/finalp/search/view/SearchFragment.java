package com.example.finalp.search.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.home.view.AreaAdapter;
import com.example.finalp.home.view.CategoryAdapter;
import com.example.finalp.home.view.onClickAdapter;
import com.example.finalp.meals_of_category.view.MealsOfCategoryFragment;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.search.presenter.SearchPresenter;
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
        // setUpFilter();

        searchRecyclerView = view.findViewById(R.id.searchrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        searchRecyclerView.setLayoutManager(gridLayoutManager);


        // ingredientAdapter=new IngredientAdapter(getContext(),new ArrayList<>() ,this);
         //searchRecyclerView.setAdapter(ingredientAdapter);
         // areaAdapter=new AreaAdapter(getContext(),new ArrayList<>() ,this);
          //searchRecyclerView.setAdapter(areaAdapter);
         catadapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);
        searchRecyclerView.setAdapter(catadapter);

        presenter = new SearchPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenter.getAllMeals();

        return view;
    }

    @Override
    public void setMeal(List<Meal> mealList) {

    }

    @Override
    public void setCategory(List<Category> categoryList) {
        catadapter.setCategoryList(categoryList);
        catadapter.notifyDataSetChanged();
    }

    @Override
    public void setArea(List<Area> areaList) {
        //areaAdapter.setAreaList(areaList);
        //areaAdapter.notifyDataSetChanged();
    }



    @Override
    public void setIngredient(List<Ingredient> ingredientList) {
        //ingredientAdapter.setIngredientList(ingredientList);
        //ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meal meal) {

    }

    @Override
    public void onCategoryClick(Category category) {
        Log.i("TAG", "onCategoryClick: "+category.getStrCategory());
         }

    @Override
    public void onAreaClick(Area area) {

    }

    @Override
    public void onIngClick(Ingredient ingredient) {

    }
}
