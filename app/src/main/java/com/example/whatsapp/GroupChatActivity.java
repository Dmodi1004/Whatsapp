package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapters.ChatAdapters;
import com.example.whatsapp.Models.MessageModels;
import com.example.whatsapp.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String senderId = FirebaseAuth.getInstance().getUid();
        String userName = getIntent().getStringExtra("userName");

        binding.chatUserName.setText("Friends Group");

        final ArrayList<MessageModels> messageModels = new ArrayList<>();
        final ChatAdapters adapters = new ChatAdapters(messageModels, this);
        binding.chatRecyclerView.setAdapter(adapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Group Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MessageModels models = dataSnapshot.getValue(MessageModels.class);
                    messageModels.add(models);
                }
                adapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.etMessage.getText().toString().isEmpty()){
                    binding.etMessage.setError("Enter Message");
                    return;
                }

                final String message = binding.etMessage.getText().toString();
                final MessageModels models = new MessageModels(senderId, message, userName);
                binding.etMessage.setText("");
                models.setTimeStamp(new Date().getTime());

                database.getReference().child("Group Chats")
                        .push()
                        .setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

            }
        });


    }
}