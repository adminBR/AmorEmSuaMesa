package com.example.amoremsuamesa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class teste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);


        TextView prod_nome = findViewById(R.id.prod_nome);
        prod_nome.setText(staticStorageClass.selectedProduct.getNome());

        TextView id_Preco = findViewById(R.id.id_Preco);
        id_Preco.setText("R$ " + staticStorageClass.selectedProduct.getPreco());

        TextView id_descricao = findViewById(R.id.id_descricao);
        id_descricao.setText(staticStorageClass.selectedProduct.getDescricao());

        ImageView image_product = findViewById(R.id.imageButton4);
        new DownloadImageTask((ImageView) image_product).execute(staticStorageClass.selectedProduct.getImages().get(0));

        //ImageView image_product = findViewById(R.id.imageButton4);
        //image_product.setImageResource(staticStorageClass.selectedProduct.getImages());


        findViewById(R.id.prod_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticStorageClass.carrinho.add(staticStorageClass.selectedProduct);
                startActivity(new Intent(teste.this, MainActivity.class));
            }
        });

        RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
        findViewById(R.id.btn_comentar3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float nota = mBar.getRating();
                Toast toast = Toast.makeText(v.getContext(), "" + nota, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        findViewById(R.id.btn_comentar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(teste.this, comentarios.class));
            }
        });


    }
}


