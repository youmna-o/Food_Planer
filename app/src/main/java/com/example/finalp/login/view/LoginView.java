package com.example.finalp.login.view;

import android.content.Intent;

public interface LoginView {
    void showLoginSuccess();
    void showLoginError(String message);
    void navigateToHome();
    void navigateToRegister();
    void navigateToGoogleSignIn(Intent signInIntent);
    void showGoogleSignInError(String message);
}