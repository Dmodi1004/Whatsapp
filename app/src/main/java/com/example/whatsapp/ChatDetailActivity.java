package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapters.ChatAdapters;
import com.example.whatsapp.Models.MessageModels;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String reciveid = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        binding.chatUserName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.avatar).into(binding.profileImage);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

        final ArrayList<MessageModels> messageModels = new ArrayList<>();

        final ChatAdapters chatAdapters = new ChatAdapters(messageModels, this, reciveid);
        binding.chatRecyclerView.setAdapter(chatAdapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        final  String senderRoom = senderId + reciveid;
        final String receiverRoom = reciveid + senderId;

        database.getReference().child("Chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        messageModels.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            MessageModels models = snapshot1.getValue(MessageModels.class);

                            models.setMessageId(snapshot1.getKey());

                            messageModels.add(models);
                        }
                        chatAdapters.notifyDataSetChanged();

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

                String message = binding.etMessage.getText().toString();
                final MessageModels models = new MessageModels(senderId, message, userName);
                models.setTimeStamp(new Date().getTime());
                binding.etMessage.setText("");

                String randomKey = database.getReference().push().getKey();

                database.getReference().child("Chats")
                        .child(senderRoom)
                        .child(randomKey)
                        .setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        database.getReference().child("Chats")
                                .child(receiverRoom)
                                .child(randomKey)
                                .setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override


                            
                            public void onSuccess(Void unused) {

                            }
                        });

                    }
                });

            }
        });


    }
}