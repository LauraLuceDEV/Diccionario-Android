package com.example.diccionario.Models;

import android.os.Environment;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.Utils.DataTransformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MyBackUp_DAO {
    //Obtenemos el directorio raíz de la SD
    private static File root = Environment.getExternalStorageDirectory();

    //Constructores
    public MyBackUp_DAO(){}


    /**
     * Nos realiza una copia de seguridad.
     * Nos guarda todas las entradas de 'Entradas Diccionario' que contendrá nuestro listado en el formato JSON
     * */
    public static String realizarBackUp(List<Entrada_Diccionario> miListadoEntradas){
        String jsonData =  "";
        try {
            JSONArray jsonArray = new JSONArray();
            for (Entrada_Diccionario entrada : miListadoEntradas) {
                JSONObject objeto = new JSONObject();
                objeto.put("palabra_ing", entrada.getPalabra_ing());
                objeto.put("palabra_esp", entrada.getPalabra_esp());
                objeto.put("palabra_tipo", entrada.getPalabraTipo());
                objeto.put("fecha_intro", entrada.getFechaIntro());
                objeto.put("fecha_latest_test", entrada.getFecha_latest_test());
                objeto.put("num_aciertos", entrada.getNumAciertos());
                jsonArray.put(objeto);
            }
            jsonData = jsonArray.toString();
        } catch (JSONException  e) {
            jsonData =  null;

        }catch (Exception e){
            jsonData =  null;
        }
        //Detectamos primero si existe la tarjeta SD
        if(root != null){
            //Ya creado el DIRECTORIO y el FICHERO escribimos en este
            try {
                Writer out = null;
                // File dir = new File(root + "/BackUpDiccionario");
                // File file = new File(dir.getAbsolutePath() + "BackUpDiccionario.json");
                File file = new File(root + "/BackUpDiccionario.json");
                out = new BufferedWriter(new FileWriter(file));
                out.write(jsonData);
                out.close();

            } catch (Exception e) {
                jsonData = null;
            }
        }
        return jsonData;
    }


    //LEER JSON
    private static String leerFicheroJSON() {

        String palabras = "";
        File file = new File(root + "/BackUpDiccionario.json");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String cadenaObtenida = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (cadenaObtenida = bufferedReader.readLine()) != null ) {
                stringBuilder.append(cadenaObtenida);
            }
            palabras = stringBuilder.toString();
        }
        catch (FileNotFoundException e) {
            palabras = "Fichero de BackUp no encontrado";
        } catch (IOException e) {
            palabras = "No s epudo leer el fichero del BackUp";
        }
        return palabras;
    }

    public static List<Entrada_Diccionario> leerBackUp_JSON() {
        List<Entrada_Diccionario> listado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(leerFicheroJSON());

            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                listado.add(new Entrada_Diccionario(objeto.getString("palabra_ing"),
                        objeto.getString("palabra_esp"), objeto.getString("palabra_tipo"),
                        DataTransformation.stringToDate(objeto.getString("fecha_intro")), DataTransformation.stringToDate(objeto.getString("fecha_latest_test")),
                        objeto.getInt("num_aciertos")));
            }
        } catch (JSONException e) {
            listado = null;
        }
        return listado;
    }
}

