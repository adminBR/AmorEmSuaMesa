package com.example.amoremsuamesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.zip.Inflater;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        viewFlipper.setDisplayedChild(0);

        if(staticStorageClass.usuarioID == ""){ //checar melhor no firebase
            viewFlipper.setDisplayedChild(1);
        }



        viewFlipper.findViewById(R.id.btnFazerCadastro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(2);
            }
        });


    }

    public void activityInfo(){

    }
    public void finalizarCadastro(){

    }
}