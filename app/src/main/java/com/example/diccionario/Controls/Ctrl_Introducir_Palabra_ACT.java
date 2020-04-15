package com.example.diccionario.Controls;

import android.text.Editable;

import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;

public class Ctrl_Introducir_Palabra_ACT {


    /**
     * Nos comprueba antes de llamar al método 'insertarPalabra' que todos los campos EditText
     * estén completados/rellenos
     * */
    public static int checking_CamposCompletados(String eng, String esp, String tipo) {
        int res = 1;
        //Todos los campos están vacíos
        if((eng == null || eng.isEmpty()) || (esp == null || esp.isEmpty()) || (tipo == null || tipo.isEmpty() )) {
            res = 0;
        }
        return res;
    }

    /**
     * Método que llamará a 'insertar_EntradaDiccionario' y este nos insertará uina palabra en nuestra DB
     * */
    public static int insertarPalabra(String eng, String esp, String tipo){
        Entrada_Diccionario entrada = new Entrada_Diccionario(eng, esp, tipo);

        return Diccionario_DAO.getInstance().insertar_EntradaDiccionario(entrada);
    }
}
