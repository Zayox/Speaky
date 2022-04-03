package com.zayox.speaky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    ListFragment listFragment = new ListFragment();
    HomeFragment homeFragment = new HomeFragment();

    private EditText editEmail, editPassword;
    private Button loginBtn;
    private TextView registerLink;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private String userName;


    public MainActivity(){

    }

    public void userName(String name){
        this.userName = name;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.email_login);
        editPassword = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_btn);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        registerLink = findViewById(R.id.register_link);
        auth = FirebaseAuth.getInstance();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
                        return true;

                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                }

                return false;
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, userName, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}