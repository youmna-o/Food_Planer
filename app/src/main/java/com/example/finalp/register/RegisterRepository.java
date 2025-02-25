package com.example.finalp.register;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterRepository {
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;

    public RegisterRepository(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = context.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
    }

    public void registerUser(String email, String password, RegisterCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserToPrefs(email,password);
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    private void saveUserToPrefs(String email,String password) {
        sharedPreferences.edit().putString("EMAIL", email);
        sharedPreferences.edit().putString("PASSWORD", password);
        sharedPreferences.edit().putBoolean("Login", true).apply();
    }

    public interface RegisterCallback {
        void onSuccess();
        void onFailure(String error);
    }
}
