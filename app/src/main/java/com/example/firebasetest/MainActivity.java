package com.example.firebasetest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button SignOut;

    EditText data_input;

    EditText time_input;

    EditText name_input;

    EditText date_input;
    Button add, remove, get;

    //TextView data_output;

    Button AddEvent;

    TextView aboutUs;

    public static final String locationMessage = "";


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        createSignInIntent();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://fir-test-775d0-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Event");
        DatabaseReference myRef1 = myRef.child("Location");
        DatabaseReference myRef2 = myRef.child("Name");
        DatabaseReference myRef3= myRef.child("Date");
        DatabaseReference myRef4= myRef.child("Time");
        myRef.setValue("Helloo world!");


        Button openMaps_btn = (Button) findViewById(R.id.btn_maps);
        openMaps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

//        AddEvent = (Button) findViewById(R.id.add_btn);
//        AddEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(i);
//            }
//        });
        aboutUs = findViewById(R.id.txt_about_us);
        aboutUs.setText("We were inspired to create She Connects in order to combat gender inequality " +
                "in NI today. Our app will serve as a central space for women's groups and support networks, making it easier " +
                "for women and girls to connect and share experiences.");

        SignOut = (Button) findViewById(R.id.btn_signOut);
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        data_input = (EditText) findViewById(R.id.data_txt);

        name_input = (EditText) findViewById(R.id.name_txt);
        date_input = (EditText) findViewById(R.id.date_txt);
        time_input = (EditText) findViewById(R.id.time_txt);
        //data_output = (TextView) findViewById(R.id.output_txt);

        add = (Button) findViewById(R.id.add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = data_input.getText().toString();
                myRef1.setValue(data);
                String data1 = name_input.getText().toString();
                myRef2.setValue(data1);
                String data2 = date_input.getText().toString();
                myRef3.setValue(data2);
                String data3 = time_input.getText().toString();
                myRef4.setValue(data2);
                String submitEvent = ((EditText) findViewById(R.id.data_txt)).getText().toString();
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra(locationMessage, submitEvent);
                startActivity(i);

            }
        });
    }


    public void createSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.AnonymousBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        createSignInIntent();
                    }
                });
    }
}
