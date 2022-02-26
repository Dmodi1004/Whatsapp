package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.ActivityPhoneNumberBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumberActivity extends AppCompatActivity {

    ActivityPhoneNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        binding.phoneBox.requestFocus();

        binding.otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.phoneBox.getText().toString().isEmpty()){
                    binding.phoneBox.setError("Enter Your Number");
                }
                else {
                    Toast.makeText(PhoneNumberActivity.this, "Working on it", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(PhoneNumberActivity.this, OTPVerificationActivity.class);
//                    intent.putExtra("mobile", binding.phoneBox.getText().toString().trim());
//                    startActivity(intent);
                }
            }
        });


    }
}