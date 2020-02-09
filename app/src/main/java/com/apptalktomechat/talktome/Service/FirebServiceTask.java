package com.apptalktomechat.talktome.Service;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseUser;

public class FirebServiceTask extends AsyncTask<Void,Void,FirebaseUser> {
    @Override
    protected FirebaseUser doInBackground(Void... voids) {
        FirebaseService service = new FirebaseService();
        return service.getFirebaseUser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(FirebaseUser firebaseUser) {
        super.onPostExecute(firebaseUser);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(FirebaseUser firebaseUser) {
        super.onCancelled(firebaseUser);
    }
}
