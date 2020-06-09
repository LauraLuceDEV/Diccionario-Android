package com.example.diccionario.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.Controls.Ctrl_Ejercicios_ConfigACT;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class OpcionesPreferenciasAct extends AppCompatActivity {
    private Spinner numPreg;
    private Spinner numSeg;
    //Var estática para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_preferencias);
        NavigationView menuLateral = findViewById(R.id.navview);

        this.opcionBackUp = 0;

        //Obtener preferencias
        SharedPreferences pref = getSharedPreferences("test", Context.MODE_PRIVATE);

        //Controlador
        Ctrl_Ejercicios_ConfigACT control = new Ctrl_Ejercicios_ConfigACT(pref.getInt("numPreg", 5), pref.getInt("numSeg", 10));

        //Rellenamos Spiners
        this.numPreg = findViewById(R.id.numPregTest);
        this.numSeg = findViewById(R.id.numSegTest);

        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource(this, R.array.valoresPref, android.R.layout.simple_spinner_item);
        this.numPreg.setAdapter(adap1);
        this.numSeg.setAdapter(adap1);


        //Ver en el Spinner los Valores actuales
        int numPreguntasActuales = pref.getInt("numPreg", 5);
        int numSegundosActuales = pref.getInt("numSeg", 10);

        this.numPreg.setSelection(control.obtenerPosicionSpinner(numPreguntasActuales), false);
        this.numSeg.setSelection(control.obtenerPosicionSpinner(numSegundosActuales), false);
        //------------------------
        //EVENTOS SPINNERS

        //SpinnerNum-Preguntas
        this.numPreg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //OBTENEMOS SHARED PREFERENCES PARA MODIFICALOS
                SharedPreferences pref = getSharedPreferences("test", Context.MODE_PRIVATE);
                SharedPreferences.Editor numPregEDIT = pref.edit();

                switch (position) {
                    case 0:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de " + numPreg.getSelectedItem().toString() + " preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 10 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 15 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 3:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 20 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 4:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 25 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 5:
                        numPregEDIT.putInt("numPreg", Integer.parseInt(numPreg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 30 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

        //Spiner num-Seg
        this.numSeg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //OBTENEMOS SHARED PREFERENCES PARA MODIFICALOS
                SharedPreferences pref = getSharedPreferences("test", Context.MODE_PRIVATE);
                SharedPreferences.Editor numPregEDIT = pref.edit();

                switch (position) {
                    case 0:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Modo 'SPEED-RUN' debes responder en 5 segundos o menos!", Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡Dispondrás de 10 segundos para responder!", Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡¡Dispondrás de 15 segundos para responder!!", Toast.LENGTH_LONG).show();
                        break;

                    case 3:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡¡Dispondrás de 20 segundos para responder!!", Toast.LENGTH_LONG).show();
                        break;

                    case 4:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡¡Dispondrás de 25 segundos para responder!", Toast.LENGTH_LONG).show();
                        break;

                    case 5:
                        numPregEDIT.putInt("numSeg", Integer.parseInt(numSeg.getSelectedItem().toString()));
                        numPregEDIT.apply();
                        Toast.makeText(parent.getContext(), "¡¡Dispondrás de 30 segundos para responder!!", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });


        //NAVIGATION VIEW - Menu Lateral
        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(OpcionesPreferenciasAct.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);

                        break;
                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(OpcionesPreferenciasAct.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(OpcionesPreferenciasAct.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(OpcionesPreferenciasAct.this, OpcionesPreferenciasAct.class);
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
                Toast.makeText(OpcionesPreferenciasAct.this, res, Toast.LENGTH_SHORT).show();

            } else {
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(OpcionesPreferenciasAct.this, res2, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(OpcionesPreferenciasAct.this, res, Toast.LENGTH_SHORT).show();

                    } else {
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(OpcionesPreferenciasAct.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(OpcionesPreferenciasAct.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
