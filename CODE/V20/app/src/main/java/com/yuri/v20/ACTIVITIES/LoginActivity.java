package com.yuri.v20.ACTIVITIES;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yuri.v20.HELPER.AndroidHelper;
import com.yuri.v20.MODEL.Usuario;
import com.yuri.v20.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText emailUsu, senhaUsu;
    private TextView aviso;

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

        String email = emailUsu.getText().toString();
        String senha = senhaUsu.getText().toString();

        if(email.isEmpty() || senha.isEmpty()){
            aviso.setVisibility(View.VISIBLE);
        }else{
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    AndroidHelper.trocarTela(LoginActivity.this, HomePageActivity.class);
                }
            });
        }
    }

    public void encontrarIDs(){
        btnEntrar = findViewById(R.id.btnEntrar);
        emailUsu = findViewById(R.id.emailUsu);
        senhaUsu = findViewById(R.id.senhaUsu);
        aviso = findViewById(R.id.aviso);
    }
}