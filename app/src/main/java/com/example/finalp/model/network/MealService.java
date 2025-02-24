package com.example.finalp.model.network;




import com.example.finalp.model.AreaResponse;
import com.example.finalp.model.CategoryResponse;
import com.example.finalp.model.IngredientResponse;
import com.example.finalp.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal() ;

    @GET("categories.php")
    Call<CategoryResponse> getCategory() ;

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngrediant();

    @GET("list.php?a=list")
    Call<AreaResponse> getAreas();

    @GET("lookup.php")
    Call<MealResponse> getMealDetails(@Query("i" )String id);

    @GET("filter.php")
    Call<MealResponse> getMealOfThisCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);

    @GET("filter.php")
    Call<MealResponse> getMealsByIngrediant(@Query("i") String ing);

    @GET("search.php")
    Call<MealResponse> getMealWithName(@Query("s") String name) ;
}
