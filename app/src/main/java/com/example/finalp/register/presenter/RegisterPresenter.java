package com.example.finalp.register.presenter;

import android.content.Context;
import android.util.Patterns;

import com.example.finalp.register.RegisterRepository;
import com.example.finalp.register.view.RegisterView;
import com.example.finalp.utilities.NetworkChecker;

public class RegisterPresenter {
    private RegisterRepository repository;
    private RegisterView view;
    private NetworkChecker networkChecker;

    public RegisterPresenter(RegisterView view, Context context, NetworkChecker networkChecker) {
        this.view = view;
        this.repository = new RegisterRepository(context);
        this.networkChecker=networkChecker;
    }
    public void checkNetwork() {
        if (!networkChecker.isConnected()) {
            view.showOfflineFragment();
        }
    }
    public void registerUser(String email, String password, String confirmPassword) {
        if (email.isEmpty()) {
            view.showError("Enter your email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showError("Invalid email format");
            return;
        }

        if (password.isEmpty()) {
            view.showError("Enter a password");
            return;
        }

        if (password.length() < 6) {
            view.showError("Password must be at least 6 characters");
            return;
        }

        if (!password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            view.showError("Password must contain at least one uppercase letter and one number");
            return;
        }
        if (!password.equals(confirmPassword)) {
            view.showError("Passwords do not match");
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
