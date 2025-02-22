package com.example.finalp.model.network;



import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;

import java.util.List;

public interface NetworkCallBack {
  void onSuccess(List<Meal> mealList);
    public void onFailure(String error);
}
