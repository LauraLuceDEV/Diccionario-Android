package com.example.diccionario.Controls;

import android.content.Context;
import com.example.diccionario.Models.Diccionario_DAO;
import java.util.Date;

public class CtrlPPL {

    /**
     * Preparamos la DBDiccionario que estará embebida en nuestra aplicación
     * */
    public static void preparar_DB_DiccionarioDAO_SQLite(Context context){
        Diccionario_DAO.createInstance(context);
    }

    /**
     * Obtenemos el dato de la fecha en String
     * @param date
     * */public static String obtenerFechaenString(Date date){
        return Diccionario_DAO.getInstance().dateToString(date);
    }
}
