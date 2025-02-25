package com.example.finalp.login;



import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginRepository {
    private final FirebaseAuth firebaseAuth;
    private final SharedPreferences sharedPreferences;

    public LoginRepository(Context context) {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.sharedPreferences = context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
    }

    public void loginUser(String email, String password, LoginCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserCredentials(email, password);
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount account, LoginCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            saveGoogleUserCredentials(user.getEmail(), user.getDisplayName());
                        }
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    private void saveUserCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.putBoolean("Login", true);
        editor.apply();
    }

    private void saveGoogleUserCredentials(String email, String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.putString("NAME", name);
        editor.putBoolean("Login", true);
        editor.apply();
    }

    public interface LoginCallback {
        void onSuccess();
        void onFailure(String error);
    }
}
