package com.example.finalp.main.view;

import static android.view.View.GONE;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalp.main.presenter.MainPresenter;
import com.example.finalp.R;

import com.example.finalp.home.view.AreaAdapter;
import com.example.finalp.home.view.CategoryAdapter;
import com.example.finalp.home.view.MealAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MainView {
    private NavController navController;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    BottomAppBar bottomAppBar ;
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
        bottomAppBar=findViewById(R.id.bottomAppBar);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.splashFragment || destination.getId() == R.id.login||destination.getId() == R.id.register) {
                bottomNavigationView.setVisibility(GONE);
                bottomAppBar.setVisibility(GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomAppBar.setVisibility(View.VISIBLE);
            }
        });
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        myPresenter = new MainPresenter(this ,this);
        myPresenter.goToLogin(navController);

    }

    @Override
    public void goToLogin(View view) {
        myPresenter.goToLogin(navController);
    }
}