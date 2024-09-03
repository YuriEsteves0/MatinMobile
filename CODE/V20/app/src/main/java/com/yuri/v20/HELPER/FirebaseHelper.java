package com.yuri.v20.HELPER;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static DatabaseReference reference;

    public static DatabaseReference getReference(){
        if(reference != null){
            reference = FirebaseDatabase.getInstance().getReference();
        }
        return reference;
    }

    public static DatabaseReference getReferenceUsuario(){
        reference = getReference().child("usuarios");
        return reference;
    }
}
