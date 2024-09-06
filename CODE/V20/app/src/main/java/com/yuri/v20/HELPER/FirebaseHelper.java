package com.yuri.v20.HELPER;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yuri.v20.ACTIVITIES.LoginActivity;
import com.yuri.v20.MODEL.Usuario;
import com.yuri.v20.MODEL.UsuarioCallback;

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

    public static void verifLogadoSenaoRedirecionar(Context context, Activity activity){
        if(!isUserLogged()){
            AndroidHelper.trocarTela(context, LoginActivity.class, activity);
        }
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

    public static CollectionReference getInstanceProd(){
        return getInstance().collection("produtos");
    }

    public static Usuario pegarDadosUsu(final UsuarioCallback callback){
        Usuario usuario = new Usuario();
        getInstance().collection("usuarios").document(getCurrentUserUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Usuario usuario = documentSnapshot.toObject(Usuario.class);
                callback.onCallback(usuario);
            }
        });
        return usuario;
    }
}
