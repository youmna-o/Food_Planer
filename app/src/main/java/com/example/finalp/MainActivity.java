package com.example.finalp;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalp.home.presenter.HomePresenter;

import com.example.finalp.home.view.AreaAdapter;
import com.example.finalp.home.view.CategoryAdapter;
import com.example.finalp.home.view.HomeView;
import com.example.finalp.home.view.MealAdapter;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeView {
    private NavController navController;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    MealAdapter homeAdapter;
    HomePresenter presenrer;
    FloatingActionButton favButton ;
    FloatingActionButton actionButton ;
    boolean isFavorite = false;
    boolean isSaved = false ;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    MealAdapter rondomadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   presenrer = new HomePresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(this)));
       // presenrer.getAllMeals();

       /* recyclerView = findViewById(R.id.catrecycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new CategoryAdapter(this , new ArrayList<Category>(),this);
        recyclerView.setAdapter(adapter);*/



        /* favButton = findViewById(R.id.favButton);
         actionButton = findViewById(R.id.actionButton);
         favButton.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            if (isFavorite) {
                favButton.setImageResource(R.drawable.baseline_favorite_24);

            } else {
                favButton.setImageResource(R.drawable.baseline_favorite_border_24);

            }
        });

        actionButton.setOnClickListener(v -> {
            isSaved = !isSaved;
            if (isSaved) {

                actionButton.setImageResource(R.drawable.baseline_playlist_add_check_24);
                //favButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red)));
            } else {

                actionButton.setImageResource(R.drawable.baseline_playlist_add_24);
                //favButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_500)));
            }
        });*/




        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentscontainer);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.login) {
                hideAppBar();
            } else {
                showAppBar();
            }
        });
    }

    private void hideAppBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void showAppBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }



    @Override
    public void setMeal(List<Meal> mealList) {

    }

    @Override
    public void setCategory(List<Category> categoryList) {
       // adapter =new CategoryAdapter(this , new ArrayList<Category>(),this);
        //recyclerView.setAdapter(adapter);
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
        rondomadapter.setMealList(rundomList);
        rondomadapter.notifyDataSetChanged();
    }


}

