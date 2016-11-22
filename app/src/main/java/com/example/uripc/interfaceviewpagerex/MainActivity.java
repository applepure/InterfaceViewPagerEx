package com.example.uripc.interfaceviewpagerex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Firebase ref;
    EditText editTextUserName;
    EditText editTextPassword;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = new Firebase("https://radiant-inferno-2446.firebaseio.com/");



    }
    public void ChangeData(View v){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapShot) {
                signIn(snapShot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    //getting all user login data
    public void signIn(DataSnapshot snapShot) {
        // get the Refferences of views
        editTextUserName = (EditText) findViewById(R.id.userName);
        editTextPassword = (EditText) findViewById(R.id.pass);
        editTextEmail = (EditText)findViewById(R.id.email);

        // get The User name and Password

        final String userName = editTextUserName.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String email = editTextEmail.getText().toString();
        User newUser = new User(email,password,userName);
        Map <String, Object> addNewUser = new HashMap<>();
        addNewUser.put("email",newUser.email);
        ref.child("users").child(userName).updateChildren(addNewUser);
        addNewUser.put("pass",newUser.pass);
        ref.child("users").child(userName).updateChildren(addNewUser);
        addNewUser.put("user",newUser.user);
        ref.child("users").child(userName).updateChildren(addNewUser);
        // fetch the Password form database for respective user name

        boolean validpass=false;
        // check if the Stored password matches with  Password entered by user
        for (DataSnapshot dataSnapshot : snapShot.child("users").getChildren()) {
            User user = dataSnapshot.getValue(User.class);

            if (user.getPass()!=null&&user.getUser()!=null&&user.getEmail()!=null) {
                Log.d("showmeemail", user.getEmail());
                Log.d("showmepass", user.getPass());
                Log.d("showmeuser", user.getUser());
                if (password.equals(user.getPass())) {

                    validpass = true;
                }
            }
        }
        if (validpass){
            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,ShowFireBase.class);
            startActivity(intent);

        }else {
            Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();

        }


    }
}
