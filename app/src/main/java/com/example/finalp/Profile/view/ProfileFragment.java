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
                DatabaseReference mealsRef = FirebaseDatabase.getInstance().getReference("meals").child(currentUserId); ;


            mealsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            Log.e("Firebase", "No data found in Firebase!");
                            return;
                        }

                        List<Meal> mealList = new ArrayList<>();

                        for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                            Meal meal = mealSnapshot.getValue(Meal.class);
                            if (meal != null && meal.getIdMeal() != null) {
                                mealList.add(meal);
                                Log.d("Firebase", "Meal Loaded: " + meal.toString());
                            } else {
                                Log.e("Firebase", "Meal is NULL or missing fields");
                            }
                        }

                        for (Meal meal : mealList) {
                            detailsPresenter.onMealClick(meal);
                        }

                        Log.d("Firebase", "Total meals loaded for user " + currentUserId + ": " + mealList.size());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("Firebase", "Failed to restore backup", databaseError.toException());
                    }
                });


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