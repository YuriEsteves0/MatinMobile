package com.yuri.v20.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri.v20.MODEL.Produto;
import com.yuri.v20.R;

import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    private ArrayList<Produto> produtoList = new ArrayList<>();
    private Context context;

    public ProdutoAdapter(Context context, ArrayList<Produto> produtoList) {
        this.produtoList = produtoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_modelo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.fotoProd1.setImageResource(produto.getImagemResId());
         holder.nomeProd1.setText(produto.getNomeProd());
         holder.precoProd1.setText(String.format("R$ %.2f", produto.getPrecoProd()));
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView fotoProd1;
        public TextView nomeProd1;
        public TextView precoProd1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoProd1 = itemView.findViewById(R.id.fotoProd1);
            nomeProd1 = itemView.findViewById(R.id.nomeProd1);
            precoProd1 = itemView.findViewById(R.id.precoProd1);
        }
    }
}
