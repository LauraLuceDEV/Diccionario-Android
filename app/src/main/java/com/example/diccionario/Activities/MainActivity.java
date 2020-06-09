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
import android.widget.Button;
import android.widget.Toast;
import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    //Var statica para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Introd_palab_ACT = findViewById(R.id.introd_palabras);
        Button btn_Consult_palab_ACT = findViewById(R.id.consult_palabras);
        Button btn_Ejercicios_ACT = findViewById(R.id.test_palabras);
        Button btn_juegoFlechas = findViewById(R.id.ejercicioFlechas);
        Button btn_juegoComecocos = findViewById(R.id.ejercicioComecocos);
        Button btn_preferencias = findViewById(R.id.menuPref);
        NavigationView menuLateral = findViewById(R.id.navview);
        this.opcionBackUp = 0;


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

        //Realización ejercicios tipo test
        btn_Ejercicios_ACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_Intro_palabra = new Intent(MainActivity.this, EjercicioAct.class);
                startActivity(int_Intro_palabra);
            }
        });

        //Realización ejercicios unir con flechas
        btn_juegoFlechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_EjercicioFlechas = new Intent(MainActivity.this, FlechasAct.class);
                startActivity(int_EjercicioFlechas);
            }
        });

        //Realización juego comecocos
        //TODO: Cambiar a ventana de juego-comecocos cuando esté creada
        btn_juegoComecocos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_EjercicioComecocos = new Intent(MainActivity.this, FlechasAct.class);
                startActivity(int_EjercicioComecocos);
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
                    //Ejercicios
                    case R.id.ejercicioTipoTest:
                        Intent int_Ejercicio1 = new Intent(MainActivity.this, EjercicioAct.class);
                        startActivity(int_Ejercicio1);
                        break;

                    case R.id.ejercicioTipoFlecha:
                        Intent int_Ejercicio2 = new Intent(MainActivity.this, FlechasAct.class);
                        startActivity(int_Ejercicio2);
                        break;

                    case R.id.ejercicioTipoComecocos:
                        Intent int_Ejercicio3 = new Intent(MainActivity.this, EjercicioAct.class);
                        startActivity(int_Ejercicio3);
                        break;
                        //Config
                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(MainActivity.this, OpcionesPreferenciasAct.class);
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

        }else{
            if(this.opcionBackUp == 1){
                String res = CtrlPPL.realizarBackUpCCTRL();
                Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();

            }else{
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(MainActivity.this, res2, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permisos: {
                // If request is cancelled, the result arrays are empty.
                boolean permisosAceptados = true;
                for(int i = 0; i < grantResults.length; i++){
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        permisosAceptados = false;
                    }
                }

                if (permisosAceptados) {
                    if(this.opcionBackUp == 1){
                        String res = CtrlPPL.realizarBackUpCCTRL();
                        Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();

                    }else{
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(MainActivity.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
