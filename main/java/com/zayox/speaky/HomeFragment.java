package com.zayox.speaky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private EditText editEmailFrag, editPasswordFrag;
    private Button loginBtnFrag;
    private FirebaseAuth auth;
    private String userName;


    public HomeFragment() {

    }

    public void userName(String name){
        this.userName = name;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        editEmailFrag = rootView.findViewById(R.id.email_loginFrag);
        editPasswordFrag = rootView.findViewById(R.id.password_loginFrag);
        loginBtnFrag = rootView.findViewById(R.id.login_btnFrag);
        auth = FirebaseAuth.getInstance();



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btn1 = (Button) view.findViewById(R.id.login_btnFrag);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailFrag.getText().toString();
                String password = editPasswordFrag.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getActivity(), DashboardActivity.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                        }
                        else{
                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
            }
        });
    }

});
    }
}