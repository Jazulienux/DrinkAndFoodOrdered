package com.example.dfoor.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfoor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText phone, vert;

    FirebaseAuth mAuth;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.inNumber);
        vert = findViewById(R.id.inVert);

        mAuth = FirebaseAuth.getInstance();
    }
    public void postLogin(View view) {
        sendVertf();
    }

    public void postVert(View view) {
        vertCodePhone();
    }

    private void vertCodePhone() {
        String code = vert.getText().toString();

        if(code.equals("")){
            Toast.makeText(LoginActivity.this,"Vertification Is Required",Toast.LENGTH_SHORT).show();
        }
        else{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);

            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"Login Success ",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this,"Vertification Incorrect ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void sendVertf(){
        String phoneNumber = phone.getText().toString();

        if(phoneNumber.equals("")){
            Toast.makeText(LoginActivity.this,"Phone Number Is Required",Toast.LENGTH_SHORT).show();
        }
        else if(phoneNumber.length() < 10){
            Toast.makeText(LoginActivity.this,"Phone Length Number Is Not Spesificed",Toast.LENGTH_SHORT).show();
        }
        else {

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks
        }
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };

    public void guest(View view) {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }
}
