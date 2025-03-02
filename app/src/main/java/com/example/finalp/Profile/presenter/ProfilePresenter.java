package com.example.finalp.Profile.presenter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.finalp.Profile.view.ProfileView;
import com.example.finalp.model.Repo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;

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
    public void restoreBackup() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (userId == null) return;
        Completable.mergeArray(
                repo.restoreFavoritesFromFirebase(userId),
                repo.restoreSavedMealsFromFirebase(userId),
                repo.restorePlannedMealsFromFirebase(userId)
        ).subscribe(() -> {
        }, throwable -> {
            Log.e("Firebase", "Error restoring backup", throwable);
        });
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Login",false);
        editor.apply();
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
