package com.example.diccionario.Controls;

import android.content.Context;
import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.Models.MyBackUp_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import java.util.Date;
import java.util.List;

public class CtrlPPL {

    /**
     * Preparamos la DBDiccionario que estará embebida en nuestra aplicación
     * */
    public static void preparar_DB_DiccionarioDAO_SQLite(Context context){
        Diccionario_DAO.createInstance(context);
    }


    //Nos realiza un fichero JSON cómo BackUp
    public static String realizarBackUpCCTRL(){
        String res = MyBackUp_DAO.realizarBackUp(Diccionario_DAO.getInstance().getListadoEntradas());

        if(res != null && res.length() > 0){
            return "BACKUP REALIZADO";
        }else{
            return "ERROR";
        }
    }


    //Obtenemos de nuestro fichero JSON el último BackUp realizado
    public static String obtenerUltimoBackUp() {
         int res = 0;
        List<Entrada_Diccionario> listado = MyBackUp_DAO.leerBackUp_JSON();
        //Actualizamos la DB
        if(listado != null){
            res = Diccionario_DAO.getInstance().actualizarDatabase(listado);

            if(res == 0){
                return "ERROR";
            }else{
                return "Se ha vuelto a la copia de seguridad guardada";
            }
        }else{
            return "Error, registros de BackUp no encontrados";
        }
    }

    //Obtener el listado de palabras del diccionario
    public static List<Entrada_Diccionario> getEntradasDiccionario(){
        return Diccionario_DAO.getInstance().getListadoEntradas();
    }
}
