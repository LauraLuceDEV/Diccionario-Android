package com.example.diccionario.Activities;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Introd_palab_ACT = findViewById(R.id.introd_palabras);
        Button btn_Consult_palab_ACT = findViewById(R.id.consult_palabras);
        Button btn_Ejercicios_ACT = findViewById(R.id.test_palabras);
        Button btn_preferencias = findViewById(R.id.menuPref);
        NavigationView menuLateral = findViewById(R.id.navview);

        //-------------------
        // ACCIONES - LISTENERS
        //-------------------
        //Introducir palabra
        btn_Introd_palab_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, IntroducirPalabraAct.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Consultar palabras
        btn_Consult_palab_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, ConsultasActivity.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Realización ejercicios
        btn_Ejercicios_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, EjercicioAct.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Acceder a Preferemcias/Configuración
        btn_preferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Pref = new Intent(MainActivity.this, OpcionesPreferenciasAct.class);
                startActivity(int_Pref);
            }
        });

        //NAVIGATION VIEW - Menu Lateral
        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(MainActivity.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);

                        break;
                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(MainActivity.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(MainActivity.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(MainActivity.this, OpcionesPreferenciasAct.class);
                        startActivity(int_Preferencias);
                        break;
                }
                return true;
            }
        });


    }
}
