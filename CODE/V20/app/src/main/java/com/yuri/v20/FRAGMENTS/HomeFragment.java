package com.yuri.v20.FRAGMENTS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yuri.v20.ADAPTER.ProdutoAdapter;
import com.yuri.v20.HELPER.FirebaseHelper;
import com.yuri.v20.MODEL.Produto;
import com.yuri.v20.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private ImageView indicator1, indicator2, indicator3;
    private ViewFlipper viewFlipper;
    private Handler handler;
    private Runnable runnable;
    private int currentImage = 0;
    private RecyclerView RV;
    private ProdutoAdapter adapter;
    private ArrayList<Produto> produtoList = new ArrayList<>();
    private CollectionReference reference = FirebaseHelper.getInstanceProd();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        pegarViews(view);
        trocarFotoBanner();

        //CONFIG RV

        RV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();

                    if(querySnapshot != null){
                        for (QueryDocumentSnapshot document : querySnapshot){
                            String nomeProd = document.getString("nomeProd");
                            Double precoProd = document.getDouble("precoProd");
                            String imagemResId = document.getString("imagemResId");
//
//                            Produto produto = new Produto(nomeProd, precoProd, imagemResId);

                        }
                    }
                }
            }
        });
        produtoList.add(new Produto("Produto 1", 10.00, R.drawable.kiwi));
        produtoList.add(new Produto("Produto 2", 20.00, R.drawable.kiwi));
        produtoList.add(new Produto("Produto 3", 30.00, R.drawable.kiwi));

        adapter = new ProdutoAdapter(getContext(), produtoList);
        RV.setAdapter(adapter);

        return view;
    }

    public void trocarFotoBanner(){
        indicator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 0;
                trocarIndicador(0);
            }
        });

        indicator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 1;
                trocarIndicador(1);
            }
        });

        indicator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 2;
                trocarIndicador(2);
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentImage++;
                if (currentImage > 2) {
                    currentImage = 0;
                }
                trocarIndicador(currentImage);
                handler.postDelayed(this, 2500);
            }
        };
        handler.postDelayed(runnable, 2500);
    }

    public void trocarIndicador(int position){
        viewFlipper.setDisplayedChild(position);

        indicator1.setImageResource(R.drawable.circle_outline);
        indicator2.setImageResource(R.drawable.circle_outline);
        indicator3.setImageResource(R.drawable.circle_outline);

        switch (position){
            case 0:
                indicator1.setImageResource(R.drawable.circle_filled);
                break;
            case 1:
                indicator2.setImageResource(R.drawable.circle_filled);
                break;
            case 2:
                indicator3.setImageResource(R.drawable.circle_filled);
                break;
        }
    }

    public void pegarViews(View view){
        indicator1 = view.findViewById(R.id.indicator1);
        indicator2 = view.findViewById(R.id.indicator2);
        indicator3 = view.findViewById(R.id.indicator3);
        viewFlipper = view.findViewById(R.id.VS);
        RV = view.findViewById(R.id.RV);
    }

}