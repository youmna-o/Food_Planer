package com.example.finalp.model.network;




import com.example.finalp.model.pojos.AreaResponse;
import com.example.finalp.model.pojos.CategoryResponse;
import com.example.finalp.model.pojos.IngredientResponse;
import com.example.finalp.model.pojos.MealResponse;

import io.reactivex.rxjava3.core.Flowable;
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
    Flowable<MealResponse> getMealWithName(@Query("s") String name) ;
}
