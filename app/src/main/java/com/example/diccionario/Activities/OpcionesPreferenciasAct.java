package com.example.diccionario.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.diccionario.Controls.Ctrl_Ejercicios_ConfigACT;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class OpcionesPreferenciasAct extends AppCompatActivity {
    private Spinner numPreg;
    private Spinner numSeg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_preferencias);
        NavigationView menuLateral = findViewById(R.id.navview);

        //Obtener preferencias
        SharedPreferences pref = getSharedPreferences("test", Context.MODE_PRIVATE);

        //Controlador
        Ctrl_Ejercicios_ConfigACT control = new Ctrl_Ejercicios_ConfigACT(pref.getInt("numPreg", 5), pref.getInt("numSeg", 10));;

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
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de " +numPreg.getSelectedItem().toString()+ " preguntas-test !", Toast.LENGTH_LONG).show();
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
                }
                return true;
            }
        });


    }
}
