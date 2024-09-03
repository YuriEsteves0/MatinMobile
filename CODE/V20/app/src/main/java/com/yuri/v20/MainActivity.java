package com.yuri.v20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yuri.v20.ACTIVITIES.CadastroActivity;
import com.yuri.v20.ACTIVITIES.LoginActivity;
import com.yuri.v20.HELPER.AndroidHelper;

public class MainActivity extends AppCompatActivity {

    private Button entrarEmail, cadastroBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        encontrarID();

        entrarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidHelper.trocarTela(MainActivity.this, LoginActivity.class);
            }
        });

        cadastroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidHelper.trocarTela(MainActivity.this, CadastroActivity.class);
            }
        });
    }

    public void encontrarID(){
        entrarEmail = findViewById(R.id.entrarEmail);
        cadastroBtn = findViewById(R.id.cadastroBtn);
    }
}