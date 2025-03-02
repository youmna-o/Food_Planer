package com.example.finalp.login.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;


import com.example.finalp.login.LoginRepository;
import com.example.finalp.login.view.LoginView;
import com.example.finalp.utilities.NetworkChecker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LoginPresenter {
    private final LoginView view;
    private final LoginRepository repository;
    private final GoogleSignInClient googleSignInClient;
    private NetworkChecker networkChecker;

    public LoginPresenter(LoginView view, Context context , NetworkChecker networkChecker) {
        this.view = view;
        this.repository = new LoginRepository(context);
        this.networkChecker=networkChecker;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("760233796809-ttef1v37f4kp5dvhpq2hqtpkghb72ifq.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }
    public void checkNetwork() {
        if (!networkChecker.isConnected()) {
            view.showOfflineFragment();
        }
    }
    public void loginUser(String email, String password) {
        if (email.isEmpty()) {
            view.showLoginError("Enter your email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showLoginError("Invalid email format");
            return;
        }

        if (password.isEmpty()) {
            view.showLoginError("Enter your password");
            return;
        }

        if (password.length() < 6) {
            view.showLoginError("Password must be at least 6 characters");
            return;
        }

        repository.loginUser(email, password, new LoginRepository.LoginCallback() {
            @Override
            public void onSuccess() {
                view.showLoginSuccess();
                view.navigateToHome();
            }

            @Override
            public void onFailure(String error) {
                view.showLoginError("Login Failed: " + error);
            }
        });
    }

    public void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        view.navigateToGoogleSignIn(signInIntent);
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        repository.firebaseAuthWithGoogle(account, new LoginRepository.LoginCallback() {
            @Override
            public void onSuccess() {
                view.showLoginSuccess();
                view.navigateToHome();
            }

            @Override
            public void onFailure(String error) {
                view.showGoogleSignInError("Authentication Failed: " + error);
            }
        });
    }
}
