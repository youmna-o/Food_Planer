package com.example.finalp.login.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.finalp.R;
import com.example.finalp.login.presenter.LoginPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment implements LoginView {
    private static final int RC_SIGN_IN = 9001;
    private LoginPresenter presenter;

    Button loginbtn, google;
    TextView sign, skip;
    TextInputEditText emailtxt, passwordtxt;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        google = view.findViewById(R.id.googlebtn);
        sign = view.findViewById(R.id.sign);
        skip = view.findViewById(R.id.fav);
        loginbtn = view.findViewById(R.id.register);
        emailtxt = view.findViewById(R.id.emailtxt);
        passwordtxt = view.findViewById(R.id.passwordtxt);

        google.setOnClickListener(view1 -> presenter.signInWithGoogle());
        sign.setOnClickListener(view1 -> navigateToRegister());
        skip.setOnClickListener(view1 -> showSkipDialog());
        loginbtn.setOnClickListener(view1 -> {
            String email = emailtxt.getText().toString().trim();
            String password = passwordtxt.getText().toString().trim();
            presenter.loginUser(email, password);
        });
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_login_to_homeFragment, null,
                new NavOptions.Builder().setPopUpTo(R.id.login, true).build());
    }

    @Override
    public void navigateToRegister() {
        Navigation.findNavController(requireView()).navigate(R.id.action_login_to_register);
    }

    @Override
    public void navigateToGoogleSignIn(Intent signInIntent) {
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void showGoogleSignInError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                showGoogleSignInError("Google Sign-In Failed: " + e.getMessage());
            }
        }
    }
    private void showSkipDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Skip Login?")
                .setMessage("If you don't log in, you will lose the favorite and plan features.")
                .setPositiveButton("OK", (dialog, which) -> navigateToHome())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
