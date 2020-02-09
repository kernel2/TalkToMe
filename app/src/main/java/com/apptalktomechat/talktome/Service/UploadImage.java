package com.apptalktomechat.talktome.Service;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import static com.apptalktomechat.talktome.Model.Constante.IMAGE_REQUEST;

public class UploadImage {

    DatabaseReference databaseReference;
    StorageReference storageReference;

    private StorageTask uploadTask;
    private  Uri imageUrl;

    public UploadImage(Uri imageUri){
        this.imageUrl = imageUri;
        storageReference =  FirebaseStorage.getInstance()
                .getReference("Uploads");

    }

    public StorageTask getUploadTask() {
        return uploadTask;
    }

    private String getFileExtention(Context mContext){
        ContentResolver contentResolver = mContext.getContentResolver();
        MimeTypeMap mmTypeMap = MimeTypeMap.getSingleton();
        return  (MimeTypeMap.getSingleton())
                .getExtensionFromMimeType(contentResolver.getType(imageUrl));
    }

    public void upload(final Context mContext){
        final ProgressDialog pgr = new ProgressDialog(mContext);
        pgr.setMessage("Uploading...");
        pgr.show();
        if(imageUrl != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtention(mContext));

            uploadTask = fileReference.putFile(imageUrl);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()) throw task.getException();
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("image",task.getResult().toString());
                        databaseReference.updateChildren(map);
                    }else{
                        Toast.makeText(mContext,"Upload Failed",Toast.LENGTH_SHORT).show();
                        pgr.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
                    pgr.dismiss();
                }
            });
        }else{
            Toast.makeText(mContext,"No image was selected",Toast.LENGTH_SHORT).show();
        }

    }
}
