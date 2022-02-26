package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.databinding.ActivitySetUpProfileBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SetUpProfileActivity extends AppCompatActivity {

    ActivitySetUpProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
//    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

//        binding.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 44);
//            }
//        });
//
//        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String name = binding.etUserName.getText().toString();
//
//                if (name.isEmpty()){
//                    binding.saveBtn.setError("Enter your name");
//                }
//                if (selectedImage != null){
//                    StorageReference reference = storage.getReference().child("Profile Pic")
//                            .child(auth.getUid());
//                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            if (task.isSuccessful()){
//                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                    @Override
//                                    public void onSuccess(Uri uri) {
//                                        String imageUrl = uri.toString();
//
//                                        String uid = auth.getUid()
//                                        Users users = new Users()
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
//            }
//        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null){
//            if (data.getData() != null){
//                binding.profileImage.setImageURI(data.getData());
//                selectedImage = data.getData();
//            }
//        }
//    }
}

//        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Users users = snapshot.getValue(Users.class);
//                        Picasso.get().load(users.getProfilePic())
//                                .placeholder(R.drawable.avatar)
//                                .into(binding.profileImage);
//                        binding.etUserName.setText(users.getUserName());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//        binding.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImagePicker.with(SetUpProfileActivity.this)
//                        .crop()//Crop image(Optional), Check Customization for more option
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });
//
//        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = binding.etUserName.getText().toString();
//
//                if (name.isEmpty()) {
//                    binding.etUserName.setError("Enter Your Name");
//                }
//                else{
//
//                    HashMap<String, Object> obj = new HashMap<>();
//                    obj.put("userName", name);
//
//                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                            .updateChildren(obj);
//                    Toast.makeText(SetUpProfileActivity.this, "Account has been created", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (data.getData() != null){
//            Uri sFile = data.getData();
//            binding.profileImage.setImageURI(sFile);
//
//            final StorageReference reference = storage.getReference().child("Profile Pic")
//                    .child(FirebaseAuth.getInstance().getUid());
//            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//
//                            database.getReference().child("Users")
//                                    .child(FirebaseAuth.getInstance().getUid())
//                                    .child("profilePic").setValue(uri.toString());
//                            Toast.makeText(SetUpProfileActivity.this, "Profile Pic Updated", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            });
//        }
//
//
//}