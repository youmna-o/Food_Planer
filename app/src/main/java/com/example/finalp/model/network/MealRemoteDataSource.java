package com.example.finalp.model.network;
import android.util.Log;
import com.example.finalp.model.pojos.AreaResponse;
import com.example.finalp.model.pojos.CategoryResponse;
import com.example.finalp.model.pojos.IngredientResponse;
import com.example.finalp.model.pojos.MealResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MealRemoteDataSource {
    private static final String url = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "Retrofit";
    String filterBy ;
    private MealService mealService;
    private static MealRemoteDataSource client = null;

    public static MealRemoteDataSource getInstance() {
        if (client == null) {
            client = new MealRemoteDataSource();
        }
        return client;
    }

    public MealRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        mealService = retrofit.create(MealService.class);
    }
    public  void getDataOverNetwork(NetworkCallBack_meal networkCallBackMeal) {
        Call<CategoryResponse> call = mealService.getCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessCategory(response.body().getCategories());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });

        Call<IngredientResponse> call2 = mealService.getIngrediant();
        call2.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessIng(response.body().getIngredient());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });
        Call<AreaResponse> call3 = mealService.getAreas();
        call3.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessArea(response.body().getAreas());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });
        Call<MealResponse> call4 = mealService.getRandomMeal();
        call4.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessRundom(response.body().getMeals());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });

    }
   public void filterByCategory(NetworkCallBack_meal networkCallBackMeal,String cat){
       Call<MealResponse> call5 = mealService.getMealOfThisCategory(cat);
       call5.enqueue(new Callback<MealResponse>() {
           @Override
           public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
               if (response.isSuccessful() && response.body() != null) {
                   networkCallBackMeal.onSuccessgetMealsOfCategory(response.body().getMeals());
               } else {
                   Log.i(TAG, "onResponse: " + response.code());
                   networkCallBackMeal.onFailure(response.errorBody().toString());
               }
           }

           @Override
           public void onFailure(Call<MealResponse> call, Throwable throwable) {
               Log.e(TAG, "onFailure: " + throwable.getMessage());

           }
       });
   }
    public void filterByArea(NetworkCallBack_meal networkCallBackMeal,String area){
        Call<MealResponse> call5 = mealService.getMealsByCountry(area);
        call5.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessgetMealsOfCategory(response.body().getMeals());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });
    }
    public void filterByIng(NetworkCallBack_meal networkCallBackMeal,String ing){
        Call<MealResponse> call5 = mealService.getMealsByIngrediant(ing);
        call5.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessgetMealsOfCategory(response.body().getMeals());
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());

            }
        });
    }
    public void getMealByID(NetworkCallBack_meal networkCallBackMeal,String id){
        Call<MealResponse> call6 = mealService.getMealDetails(id);
        call6.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBackMeal.onSuccessgetMeal(response.body().getMeals().get(0));
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                    networkCallBackMeal.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {

            }

        });
    }


    }
