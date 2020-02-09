package com.apptalktomechat.talktome.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apptalktomechat.talktome.Model.User;
import com.apptalktomechat.talktome.R;
import com.apptalktomechat.talktome.Service.FirebaseService;
import com.apptalktomechat.talktome.Service.UploadImage;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.apptalktomechat.talktome.Model.Constante.IMAGE_REQUEST;


public class ProfilUserFragment extends Fragment {
    CircleImageView profil_image;
    TextView username;
    TextView email;
    UploadImage uploadImage;
    DatabaseReference reference;
    FirebaseService service;
    FirebaseUser fuser;/*
    StorageReference storageReference;*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_user, container, false);

        profil_image = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        //storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        setOnClickListener();
        reference.addValueEventListener(new ValueEventListener() {

             @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                email.setText(fuser.getEmail());
                if (user.getImage() == null){
                    profil_image.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Glide.with(getContext()).load(user.getImage()).into(profil_image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
        return view;
    }

    private void setOnClickListener() {
        profil_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v != null) {
                    openImage();
                }else {
                    Toast.makeText(getContext(),"lien inclikable !!!",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void openImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData() != null){
            uploadImage = new UploadImage(data.getData());
            if(uploadImage.getUploadTask()!=null && uploadImage.getUploadTask().isInProgress()){
                Toast.makeText(getContext(),"Uploading ...",Toast.LENGTH_SHORT).show();
            }else{
                uploadImage.upload(getContext());
            }
        }
    }
}
