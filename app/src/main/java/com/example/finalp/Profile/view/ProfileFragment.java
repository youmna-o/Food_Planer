package com.example.finalp.Profile.view;

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

import com.example.finalp.Profile.presenter.ProfilePresenter;
import com.example.finalp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment  implements  ProfileView{

 TextView textView ;
 Button signout ;
 SharedPreferences sharedPreferences ;
 ProfilePresenter presenter ;
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.myemail);
        signout=view.findViewById(R.id.signOut);
        presenter=new ProfilePresenter(requireContext(),this);
        presenter.checkUser();


        signout.setOnClickListener(view1 -> {
            presenter.signOut();

        });
    }


    @Override
    public void checkUser(String email) {
        textView.setText(email);
    }

    @Override
    public void signOut() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment2_to_login);

    }

    @Override
    public void backUp(View view) {

    }
}