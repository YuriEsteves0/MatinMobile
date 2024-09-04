package com.yuri.v20.HELPER;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    private static DatabaseReference reference;
    private static FirebaseAuth auth;
    private static FirebaseFirestore firestore;
    private static FirebaseUser user;

    public static DatabaseReference getReference(){
        if(reference == null){
            reference = FirebaseDatabase.getInstance().getReference();
        }
        return reference;
    }

    public static DatabaseReference getReferenceUsuario(){
        reference = getReference().child("usuarios");
        return reference;
    }

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static String getCurrentUserUid(){
        return getAuth().getCurrentUser().getUid();
    }

    public static FirebaseFirestore getInstance(){
        if(firestore == null){
            firestore = FirebaseFirestore.getInstance();
        }
        return firestore;
    }

    public static boolean isUserLogged(){
        if(getAuth().getCurrentUser() == null){
            return false;
        }
        return  true;

    }
}
