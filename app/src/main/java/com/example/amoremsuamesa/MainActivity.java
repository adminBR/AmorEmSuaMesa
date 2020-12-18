package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    MyAdapter ma;
    public ArrayList<ProductsClass> nomes = new ArrayList<>();

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareItems();
        recViewConfig();
    }

    void loadActivityButton(Button btn,Class cls){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,cls));
            }
        });
    }

    void recViewConfig(){
        rv = (RecyclerView)findViewById(R.id.recycleView);
        List<ProductsClass> produtosFull = new ArrayList<>();
        produtosFull.addAll(nomes);
        ma = new MyAdapter(nomes,produtosFull);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(ma);

    }
    private void prepareItems(){
        staticStorageClass.ListaCompletaProdutos.clear();
        nomes.clear();
        for(int i = 0; i < 50; i++) {
            ProductsClass items = new ProductsClass(i,"Produto #"+i,""+20+i);
            staticStorageClass.ListaCompletaProdutos.add(items);

        }
        nomes.addAll(staticStorageClass.ListaCompletaProdutos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) searchItem.getActionView();
        //searchView.setIconifiedByDefault(false);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() { //receber input do search view
            @Override
            public boolean onQueryTextSubmit(String query) { //quando clica enviar
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //tempo real
                ma.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cart:
                startActivity(new Intent(MainActivity.this,Carrinho.class));
                return true;
            case R.id.favoritos:
                startActivity(new Intent(MainActivity.this,favoritos.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}