package com.example.finalp.Profile.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalp.Profile.presenter.ProfilePresenter;
import com.example.finalp.R;
import com.example.finalp.meal_details.presenter.DetailsPresenter;
import com.example.finalp.meal_details.view.DetailsView;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProfileFragment extends Fragment  implements  ProfileView , DetailsView {
    FirebaseStorage storage ;
    StorageReference storageRef ;
    StorageReference imagesRef ;
    StorageReference spaceRef ;

 TextView textView ;
 Button signout , backup ;
 SharedPreferences sharedPreferences ;
 ProfilePresenter presenter ;
 DetailsPresenter detailsPresenter ;
    public ProfileFragment() {

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
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(getContext());
        detailsPresenter = new DetailsPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        textView=view.findViewById(R.id.myemail);
        backup=view.findViewById(R.id.backup);
        signout=view.findViewById(R.id.signOut);
        presenter=new ProfilePresenter(requireContext(),this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenter.checkUser();
        signout.setOnClickListener(view1 -> {
            presenter.signOut();

        });
        backup.setOnClickListener(view1 -> {
            String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Log.d("Yomna", "onViewCreated: " + currentUserId);
            DatabaseReference mealsRef = FirebaseDatabase.getInstance().getReference("meals").child(currentUserId);

            DatabaseReference favRef = mealsRef.child("favorites");
            favRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Meal> favMeals = new ArrayList<>();
                    for (DataSnapshot mealSnapshot : snapshot.getChildren()) {
                        Meal meal = mealSnapshot.getValue(Meal.class);
                        if (meal != null && meal.getIdMeal() != null) {
                            favMeals.add(meal);
                        }
                    }
                    for (Meal meal : favMeals) {
                        detailsPresenter.onMealClick(meal);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Failed to restore favorites", error.toException());
                }
            });

            DatabaseReference savedRef = mealsRef.child("savedMeals");
            savedRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<SavedMeal> savedMeals = new ArrayList<>();
                    for (DataSnapshot mealSnapshot : snapshot.getChildren()) {
                        SavedMeal meal = mealSnapshot.getValue(SavedMeal.class);
                        if (meal != null && meal.getIdMeal() != null) {
                            savedMeals.add(meal);
                            Log.d("Firebase", "Saved Meal Loaded: " + meal.toString());
                        }
                    }
                    for (SavedMeal meal : savedMeals) {
                        detailsPresenter.onSavedMealClick(meal);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Failed to restore saved meals", error.toException());
                }
            });

            DatabaseReference planedRef = mealsRef.child("plannedMeals");
            planedRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<PlanMeal> planedMeals = new ArrayList<>();
                    for (DataSnapshot mealSnapshot : snapshot.getChildren()) {
                        PlanMeal meal = mealSnapshot.getValue(PlanMeal.class);
                        if (meal != null && meal.getMealId() != null) {
                            planedMeals.add(meal);
                            Log.d("Firebase", "Planed Meal Loaded: " + meal.toString());
                        }
                    }
                    for (PlanMeal meal : planedMeals) {
                        detailsPresenter.onPlannedMealClick(meal);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Failed to restore planed meals", error.toException());
                }
            });

            Log.d("Firebase", "Backup restoration started for user: " + currentUserId);
        });

    }


    @Override
    public void checkUser(String email) {
        textView.setText(email);
    }

    @Override
    public void signOut() {

            Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment2_to_login);

    }

    @Override
    public void backUp(View view) {

    }

    @Override
    public void showMealDetailsById(Meal meal) {

    }
}