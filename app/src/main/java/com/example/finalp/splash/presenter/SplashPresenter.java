package com.example.finalp.splash.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.finalp.R;
import com.example.finalp.splash.view.SplashView;
import com.google.firebase.auth.FirebaseAuth;

public class SplashPresenter {
    private SplashView view ;
    private Context context ;

    public SplashPresenter(SplashView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void checkUserLogin(View view){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPref",Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean("Login",false);
        if(isLogged && FirebaseAuth.getInstance().getCurrentUser()!=null){
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
        }else {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_login);
        }
    }
}
