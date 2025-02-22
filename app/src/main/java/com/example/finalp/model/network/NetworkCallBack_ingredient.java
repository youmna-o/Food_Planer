package com.example.finalp.model.network;



import com.example.finalp.model.Ingredient;

import java.util.List;

public interface NetworkCallBack_ingredient {
    public void onSuccess(List<Ingredient>ingredients);
    public void onFailure(String error);
}
