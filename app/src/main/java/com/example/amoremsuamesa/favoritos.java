package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class favoritos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        LinearLayout imagem_produto = findViewById(R.id.imagem_produto);

        LayoutInflater inflater = LayoutInflater.from(this);
    //ESSA PARTE É SOMENTE PARA TESTAR OS PRODUTOS APARECENDO NA TELA
        for (int i = 1; i == 5 || i < 5; i++) {

            View view = inflater.inflate(R.layout.cart_item_layout, imagem_produto, false);

            TextView textView = view.findViewById(R.id.cilName);
            textView.setText("PRODUTO " + i);

            ImageView imageView = view.findViewById(R.id.cilImage);
            new DownloadImageTask((ImageView) imageView,(RelativeLayout)view.findViewById(R.id.loadingPanel) ).execute(staticStorageClass.carrinho.get(i).getImages().get(0));

            //ImageView imageView = view.findViewById(R.id.cilImage);
            //imageView.setImageResource(R.drawable.amor_em_sua_mesa);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getApplicationContext(), "testanto 123", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            imagem_produto.addView(view);
        }
    }
}