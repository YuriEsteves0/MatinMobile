package com.yuri.v20.ACTIVITIES;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yuri.v20.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText senhaUsu, senhaRepetUsu;
    private boolean isPasswordVisible = false;

    @SuppressLint("ClickableViewAccessibility")
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

        senhaUsu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_END = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (senhaUsu.getRight() - senhaUsu.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                        if (isPasswordVisible) {
                            // Oculta a senha
                            senhaUsu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhoaberto, 0);
                            senhaRepetUsu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            senhaRepetUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhoaberto, 0);
                        } else {
                            // Mostra a senha
                            senhaUsu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhofechado, 0);
                            senhaRepetUsu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            senhaRepetUsu.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.olhofechado, 0);
                        }
                        isPasswordVisible = !isPasswordVisible;
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
    }
}