package com.example.finalp.register.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.finalp.R;
import com.example.finalp.register.presenter.RegisterPresenter;
import com.example.finalp.utilities.NetworkChecker;
import com.example.finalp.utilities.OfflineFragment;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment implements RegisterView {
    private RegisterPresenter presenter;
    private TextInputEditText emailtxt, passwordtxt , confirmPassword;
    private Button registerbtn;
    NetworkChecker networkChecker;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailtxt = view.findViewById(R.id.txtemail);
        passwordtxt = view.findViewById(R.id.txtpassword);
        registerbtn = view.findViewById(R.id.register);
        confirmPassword=view.findViewById(R.id.conPassword);
        networkChecker = new NetworkChecker(requireContext());
        presenter = new RegisterPresenter(this, requireContext(),networkChecker);
        presenter.checkNetwork();

        registerbtn.setOnClickListener(v -> {
            String email = emailtxt.getText().toString().trim();
            String password = passwordtxt.getText().toString().trim();
            String confirm = confirmPassword.getText().toString().trim();
            presenter.registerUser(email, password,confirm);
        });
    }


    @Override
    public void onRegisterSuccess() {
        Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_register_to_homeFragment, null,
                new NavOptions.Builder().setPopUpTo(R.id.register, true).build());
    }

    @Override
    public void showError(String message) {
        if (message.toLowerCase().contains("email")) {
            emailtxt.setError(message);
            emailtxt.requestFocus();
        } else if (message.toLowerCase().contains("password")) {
            passwordtxt.setError(message);
            passwordtxt.requestFocus();
        } else {
            Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showOfflineFragment() {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentscontainer, new OfflineFragment())
                .commit();

    }
}

