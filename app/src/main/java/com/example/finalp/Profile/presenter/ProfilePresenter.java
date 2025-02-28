package com.example.finalp.Profile.presenter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalp.Profile.view.ProfileView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePresenter {
    private Context context ;
    private ProfileView view;
   SharedPreferences sharedPreferences ;
    public ProfilePresenter(Context context, ProfileView view) {
        this.context = context;
        this.view = view;
        this.sharedPreferences=context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Login",false);
        editor.apply();
        view.signOut();

    }
    public void checkUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
           view.checkUser(user.getEmail());
        }
    }

}
