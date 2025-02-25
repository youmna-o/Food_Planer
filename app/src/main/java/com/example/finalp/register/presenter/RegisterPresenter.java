package com.example.finalp.register.presenter;

import android.content.Context;

import com.example.finalp.register.RegisterRepository;
import com.example.finalp.register.view.RegisterView;

public class RegisterPresenter {
    private RegisterRepository repository;
    private RegisterView view;

    public RegisterPresenter(RegisterView view, Context context) {
        this.view = view;
        this.repository = new RegisterRepository(context);
    }

    public void registerUser(String email, String password) {
        if (email.isEmpty()) {
            view.showError("Enter your email");
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            view.showError("Password must be at least 6 characters");
            return;
        }



        repository.registerUser(email, password, new RegisterRepository.RegisterCallback() {
            @Override
            public void onSuccess() {
                view.onRegisterSuccess();
            }

            @Override
            public void onFailure(String error) {
                view.showError(error);
            }
        });
    }
}
