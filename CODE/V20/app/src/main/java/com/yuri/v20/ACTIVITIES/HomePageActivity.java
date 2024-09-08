package com.yuri.v20.ACTIVITIES;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.yuri.v20.FRAGMENTS.CategoriaFragment;
import com.yuri.v20.FRAGMENTS.HomeFragment;
import com.yuri.v20.HELPER.ActivityHelper;
import com.yuri.v20.HELPER.FirebaseHelper;
import com.yuri.v20.MODEL.Usuario;
import com.yuri.v20.MODEL.UsuarioCallback;
import com.yuri.v20.R;

public class HomePageActivity extends AppCompatActivity {

    private BottomNavigationView BNVG;
    private FragmentManager fragmentManager;
    private TextView nomeUsu, emailUsu;
    private ImageButton pesquisarBtn;
    private ImageView fotoUsu;
    private FirebaseAuth auth = FirebaseHelper.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseHelper.verifLogadoSenaoRedirecionar(getApplicationContext(), this);
        pegarViews();
        secoesTop();
        definirDadosHeader();

        pesquisarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + ActivityHelper.qntActivities());
                ActivityHelper.finishAllActivities();
                Log.d("TAG", "onClick: " + ActivityHelper.qntActivities());
            }
        });

        fotoUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                ActivityHelper.finishAllActivities();
                finish();
            }
        });

    }

    public void definirDadosHeader(){
        FirebaseHelper.pegarDadosUsu(new UsuarioCallback() {
            @Override
            public void onCallback(Usuario usuario) {
                nomeUsu.setText(usuario.getNome());
                emailUsu.setText(usuario.getEmail());
            }
        });
    }

    public void secoesTop(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

        BNVG.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();

                if(itemID == R.id.produtos){
                    fragmentManager.beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
                    return true;
                }
                if(itemID == R.id.categorias){
                    fragmentManager.beginTransaction().replace(R.id.frame, new CategoriaFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    public void pegarViews(){
        BNVG = findViewById(R.id.BNVG);
        nomeUsu = findViewById(R.id.nomeUsu);
        emailUsu = findViewById(R.id.emailUsu);
        pesquisarBtn = findViewById(R.id.pesquisarBtn);
        fotoUsu = findViewById(R.id.fotoUsu);
    }
}