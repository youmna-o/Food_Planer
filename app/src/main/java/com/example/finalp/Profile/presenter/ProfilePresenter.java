package com.example.finalp.Profile.presenter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.finalp.Profile.view.ProfileView;
import com.example.finalp.model.Repo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePresenter {
    private Context context ;
    private ProfileView view;
    private Repo repo ;
   SharedPreferences sharedPreferences ;
    public ProfilePresenter(Context context, ProfileView view,Repo repo) {
        this.context = context;
        this.view = view;
        this.repo=repo;
        this.sharedPreferences=context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Login",false);
        editor.apply();
        //view.signOut();
        repo.ClearAll()
                .subscribe(() -> {
                    Log.d("DB", "All data deleted successfully");
                }, throwable -> {
                    Log.e("DB", "Error deleting data", throwable);
                });
        view.signOut();
    }
    public void checkUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
           view.checkUser(user.getEmail());
        }
    }

}
