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
import androidx.navigation.Navigation;

import com.example.finalp.R;
import com.example.finalp.register.presenter.RegisterPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment implements RegisterView {
    private RegisterPresenter presenter;
    private TextInputEditText emailtxt, passwordtxt;
    private Button registerbtn;


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

        presenter = new RegisterPresenter(this, requireContext());

        registerbtn.setOnClickListener(v -> {
            String email = emailtxt.getText().toString().trim();
            String password = passwordtxt.getText().toString().trim();
            presenter.registerUser(email, password);
        });
    }




    @Override
    public void onRegisterSuccess() {
        Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_register_to_homeFragment);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}



/*public class RegisterFragment extends Fragment {

    Button register;
    TextInputEditText emailtxt, passwordtxt;
    public RegisterFragment() {
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
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        register=view.findViewById(R.id.register);
        emailtxt=view.findViewById(R.id.txtemail);
        passwordtxt=view.findViewById(R.id.txtpassword);

        register.setOnClickListener(view1 -> {
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
            Navigation.findNavController(view).navigate(R.id.action_register_to_homeFragment);
          /*  User user = new User(email,password);
            RegisterFragmentDirections.ActionRegisterToHomeFragment action
                    =RegisterFragmentDirections.actionRegisterToHomeFragment(user);
            Navigation.findNavController(view).navigate(action);*/

//       });


  //  }

//}
