package com.example.diccionario.Activities;

import android.Manifest;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;


public class ResultadoFinalActivity extends AppCompatActivity {
    //Var estática para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);

        this.opcionBackUp = 0;
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

                    case R.id.realizarBackUp:
                        opcionBackUp = 1;
                        //Permisos
                        chequearPermisos();
                        break;

                    case R.id.resetBackUp:
                        opcionBackUp = 2;
                        //Permisos
                        chequearPermisos();
                        break;
                }
                return true;
            }
        });


    }

    public void chequearPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, permisos);

        } else {
            if (this.opcionBackUp == 1) {
                String res = CtrlPPL.realizarBackUpCCTRL();
                Toast.makeText(ResultadoFinalActivity.this, res, Toast.LENGTH_SHORT).show();

            } else {
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(ResultadoFinalActivity.this, res2, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permisos: {
                // If request is cancelled, the result arrays are empty.
                boolean permisosAceptados = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        permisosAceptados = false;
                    }
                }

                if (permisosAceptados) {
                    if (this.opcionBackUp == 1) {
                        String res = CtrlPPL.realizarBackUpCCTRL();
                        Toast.makeText(ResultadoFinalActivity.this, res, Toast.LENGTH_SHORT).show();

                    } else {
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(ResultadoFinalActivity.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ResultadoFinalActivity.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
