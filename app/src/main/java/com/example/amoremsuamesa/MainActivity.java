package com.example.amoremsuamesa;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    MyAdapter ma;
    public ArrayList<ProductsClass> nomes = new ArrayList<>();

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadData();
        PreencherDB();


        findViewById(R.id.fab_carrinho).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Carrinho.class));
            }
        });
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
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

    }
    private void downloadData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        staticStorageClass.ListaCompletaProdutos.clear();
        nomes.clear();

        db.collection("Produtos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + Integer.parseInt((String)document.get("id")));
                                ProductsClass items = new ProductsClass(document.getId() , Integer.parseInt((String)document.get("id")),(String)document.get("nome"),(String)document.get("descricao"),(List<String>)document.get("imagens"), (String)document.get("preco"));
                                staticStorageClass.ListaCompletaProdutos.add(items);
                            }
                            nomes.addAll(staticStorageClass.ListaCompletaProdutos);
                            recViewConfig();
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public void PreencherDB(){
        findViewById(R.id.btnTemp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 10; i++) {
                    //ProductsClass items = new ProductsClass(i,"Produto #"+i,""+20+i);
                    //staticStorageClass.ListaCompletaProdutos.add(items);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    List<String> images = new ArrayList<>();
                    images.add("localhost1");
                    images.add("localhost2");
                    ProductsClass tempPc = new ProductsClass("",i,"produto #"+i, "descricao do produto #"+i, images,""+(200+i));

                    Map<String, Object> user = new HashMap<>();
                    user.put("id", ""+tempPc.getId());
                    user.put("nome", tempPc.getNome());
                    user.put("descricao", tempPc.getDescricao());
                    user.put("imagens", tempPc.getImages());
                    user.put("preco", tempPc.getPreco());


                    db.collection("Produtos").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast t = Toast.makeText(getApplicationContext(),"salvo",Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Log.w(TAG, "Error adding document", e);
                            Toast t = Toast.makeText(getApplicationContext(),"erro",Toast.LENGTH_SHORT);
                            t.show();
                        }
                    });

                }
            }
        });
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

            case R.id.favoritos:
                startActivity(new Intent(MainActivity.this,favoritos.class));
                return true;
            case R.id.user:
                startActivity(new Intent(MainActivity.this,UsuarioActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}