package com.apptalktomechat.talktome.Service;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class FirebaseService {
    /**
     * Methode générique ne fonctionne pas pour l'instant
     * */
    FirebaseUser fuser;
    public FirebaseService(){
        if((fuser = FirebaseAuth.getInstance().getCurrentUser()) != null);
    }
    public FirebaseUser getFirebaseUser(){
        return fuser;
    }


}
