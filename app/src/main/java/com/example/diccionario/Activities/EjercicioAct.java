package com.example.diccionario.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.diccionario.Controls.Controlador_Ejercicios_ACT;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.R;

import java.util.ArrayList;
import java.util.List;

public class EjercicioAct extends AppCompatActivity {
    private Button btn1_1;
    private Button btn1_2;
    private Button btn2_1;
    private Button btn2_2;
    private TextView palabra_Adivinar;
    private Controlador_Ejercicios_ACT control;
    private Boolean acierto;
    private int contador_Aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        this.btn1_1 = findViewById(R.id.btn1_1);
        this.btn1_2 = findViewById(R.id.btn1_2);
        this.btn2_1 = findViewById(R.id.btn2_1);
        this.btn2_2 = findViewById(R.id.btn2_2);
        this.palabra_Adivinar = findViewById(R.id.tv_Test);

        control = new Controlador_Ejercicios_ACT();
        refrescarElementos();

        //1_1_BTN
        this.btn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                acierto = control.comprobarCoincidencias(btn2_2.getText().toString(), palabra_Adivinar.getText().toString());

                if (acierto) {
                    contador_Aciertos++;
                }
                refrescarElementos();

            }
        });

    }

    private void refrescarElementos() {
        List<Entrada_Diccionario> lista_palabras = new ArrayList<Entrada_Diccionario>();
        Boolean elem_Exist = control.refrescarElementos_ctrl(lista_palabras);

        if (elem_Exist) {
            this.palabra_Adivinar.setText(lista_palabras.get(0).getPalabra_esp());
            List<Entrada_Diccionario> lista_desordenada = control.obtenerElemDesordenados(lista_palabras);

            this.btn1_1.setText(lista_desordenada.get(0).getPalabra_ing());
            this.btn1_2.setText(lista_desordenada.get(1).getPalabra_ing());
            this.btn2_1.setText(lista_desordenada.get(2).getPalabra_ing());
            this.btn2_2.setText(lista_desordenada.get(3).getPalabra_ing());

        } else {
            Bundle bundPutuacion = new Bundle();
            bundPutuacion.putInt("puntuacion", contador_Aciertos);
            Intent intent = new Intent(EjercicioAct.this, ResultadoFinalActivity.class);
            intent.putExtras(bundPutuacion);
            startActivity(intent);
            finish();
        }
    }
}
