package com.example.finalp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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
import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private NavController navController;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    MealAdapter homeAdapter;
   // HomePresenter presenrer;
    FloatingActionButton favButton;
    FloatingActionButton actionButton;
    boolean isFavorite = false;
    boolean isSaved = false;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    MealAdapter rondomadapter;
    MainPresenter myPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentscontainer);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
       // NavigationUI.setupActionBarWithNavController(this, navController);
        myPresenter = new MainPresenter(this ,this);
        myPresenter.goToLogin(navController);

    }

    @Override
    public void goToLogin(View view) {
        myPresenter.goToLogin(navController);
    }
}