package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class Carrinho extends AppCompatActivity {

    Inflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        loadCart();

    }

    public void loadCart(){
        LinearLayout cartLayout = findViewById(R.id.carrinhoLlayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        int total = 0;
        for (int i = 0; i < staticStorageClass.carrinho.size(); i++) {

            View view = inflater.inflate(R.layout.cart_item_layout, cartLayout, false);
            TextView nome = view.findViewById(R.id.cilName);
            nome.setText(staticStorageClass.carrinho.get(i).getNome());
            TextView preco = view.findViewById(R.id.cilPreco);
            preco.setText("R$ "+staticStorageClass.carrinho.get(i).getPreco()+",00");
            TextView uni = view.findViewById(R.id.cilUnidades);
            uni.setText("1 Un.");
            total+=Integer.parseInt(staticStorageClass.carrinho.get(i).getPreco());
            ImageView imageView = view.findViewById(R.id.cilImage);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
            cartLayout.addView(view);
        }
        TextView TVtotal = findViewById(R.id.carrinhoTotal);
        TVtotal.setText("R$ "+total+",00");
    }
}