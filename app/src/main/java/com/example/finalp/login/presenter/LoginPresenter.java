package com.example.finalp.login.presenter;

import com.example.finalp.login.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.firebase.auth.AuthCredential;

public class LoginPresenter {
    private final LoginView view;
    private final FirebaseAuth firebaseAuth;
    private final GoogleSignInClient googleSignInClient;
    private final Context context;

    public LoginPresenter(LoginView view, Context context) {
        this.view = view;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("760233796809-ttef1v37f4kp5dvhpq2hqtpkghb72ifq.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public void loginUser(String email, String password) {
        if (email.isEmpty()) {
            view.showLoginError("Enter your email");
            return;
        }
        if (password.isEmpty()) {
            view.showLoginError("Enter your password");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserCredentials(email, password);
                        view.showLoginSuccess();
                        view.navigateToHome();
                    } else {
                        view.showLoginError("Login Failed: " + task.getException().getMessage());
                    }
                });
    }

    public void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        view.navigateToGoogleSignIn(signInIntent);
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.showLoginSuccess();
                        view.navigateToHome();
                    } else {
                        view.showGoogleSignInError("Authentication Failed: " + task.getException().getMessage());
                    }
                });
    }

    private void saveUserCredentials(String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.putBoolean("Login", true);
        editor.apply();
    }
}
