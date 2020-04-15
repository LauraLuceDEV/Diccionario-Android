package com.example.diccionario.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.diccionario.Controls.Ctrl_Ejercicios_ConfigACT;
import com.example.diccionario.R;

public class OpcionesPreferenciasAct extends AppCompatActivity {
    private Spinner numPreg;
    private Spinner numSeg;
    private Ctrl_Ejercicios_ConfigACT ctrl = Ctrl_Ejercicios_ConfigACT.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_preferencias);

        //Rellenamos Spiners
        this.numPreg = findViewById(R.id.numPregTest);
        this.numSeg = findViewById(R.id.numSegTest);

        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource(this, R.array.valoresPref, android.R.layout.simple_spinner_item);
        this.numPreg.setAdapter(adap1);
        this.numSeg.setAdapter(adap1);

        //EVENTOS SPINNERS
        this.numPreg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 5 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 10 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 15 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 3:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 20 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 4:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 25 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;

                    case 5:
                        ctrl.setNumEjecuciones(Integer.parseInt(numPreg.getSelectedItem().toString()));
                        Toast.makeText(parent.getContext(), "¡Tu lista de ejercicios ahora dispondrá de 30 preguntas-test !", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });


    }
}
