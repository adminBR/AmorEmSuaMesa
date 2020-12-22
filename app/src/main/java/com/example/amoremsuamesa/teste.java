package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class teste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

       




            TextView prod_nome = findViewById(R.id.prod_nome);
            prod_nome.setText(staticStorageClass.selectedProduct.getNome());



            //TextView id_Preco = findViewById(R.id.id_Preco);
            //id_Preco.setText(staticStorageClass.carrinho.get(i).getPreco());






         findViewById(R.id.prod_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticStorageClass.carrinho.add(staticStorageClass.selectedProduct);
                startActivity(new Intent(teste.this, MainActivity.class));
            }
        });

        RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float nota = mBar.getRating();
                Toast toast = Toast.makeText(v.getContext(),""+nota,Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}