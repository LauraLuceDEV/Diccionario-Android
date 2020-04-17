package com.example.diccionario.Activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;


public class ResultadoFinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);

        ImageButton btnJugar = findViewById(R.id.btn_volverJugar);
        ImageButton btnMenu = findViewById(R.id.btn_volverMenu);
        TextView txtFinal = findViewById(R.id.tv_Result_Final);
        NavigationView menuLateral = findViewById(R.id.navview);

        //Bundle
        int bundle_resPunt = getIntent().getIntExtra("puntuacion", 0);
        int bundle_resPreg = getIntent().getIntExtra("contador", 0);
        txtFinal.setText("¡HA OBTENIDO " + bundle_resPunt + " PUNTOS DE " + bundle_resPreg + "!");


        //Listeners
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoFinalActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoFinalActivity.this, EjercicioAct.class);
                startActivity(intent);
                finish();

            }
        });


        //NAVIGATION VIEW - Menu Lateral
        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(ResultadoFinalActivity.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);

                        break;
                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(ResultadoFinalActivity.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(ResultadoFinalActivity.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(ResultadoFinalActivity.this, OpcionesPreferenciasAct.class);
                        startActivity(int_Preferencias);
                        break;
                }
                return true;
            }
        });
    }
}
