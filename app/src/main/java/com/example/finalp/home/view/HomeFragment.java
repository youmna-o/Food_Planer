package com.example.finalp.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalp.MainActivity;
import com.example.finalp.R;
import com.example.finalp.favorites.view.MyView;
import com.example.finalp.home.presenter.HomePresenter;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MyView,onClickAdapter {

    TextView textView ;
    TextView password;
    NavController navController;
    BottomNavigationView bottomNavigationView;
    HomePresenter presenrer;
    RecyclerView category;
    RecyclerView country ;
    RecyclerView rundomMeal;
    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    MealAdapter mealAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        category = view.findViewById(R.id.categoryrecycle);
        category.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        category.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>(), this);
        category.setAdapter(categoryAdapter);

        country = view.findViewById(R.id.countryrecycle);
        country.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        country.setLayoutManager(layoutManager2);
        areaAdapter = new AreaAdapter(getContext(), new ArrayList<>(), this);
        country.setAdapter(areaAdapter);

        rundomMeal = view.findViewById(R.id.rondommealrecycle);
        rundomMeal.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        rundomMeal.setLayoutManager(layoutManager3);
        mealAdapter = new MealAdapter(getContext(), new ArrayList<>(),  this);
        rundomMeal.setAdapter(mealAdapter);

        presenrer = new HomePresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenrer.getAllMeals();

       // String username = HomeFragmentArgs.fromBundle(getArguments()).getUser().getEmail();
       // Toast.makeText(getContext(),username,Toast.LENGTH_LONG).show();

      /*  navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        bottomNavigationView = view.findViewById(R.id.bottom_navigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
   */
    }


    @Override
    public void setMeal(List<Meal> mealList) {

    }

    @Override
    public void setCategory(List<Category> categoryList) {
        categoryAdapter.setCategoryList(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setArea(List<Area> areaList) {
        areaAdapter.setAreaList(areaList);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void setRundom(List<Meal> rundomList) {
        mealAdapter.setMealList(rundomList);
        mealAdapter.notifyDataSetChanged();
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