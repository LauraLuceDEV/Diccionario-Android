package com.example.diccionario.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diccionario.R;

public class MainActivity extends AppCompatActivity {
    private Button btn_Introd_palab_ACT;
    private Button btn_Consult_palab_ACT;
    private Button btn_Ejercicios_ACT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn_Introd_palab_ACT = findViewById(R.id.introd_palabras);
        this.btn_Consult_palab_ACT = findViewById(R.id.consult_palabras);
        this.btn_Ejercicios_ACT = findViewById(R.id.test_palabras);

        //-------------------
        // ACCIONES - LISTENERS
        //-------------------
        //Introducir palabra
        this.btn_Introd_palab_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, IntroducirPalabraAct.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Consultar palabras
        this.btn_Consult_palab_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, ConsultasActivity.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Realización ejercicios
        this.btn_Ejercicios_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, EjercicioAct.class);
                startActivity(int_Intro_palabra);
            }
        });


    }
}
