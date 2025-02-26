package com.example.finalp.planner.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.favorites.presenter.FavPresenter;
import com.example.finalp.favorites.view.FavMealsAdapter;
import com.example.finalp.model.Repo;
import com.example.finalp.model.database.MealLocalDataSource;
import com.example.finalp.model.network.MealRemoteDataSource;
import com.example.finalp.model.pojos.Meal;
import com.example.finalp.model.pojos.PlanMeal;
import com.example.finalp.planner.presenter.PlannerPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlaneFragment extends Fragment implements onPlanedMealClick, PlannerView {
    private DatePicker datePicker;
    private PlannerPresenter presenter;
    private RecyclerView recyclerView;
    private PlaneMealsAdapter adapter;
    Button button;
    public PlaneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.show);
        recyclerView = view.findViewById(R.id.recyclerPlane);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        adapter = new PlaneMealsAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        presenter = new PlannerPresenter(this, Repo.getInstance(new MealRemoteDataSource(), MealLocalDataSource.getInstance(getContext())));

        // تحميل البيانات عند فتح الشاشة بالتاريخ الحالي
        String currentDate = getCurrentDate();
        presenter.getMealsByDate(currentDate);

        button.setOnClickListener(view1 -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(),
                    (v, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        Log.d("bb", "Selected Date: " + selectedDate);

                        // جلب البيانات من الـ Presenter بناءً على التاريخ
                        presenter.getMealsByDate(selectedDate);
                        Log.d("DataCheck", "Fetching meals for date: " + selectedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });
    }

    @Override
    public void setMealsPlane(List<Meal> mealList) {
        if (mealList != null && !mealList.isEmpty()) {
            Log.d("RecyclerView", "Updating with " + mealList.size() + " meals.");
            adapter.setMealList(mealList);
            adapter.notifyDataSetChanged();
        } else {
            Log.d("RecyclerView", "No meals found for the selected date.");
        }
    }
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

    @Override
    public void onPlanedMealClick(Meal meal, View view) {

    }
}
