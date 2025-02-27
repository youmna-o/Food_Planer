package com.example.finalp.meal_details.view;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalp.R;
import com.example.finalp.meal_details.presenter.DetailsPresenter;
import com.example.finalp.model.pojos.Ingredient;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.model.pojos.SavedMeal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DetailsFragment extends Fragment  implements  DetailsView  {

    private ImageView mealImage;
    private TextView mealName, steps, mealCountry;
    private WebView videoWebView;
    private RecyclerView ingredientsRecycler;
    IngredientOfMealAdapter adapter ;
    private FloatingActionButton favouriteButton ,planButton;
    boolean isFavorite = false;
    boolean isSaved = false ;
    private DetailsPresenter presenter;
    private Meal currentMeal;
    private SavedMeal currenMealToSAve;



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
            favouriteButton.setEnabled(true);
        });

        planButton.setOnClickListener(v -> {
            showDatePickerDialog();

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
        this.currenMealToSAve = new SavedMeal(
                meal.getIdMeal(),
                meal.getStrMeal(),
                meal.getStrCategory(),
                meal.getStrArea(),
                meal.getStrInstructions(),
                meal.getStrMealThumb(),
                meal.getStrYoutube(),
                meal.getStrIngredient1(),
                meal.getStrIngredient2(),
                meal.getStrIngredient3(),
                meal.getStrIngredient4(),
                meal.getStrIngredient5(),
                meal.getStrIngredient6(),
                meal.getStrIngredient7(),
                meal.getStrIngredient8(),
                meal.getStrIngredient9(),
                meal.getStrIngredient10(),
                meal.getStrIngredient11(),
                meal.getStrIngredient12(),
                meal.getStrIngredient13(),
                meal.getStrIngredient14(),
                meal.getStrIngredient15(),
                meal.getStrIngredient16(),
                meal.getStrIngredient17(),
                meal.getStrIngredient18(),
                meal.getStrIngredient19(),
                meal.getStrIngredient20(),
                meal.getStrMeasure1(),
                meal.getStrMeasure2(),
                meal.getStrMeasure3(),
                meal.getStrMeasure4(),
                meal.getStrMeasure5(),
                meal.getStrMeasure6(),
                meal.getStrMeasure7(),
                meal.getStrMeasure8(),
                meal.getStrMeasure9(),
                meal.getStrMeasure10(),
                meal.getStrMeasure11(),
                meal.getStrMeasure12(),
                meal.getStrMeasure13(),
                meal.getStrMeasure14(),
                meal.getStrMeasure15(),
                meal.getStrMeasure16(),
                meal.getStrMeasure17(),
                meal.getStrMeasure18(),
                meal.getStrMeasure19(),
                meal.getStrMeasure20()
        );


        mealName.setText(meal.strMeal);
        mealCountry.setText(meal.strArea);
        steps.setText(meal.strInstructions);
        String video=meal.getStrYoutube();
        // Log.d("vv", "*******************showMealDetailsById: "+meal.strMeal+meal.strArea+meal.getStrIngredient2());
        Log.d("MealData", "Meal Object: " + new Gson().toJson(meal));
        Glide.with(requireContext()).load(meal.strMealThumb).into(mealImage);

        //  loadYouTubeVideo(video);

        List<Pair<String, String>> ingredientList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String ingredientName = getMealField(meal, "strIngredient" + i);
            String measurement = getMealField(meal, "strMeasure" + i);

            if (ingredientName != null && !ingredientName.trim().isEmpty()) {
                ingredientList.add(new Pair<>(ingredientName, measurement != null ? measurement : ""));
                Log.d("IngredientCreation", "Added: " + ingredientName + " - " + measurement);
            }
        }

        Log.d("Test", "Final Ingredients List: " + ingredientList.size());
        Log.d("Test", "Final Ingredients List: " + ingredientList);

        adapter.setIngredientList(ingredientList);
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
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();

        calendar.add(Calendar.DAY_OF_MONTH, 7);
        long weekAhead = calendar.getTimeInMillis();
        calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    if (currentMeal != null && currenMealToSAve != null) {
                        PlanMeal planMeal = new PlanMeal(currentMeal.getIdMeal(),selectedDate);
                        presenter.onPlaneToSaveClick(currenMealToSAve);
                        presenter.onMealPlaneClick(planMeal);

                    }
                    },
                year, month, day
        );

        datePickerDialog.getDatePicker().setMinDate(today);
        datePickerDialog.getDatePicker().setMaxDate(weekAhead);

        datePickerDialog.show();
    }

}
