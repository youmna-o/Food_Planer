package com.example.finalp.utilities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.finalp.R;

public class NoDataFragment extends Fragment {

   Button goToLogin ;
    public NoDataFragment() {
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
        return inflater.inflate(R.layout.fragment_no_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goToLogin=view.findViewById(R.id.tosign);
        LottieAnimationView animationView = view.findViewById(R.id.lottieAnimationView3);
        animationView.playAnimation();
        goToLogin.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_noDataFragment_to_login);
        });
    }
}