package com.yuri.v20.ACTIVITIES;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yuri.v20.HELPER.AndroidHelper;
import com.yuri.v20.HELPER.FirebaseHelper;
import com.yuri.v20.MODEL.Usuario;
import com.yuri.v20.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText senhaUsu, emailUsu, senhaRepetUsu, nomeUsu;
    private boolean isPasswordVisible = false;
    private boolean isRepetPasswordVisible = false;
    private FirebaseAuth auth = FirebaseHelper.getAuth();
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        encontrarViews();
        listenersOlho();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = senhaUsu.getText().toString();
                String senhaRepet = senhaRepetUsu.getText().toString();

                if(senha.equals(senhaRepet)){
                    String email = emailUsu.getText().toString();
                    String nome = nomeUsu.getText().toString();
                    Log.d("TAG", "onClick: " + email + nome + senha);
                    criarUsuAuth(email, senha, nome);
                }
            }
        });


    }

    public void criarUsuAuth(String email, String senha, String nome){
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String uid = FirebaseHelper.getCurrentUserUid();

                Usuario usuario = new Usuario(uid, email, nome, senha, "cliente");
                salvarUsuFirestore(uid, usuario);
            }
        });

    }

    public void salvarUsuFirestore(String uid, Usuario usuario){
        FirebaseFirestore firestore = FirebaseHelper.getInstance();

        firestore.collection("usuarios").document(uid).set(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        AndroidHelper.fazerToast(getApplicationContext(), "Usuário registrado com sucesso!");
                        Log.d("TAG", "onSuccess: saída 500");
                        AndroidHelper.trocarTela(getApplicationContext(), HomePageActivity.class, CadastroActivity.this);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AndroidHelper.fazerToast(getApplicationContext(), "Erro ao salvar dados do usuário!");
                        Log.d("TAG", "onSuccess: saída 501");
                    }
                });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void listenersOlho(){
        // Listener para o campo de senha
        senhaUsu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_END = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (senhaUsu.getRight() - senhaUsu.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                        if (isPasswordVisible) {
                            // Oculta a senha
                            senhaUsu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhofechado, 0);

                        } else {
                            // Mostra a senha
                            senhaUsu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhoaberto, 0);

                        }
                        isPasswordVisible = !isPasswordVisible;
                        return true;
                    }
                }
                return false;
            }
        });

        // Listener para o campo de repetir senha
        senhaRepetUsu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_END = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (senhaRepetUsu.getRight() - senhaRepetUsu.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                        if (isRepetPasswordVisible) {
                            // Oculta a senha
                            senhaRepetUsu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaRepetUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhofechado, 0);

                        } else {
                            // Mostra a senha
                            senhaRepetUsu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaRepetUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhoaberto, 0);

                        }
                        isRepetPasswordVisible = !isRepetPasswordVisible;
                        return true;
                    }
                }
                return false;
            }
        });

    }

    public void encontrarViews(){
        senhaUsu = findViewById(R.id.senhaUsu);
        senhaRepetUsu = findViewById(R.id.senhaRepetUsu);
        btnEntrar = findViewById(R.id.btnEntrar);
        emailUsu = findViewById(R.id.emailUsu);
        nomeUsu = findViewById(R.id.nomeUsu);
    }
}
