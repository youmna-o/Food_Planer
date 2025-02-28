package com.example.finalp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;

public class MainPresenter {
    private Context context ;
    private MainView view ;

    public MainPresenter(Context context, MainView view) {
        this.context = context;
        this.view = view;
    }
    public  void goToLogin(NavController navController){
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            /*if (destination.getId() == R.id.login) {
                view.hideAppBar();
            } else {
                view.showAppBar();
            }*/
            if(destination.getId()==R.id.favoritsFragment ||destination.getId()==R.id.planeFragment||destination.getId()==R.id.profileFragment2){
                SharedPreferences sharedPreferences =context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
                boolean isLogged = sharedPreferences.getBoolean("Login",false);
                if(!isLogged){
                    navController.navigate(R.id.noDataFragment);
                }
            }
        });
    }

}
