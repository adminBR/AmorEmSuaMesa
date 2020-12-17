package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    public ArrayList<ProductsClass> nomes = new ArrayList<>();

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBarConfig();
        prepareItems();
        recViewConfig();


        loadActivityButton((Button)findViewById(R.id.btnCarrinho),Carrinho.class);
        loadActivityButton((Button)findViewById(R.id.botao),favoritos.class);
    }

    void loadActivityButton(Button btn,Class cls){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,cls));
            }
        });
    }

    void actionBarConfig(){
        toolbar = findViewById(R.id.mainActionBar);
        setSupportActionBar(toolbar);
    }

    void recViewConfig(){
        rv = (RecyclerView)findViewById(R.id.recycleView);

        MyAdapter ma = new MyAdapter(nomes);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(ma);

    }
    private void prepareItems(){
        for(int i = 0; i < 50; i++) {
            ProductsClass items = new ProductsClass(i,"Produto #"+i,"R$: "+20+i+",00");
            nomes.add(items);
        }
    }
}