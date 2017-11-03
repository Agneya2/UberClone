package com.bmsit.marzil.driverapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText regno;
    EditText loginid;
    EditText loginpass;
    TextView text;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        login = findViewById(R.id.login);
        regno=findViewById(R.id.regno);
        loginid=findViewById(R.id.loginid);
        loginpass=findViewById(R.id.loginpass);
        text=findViewById(R.id.text);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!regno.getText().toString().matches(""))
                {
                    if(!loginid.getText().toString().matches("") && !loginpass.getText().toString().matches("")) {
                        mAuth.signInWithEmailAndPassword(loginid.getText().toString(),loginpass.getText().toString())
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(MainActivity.this, DriverMapActivity.class);
                                            intent.putExtra("regpass", regno.getText().toString());
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(),"Please Enter ID and Password",Toast.LENGTH_LONG).show();
                        }

                }
                else
                {
                        Toast.makeText(getApplicationContext(),"Enter Registration Number",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}


