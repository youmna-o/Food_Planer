package com.example.finalp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {

 TextView textView ;
 Button signout ;
 SharedPreferences sharedPreferences ;
    public ProfileFragment() {

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = requireContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.myemail);
        signout=view.findViewById(R.id.signOut);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            textView.setText(user.getEmail());
        }
        signout.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Login",false);
            editor.apply();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment2_to_login
            );
        });
    }
}