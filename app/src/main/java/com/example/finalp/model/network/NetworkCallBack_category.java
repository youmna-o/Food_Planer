package com.example.finalp.model.network;



import com.example.finalp.model.Category;

import java.util.List;

public interface NetworkCallBack_category {
    public void onSuccess(List<Category> categorieslList);
    public void onFailure(String error);
}
