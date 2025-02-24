package com.example.finalp.meal_details.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalp.R;
import com.example.finalp.home.view.CategoryAdapter;
import com.example.finalp.home.view.onClickAdapter;
import com.example.finalp.meal_details.presenter.DetailsPresenter;
import com.example.finalp.meals_of_category.presenter.MealsOfCategoryPresenter;
import com.example.finalp.meals_of_category.view.MealsOfCategoryFragmentArgs;
import com.example.finalp.model.Area;
import com.example.finalp.model.Category;
import com.example.finalp.model.Ingredient;
import com.example.finalp.model.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.search.view.IngredientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment  implements  DetailsView,onClickAdapter{

    private ImageView mealImage;
    private TextView mealName, steps, mealCountry;
    private WebView videoWebView;
    private RecyclerView ingredientsRecycler;
    IngredientAdapter adapter ;
    private FloatingActionButton favouriteButton ,planButton;
    private DetailsPresenter presenter;



    public DetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String id = DetailsFragmentArgs.fromBundle(getArguments()).getId();

        mealImage = view.findViewById(R.id.mealListImage);
        mealName = view.findViewById(R.id.mealListName);
        mealCountry = view.findViewById(R.id.countryName);
        steps = view.findViewById(R.id.instructions);
        videoWebView = view.findViewById(R.id.videoView);
        favouriteButton = view.findViewById(R.id.favbtn);
        planButton = view.findViewById(R.id.planebtn);

        ingredientsRecycler = view.findViewById(R.id.ingredientsRecyclerView);
        ingredientsRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientsRecycler.setLayoutManager(layoutManager);
        adapter = new IngredientAdapter(getContext(), new ArrayList<>(), this);
        ingredientsRecycler.setAdapter(adapter);

        presenter = new DetailsPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenter.getMealDetails(id);

        Log.d("TAG", "onViewCreated: //////////////////////");

    }

    @Override
    public void onMealClick(Meal meal, View view) {

    }

    @Override
    public void onCategoryClick(Category category, View view) {

    }

    @Override
    public void onAreaClick(Area area, View view) {

    }

    @Override
    public void onIngClick(Ingredient ingredient, View view) {

    }

    @Override
    public void showMealDetailsById(Meal meal) {

        mealName.setText(meal.strMeal);
        mealCountry.setText(meal.strArea);
        steps.setText(meal.strInstructions);
        Log.d("vv", "*******************showMealDetailsById: "+meal.strMeal+meal.strArea+meal.getStrIngredient2());

        // تحميل صورة الوجبة
        Glide.with(requireContext()).load(meal.strMealThumb).into(mealImage);

        // إعداد الفيديو
/*    String videoUrl = meal.strYoutube.replace("watch?v=", "embed/");
      String embedHtml = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
        videoWebView.loadData(embedHtml, "text/html", "utf-8");*/

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredientName = getMealField(meal, "strIngredient" + i);
            String measure = getMealField(meal, "strMeasure" + i);

            Log.d("IngredientTest", "Ingredient " + i + ": " + ingredientName + " - " + measure);

            if (ingredientName != null && !ingredientName.isEmpty()) {
                ingredients.add(new Ingredient(ingredientName));
            }
        }
        Log.d("AdapterTest", "Final Ingredients List: " + ingredients.size());

        adapter.setIngredientList(ingredients);
        adapter.notifyDataSetChanged();
    }
    private String getMealField(Meal meal, String fieldName) {
        try {
            Field field = meal.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); // للتأكد من الوصول حتى لو كان الحقل private
            Object value = field.get(meal);

            Log.d("ReflectionTest", "Field " + fieldName + ": " + value);
            return value != null ? value.toString() : "";  // تأكد من عدم إرجاع null
        } catch (NoSuchFieldException e) {
            Log.e("ReflectionTest", "Field not found: " + fieldName);
        } catch (IllegalAccessException e) {
            Log.e("ReflectionTest", "Cannot access field: " + fieldName);
        }
        return "";
    }

}
