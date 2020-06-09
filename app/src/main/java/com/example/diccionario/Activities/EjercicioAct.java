package com.example.diccionario.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.Controls.Ctrl_Ejercicios_ConfigACT;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;


public class EjercicioAct extends AppCompatActivity {
    private Button btn1_1;
    private Button btn1_2;
    private Button btn2_1;
    private Button btn2_2;
    private TextView palabra_Adivinar;
    private Boolean acierto;
    private int contador_Aciertos;
    private int contador_Pregutas;
    private Ctrl_Ejercicios_ConfigACT control;
    private boolean butonClick;
    private NavigationView menuLateral;
    private Handler handler;
    //Var estática para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        handler = new Handler();

        this.opcionBackUp = 0;
        this.btn1_1 = findViewById(R.id.btn1_1);
        this.btn1_2 = findViewById(R.id.btn1_2);
        this.btn2_1 = findViewById(R.id.btn2_1);
        this.btn2_2 = findViewById(R.id.btn2_2);
        this.palabra_Adivinar = findViewById(R.id.tv_Test);
        this.menuLateral = findViewById(R.id.navview);
        butonClick = false;

        //SharedPreferences por defecto: 5preg / 10seg
        SharedPreferences pref = getSharedPreferences("test", Context.MODE_PRIVATE);

        //Le pasamos por el constructor los elementos de las sharedPreference
        control = new Ctrl_Ejercicios_ConfigACT(pref.getInt("numPreg", 5), pref.getInt("numSeg", 10));

        refrescarElementos();

        //1_1_BTN
        this.btn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butonClick = true;
                acierto = control.comprobarCoincidencias(btn1_1.getText().toString(), palabra_Adivinar.getText().toString());

                if (acierto) {
                    contador_Aciertos++;
                }
                refrescarElementos();
            }
        });

        //1_2_BTN
        this.btn1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butonClick = true;
                acierto = control.comprobarCoincidencias(btn1_2.getText().toString(), palabra_Adivinar.getText().toString());

                if (acierto) {
                    contador_Aciertos++;
                }
                refrescarElementos();

            }
        });

        //2_1_BTN
        this.btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butonClick = true;
                acierto = control.comprobarCoincidencias(btn2_1.getText().toString(), palabra_Adivinar.getText().toString());

                if (acierto) {
                    contador_Aciertos++;
                }
                refrescarElementos();

            }
        });

        //2_2_BTN
        this.btn2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butonClick = true;
                acierto = control.comprobarCoincidencias(btn2_2.getText().toString(), palabra_Adivinar.getText().toString());

                if (acierto) {
                    contador_Aciertos++;
                }
                refrescarElementos();
            }
        });

        //NAVIGATION VIEW - Menu Lateral
        this.menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(EjercicioAct.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);

                        break;
                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(EjercicioAct.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(EjercicioAct.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(EjercicioAct.this, OpcionesPreferenciasAct.class);
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
                Toast.makeText(EjercicioAct.this, res, Toast.LENGTH_SHORT).show();

            }else{
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(EjercicioAct.this, res2, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(EjercicioAct.this, res, Toast.LENGTH_SHORT).show();

                    }else{
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(EjercicioAct.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(EjercicioAct.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void refrescarElementos() {

        List<Entrada_Diccionario> lista_palabras = new ArrayList<Entrada_Diccionario>();
        Boolean elem_Exist = control.refrescarElementos_ctrl(lista_palabras);
        handler.removeCallbacksAndMessages(null);
        if (elem_Exist) {
            butonClick = false;
            this.palabra_Adivinar.setText(lista_palabras.get(0).getPalabra_esp());
            List<Entrada_Diccionario> lista_desordenada = control.obtenerElemDesordenados(lista_palabras);

            this.btn1_1.setText(lista_desordenada.get(0).getPalabra_ing());
            this.btn1_2.setText(lista_desordenada.get(1).getPalabra_ing());
            this.btn2_1.setText(lista_desordenada.get(2).getPalabra_ing());
            this.btn2_2.setText(lista_desordenada.get(3).getPalabra_ing());

            contador_Pregutas++;

            //Pausa de pantalla
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!butonClick){
                        refrescarElementos();
                    }
                }
            }, control.gettiempoTestSegundos() * 1000);

        } else {
            Bundle bundPutuacion = new Bundle();
            bundPutuacion.putInt("puntuacion", contador_Aciertos);
            bundPutuacion.putInt("contador", contador_Pregutas);

            Intent intent = new Intent(EjercicioAct.this, ResultadoFinalActivity.class);
            intent.putExtras(bundPutuacion);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        butonClick = true;
    }
}
