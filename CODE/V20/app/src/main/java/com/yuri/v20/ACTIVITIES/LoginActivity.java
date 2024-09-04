package com.yuri.v20.ACTIVITIES;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.yuri.v20.HELPER.AndroidHelper;
import com.yuri.v20.HELPER.FirebaseHelper;
import com.yuri.v20.MODEL.Usuario;
import com.yuri.v20.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText emailUsu, senhaUsu;
    private TextView aviso;
    private FirebaseAuth auth = FirebaseHelper.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        encontrarIDs();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUsu.getText().toString();
                String senha = senhaUsu.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    aviso.setVisibility(View.VISIBLE);
                }else{
                    auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                AndroidHelper.trocarTela(LoginActivity.this, HomePageActivity.class);
                                Log.d("TAG", "onComplete: Foi" + email + senha );
                            }else{
                                Log.d("TAG", "onComplete: Foi nao papai" + email + senha );
                            }
                        }
                    });
                }
            }
        });
    }

    public void encontrarIDs(){
        btnEntrar = findViewById(R.id.btnEntrar);
        emailUsu = findViewById(R.id.emailUsu);
        senhaUsu = findViewById(R.id.senhaUsu);
        aviso = findViewById(R.id.aviso);
    }
}