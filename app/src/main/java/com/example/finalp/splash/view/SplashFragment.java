package com.example.finalp.splash.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.finalp.R;
import com.example.finalp.splash.presenter.SplashPresenter;

public class SplashFragment extends Fragment implements SplashView {

    private SplashPresenter presenter;
    TextView textView;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         presenter = new SplashPresenter(this ,getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LottieAnimationView animationView = view.findViewById(R.id.lottieAnimationView);
        textView=view.findViewById(R.id.textView2);
        animationView.setAnimation(R.raw.splashani);
        animationView.playAnimation();

        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
        fadeInOut.setDuration(3000);
        fadeInOut.setRepeatCount(ObjectAnimator.INFINITE);
        fadeInOut.start();

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.checkUserLogin(view);


            }

            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });

    }

    @Override
    public void navigationToLogin(View view) {
         Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_login);
    }

    @Override
    public void navigationToHome(View view) {
         Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
    }
}