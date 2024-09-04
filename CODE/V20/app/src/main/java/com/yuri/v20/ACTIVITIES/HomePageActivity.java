package com.yuri.v20.ACTIVITIES;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yuri.v20.FRAGMENTS.CategoriaFragment;
import com.yuri.v20.FRAGMENTS.HomeFragment;
import com.yuri.v20.R;

public class HomePageActivity extends AppCompatActivity {

    private BottomNavigationView BNVG;
    private FrameLayout frame;
    private FragmentManager fragmentManager;

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

        pegarViews();

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
        frame = findViewById(R.id.frame);
    }
}