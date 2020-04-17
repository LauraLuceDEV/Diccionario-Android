package com.example.diccionario.Adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.R;

import java.util.Locale;

public class Adaptador_Entradas extends ArrayAdapter<Entrada_Diccionario> {
    private final Locale spanish;
    private TextToSpeech textToSpeech;

    public Adaptador_Entradas(@NonNull Context context, @NonNull Entrada_Diccionario[] lst) {
        super(context, R.layout.listview_entradasdiccionario,lst);
        spanish = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_entradasdiccionario, null);

        final Entrada_Diccionario entrada = getItem(position);

        TextView tvPalabraIngles = item.findViewById(R.id.palabra_ingles);
        tvPalabraIngles.setText(entrada.getPalabra_ing());

        TextView tvPalabraEsp = item.findViewById(R.id.palabra_espannol);
        tvPalabraEsp.setText(entrada.getPalabra_esp());

        TextView tvPalabraTipo = item.findViewById(R.id.palabra_tipo);
        tvPalabraTipo.setText(entrada.getPalabraTipo());

        TextView tvFechaIntro = item.findViewById(R.id.palabra_fechaintro);
        tvFechaIntro.setText(CtrlPPL.obtenerFechaenString(entrada.getFechaIntro()));

        TextView tvFechaLatest = item.findViewById(R.id.palabra_fechaLastTest);
        tvFechaLatest.setText(CtrlPPL.obtenerFechaenString(entrada.getFecha_latest_test()));

        TextView tvPalabraAciertos = item.findViewById(R.id.palabra_aciertos);
        tvPalabraAciertos.setText(String.valueOf(entrada.getNumAciertos()));

        //BOTONES
        ImageButton imgBtn_IngSound = item.findViewById(R.id.iBtn_SoundEng);
        ImageButton imgBtn_EspSound = item.findViewById(R.id.iBtn_SoundEsp);

        imgBtn_EspSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(spanish);
                textToSpeech.speak(entrada.getPalabra_esp(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        imgBtn_IngSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(Locale.ENGLISH);
                textToSpeech.speak(entrada.getPalabra_ing(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return item;
    }
}
