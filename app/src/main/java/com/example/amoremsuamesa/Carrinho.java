package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Carrinho extends AppCompatActivity {

    public List<View> produtosNoCarrinho = new ArrayList<>();
    Inflater inflater;
    String ValorTotal = "R$ 00,00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        loadCart();

        findViewById(R.id.carrinhoFinalizar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsAppClick2(v);
            }
        });

    }
    public void onClickWhatsApp(View view) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public void whatsAppClick2(View v){
        String venda = "Olá, quero comprar: \n";
        for(int i = 0; i < staticStorageClass.carrinho.size(); i++){
            if(i == 0){
                venda = venda+staticStorageClass.carrinho.get(i).getNome()+"\n";
            }else if(i == staticStorageClass.carrinho.size()-1){
                venda = venda+staticStorageClass.carrinho.get(i).getNome()+"\n";
            }else{
                venda = venda+staticStorageClass.carrinho.get(i).getNome()+", \n";
            }
        }
        venda = venda+"valor total = "+ValorTotal;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=5577981048558&text="+venda));
        startActivity(browserIntent);

    }

    public void loadCart(){
        LinearLayout cartLayout = findViewById(R.id.carrinhoLlayout);
        cartLayout.removeAllViews();
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
            new DownloadImageTask((ImageView) imageView,(RelativeLayout)view.findViewById(R.id.loadingPanel) ).execute(staticStorageClass.carrinho.get(i).getImages().get(0)); //baixar img


            int finali = i;
            view.findViewById(R.id.btnRemover).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    staticStorageClass.carrinho.remove(finali);
                    loadCart();
                }
            });
            cartLayout.addView(view);
        }
        TextView TVtotal = findViewById(R.id.carrinhoTotal);
        TVtotal.setText("R$ "+total+",00");
        ValorTotal = "R$ "+total+",00";
    }
}