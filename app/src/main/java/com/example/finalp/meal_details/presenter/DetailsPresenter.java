package com.example.finalp.meal_details.presenter;

import android.util.Log;

import com.example.finalp.meal_details.view.DetailsView;
import com.example.finalp.model.pojos.Area;
import com.example.finalp.model.pojos.Category;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.network.NetworkCallBack_meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {
        private DetailsView view;
        private Repo repo;

        public DetailsPresenter(DetailsView view, Repo repo) {
            this.view = view;
            this.repo = repo;
        }

        public void getMealDetails(String mealId) {
            repo.getAllMeaByID(new NetworkCallBack_meal() {

                @Override
                public void onSuccessgetMeal(Meal meal) {
                    view.showMealDetailsById(meal);
                }

                @Override
                public void onSuccessgetMealsOfCategory(List<Meal> mealList) {

                }

                @Override
                public void onSuccessIng(List<Ingredient> ingredients) {

                }

                @Override
                public void onSuccessCategory(List<Category> categorieslList) {

                }

                @Override
                public void onSuccessArea(List<Area> areaList) {

                }

                @Override
                public void onSuccessRundom(List<Meal> rundomMealList) {

                }

                @Override
                public void onFailure(String error) {

                }
            },mealId);
    }

    public void onMealClick(Meal meal) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference favRef = FirebaseDatabase.getInstance().getReference("meals").child(userId)
                .child("favorites")
                .child(meal.getIdMeal());

        repo.isMealExist(meal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exists -> {
                    if (exists) {
                        repo.delete(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> Log.d("Database", "Meal Deleted: " + meal.getIdMeal()),
                                        throwable -> Log.e("Database", "Error deleting meal", throwable)
                                );
                        favRef.removeValue();
                    } else {
                        repo.insert(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> Log.d("Database", "Meal Saved: " + meal.getIdMeal()),
                                        throwable -> Log.e("Database", "Error saving meal", throwable)
                                );
                        favRef.setValue(meal) // حفظ في Firebase
                                .addOnSuccessListener(aVoid -> Log.d("Firebase", "Meal saved successfully!"))
                                .addOnFailureListener(e -> Log.e("Firebase", "Failed to save meal", e));
                    }
                }, throwable -> Log.e("Database", "Error checking if meal exists", throwable));
    }

    public void onSavedMealClick(SavedMeal meal) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference savedRef = FirebaseDatabase.getInstance().getReference("meals").child(userId)
                .child("savedMeals")
                .child(meal.getIdMeal());

        repo.isSavedMealExist(meal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exists -> {
                    if (exists) {
                        repo.deleteSaved(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                        savedRef.removeValue();
                    } else {
                        repo.insertSaved(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                        savedRef.setValue(meal);
                    }
                }, throwable -> Log.e("Database", "Error checking if saved meal exists", throwable));
    }
    public void onPlannedMealClick(PlanMeal meal) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference plannedRef = FirebaseDatabase.getInstance().getReference("meals").child(userId)
                .child("plannedMeals")
                .child(meal.getMealId());

        repo.isPlanExist(meal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(exists -> {
                    if (exists) {
                        repo.deletePlans(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                        plannedRef.removeValue();
                    } else {
                        repo.insertPlans(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                        plannedRef.setValue(meal);
                    }
                }, throwable -> Log.e("Database", "Error checking if planned meal exists", throwable));
    }



}



