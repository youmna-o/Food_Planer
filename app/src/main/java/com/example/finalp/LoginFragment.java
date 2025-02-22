package com.example.finalp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {

    Button loginbtn;
    TextView sign ;
    TextView skip ;
    TextInputEditText emailtxt, passwordtxt;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sign = view.findViewById(R.id.sign);
        skip = view.findViewById(R.id.fav);
        loginbtn=view.findViewById(R.id.register);
        emailtxt=view.findViewById(R.id.emailtxt);
        passwordtxt=view.findViewById(R.id.passwordtxt);
        sign.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register);
        });
        skip.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_login_to_homeFragment);
        });

        loginbtn.setOnClickListener(view1 -> {
            String email = emailtxt.getText().toString();
            String password = passwordtxt.getText().toString();
            if (email.isEmpty()) {
                emailtxt.setError("Enter your email");
                return;
            }

            if (password.isEmpty()) {
                passwordtxt.setError("Enter your password");
                return;
            }
            User user = new User(email,password);
            Navigation.findNavController(view).navigate(R.id.action_login_to_homeFragment);
          /*  LoginFragmentDirections.ActionLoginToHomeFragment action
                    = LoginFragmentDirections.actionLoginToHomeFragment(user);
            Navigation.findNavController(view).navigate(action);*/

        });


    }
}