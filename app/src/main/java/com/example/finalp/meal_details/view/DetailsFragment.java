package com.example.finalp.meal_details.view;

import android.net.Uri;
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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalp.R;
import com.example.finalp.favorites.view.OnClickFavAdapter;
import com.example.finalp.search.view.onClickAdapter;
import com.example.finalp.meal_details.presenter.DetailsPresenter;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.model.data_models.Category;
import com.example.finalp.model.data_models.Ingredient;
import com.example.finalp.model.data_models.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.search.view.IngredientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment  implements  DetailsView  {

    private ImageView mealImage;
    private TextView mealName, steps, mealCountry;
    private WebView videoWebView;
    private RecyclerView ingredientsRecycler;
    IngredientOfMealAdapter adapter ;
    private FloatingActionButton favouriteButton ,planButton;
    boolean isFavorite ;
    boolean isSaved = false ;
    private DetailsPresenter presenter;
    private Meal currentMeal;


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
        adapter = new IngredientOfMealAdapter(getContext(), new ArrayList<>());
        ingredientsRecycler.setAdapter(adapter);

        presenter = new DetailsPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));
        presenter.getMealDetails(id);

        Log.d("TAG", "onViewCreated: //////////////////////");


        favouriteButton.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            if (isFavorite) {
                favouriteButton.setImageResource(R.drawable.baseline_favorite_24);
                if (currentMeal != null) {
                    presenter.onMealClick(currentMeal);
                }

            } else {
                favouriteButton.setImageResource(R.drawable.baseline_favorite_border_24);
                if (currentMeal != null) {
                    presenter.onMealClick(currentMeal);
                }
            }
        });

        planButton.setOnClickListener(v -> {
            isSaved = !isSaved;
            if (isSaved) {
                planButton.setImageResource(R.drawable.baseline_playlist_add_check_24);

            } else {

                planButton.setImageResource(R.drawable.baseline_playlist_add_24);

            }
        });

    }


    @Override
    public void showMealDetailsById(Meal meal) {
        this.currentMeal = meal;
        mealName.setText(meal.strMeal);
        mealCountry.setText(meal.strArea);
        steps.setText(meal.strInstructions);
        String video=meal.getStrYoutube();
       // Log.d("vv", "*******************showMealDetailsById: "+meal.strMeal+meal.strArea+meal.getStrIngredient2());
        Log.d("MealData", "Meal Object: " + new Gson().toJson(meal));
        Glide.with(requireContext()).load(meal.strMealThumb).into(mealImage);

       loadYouTubeVideo(video);

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredientName = getMealField(meal, "strIngredient" + i);

            if (ingredientName != null && !ingredientName.trim().isEmpty()) {
                Ingredient ingredient = new Ingredient(ingredientName);
                ingredients.add(ingredient);
                Log.d("IngredientCreation", "Added: " + ingredient.toString());
            }
        }

        Log.d("Test", "Final Ingredients List: " + ingredients.size());
        Log.d("Test", "Final Ingredients List: " + ingredients);

        adapter.setIngredientList(ingredients);
        adapter.notifyDataSetChanged();
    }
    private String getMealField(Meal meal, String fieldName) {
        try {
            Field field = meal.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(meal);

            Log.d("ReflectionTest", "Field " + fieldName + ": " + value);
            return (value != null && !value.toString().trim().isEmpty()) ? value.toString() : null;
        } catch (NoSuchFieldException e) {
            Log.e("ReflectionTest", "Field not found: " + fieldName);
        } catch (IllegalAccessException e) {
            Log.e("ReflectionTest", "Cannot access field: " + fieldName);
        }
        return null;
    }
    private void loadYouTubeVideo(String youtubeUrl) {
        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        videoWebView.setWebChromeClient(new WebChromeClient());
        Uri uri = Uri.parse(youtubeUrl);
        String videoId = uri.getQueryParameter("v");

        if (videoId != null) {
            String iframe = "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" + videoId + "?autoplay=0&mute=0\" " +
                    "frameborder=\"0\" allowfullscreen></iframe>";

            videoWebView.loadData(iframe, "text/html", "utf-8");
        } else {
            videoWebView.setVisibility(View.GONE);
        }
    }


}
