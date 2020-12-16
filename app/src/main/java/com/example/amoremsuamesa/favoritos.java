package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class favoritos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        LinearLayout imagem_produto = findViewById(R.id.imagem_produto);

        LayoutInflater inflater = LayoutInflater.from(this);
    //ESSA PARTE É SOMENTE PARA TESTAR OS PRODUTOS APARECENDO NA TELA
        for (int i = 1; i == 200 || i < 200; i++) {

            View view = inflater.inflate(R.layout.item, imagem_produto, false);

            TextView textView = view.findViewById(R.id.text);
            textView.setText("PRODUTO " + i);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);

            imagem_produto.addView(view);
        }
    }
}