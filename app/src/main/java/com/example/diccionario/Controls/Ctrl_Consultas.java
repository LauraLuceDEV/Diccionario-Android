package com.example.diccionario.Controls;

import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;

import java.util.List;

public class Ctrl_Consultas {

    /**
     * Obtenermos el Listado de las entradas que tenemos en la DB
     * Lo tenemos que transformar a ARRAY para poder rellenar el ListView
     * */
    public static Entrada_Diccionario[] obtenerListadoEntradas() {
        List<Entrada_Diccionario> entradaList = Diccionario_DAO.getInstance().getListadoEntradas();

        return transformarList_Array(entradaList);
    }



    /**
     * Obtenemos un Listacon con las consultas del DAO
     * Y le realizamos una trasnformación a ARRAY
     *
     * @param op_AlfaNum    : Órden Alfabético/Núm aciertos
     * @param op_IngEsp     : Órden Inglés/Español
     * @param op_PalabExpres: : Órden Palabra/Expresión
     * */
    public static Entrada_Diccionario[] getArrayConsultasSpinners(String op_AlfaNum, String op_IngEsp, String op_PalabExpres) {
        List<Entrada_Diccionario> entradaList = Diccionario_DAO.getInstance().obtenerArrayConsultas_Spinner(op_AlfaNum, op_IngEsp, op_PalabExpres);
        return transformarList_Array(entradaList);
    }

    /**
     * Obtenemos un Listacon con las consultas del DAO
     * Y le realizamos una trasnformación a ARRAY
     *
     * @param editText_palabra : Valor del EditText
     * */
    public static Entrada_Diccionario[] getArrayConsultasEditText(String editText_palabra) {
        List<Entrada_Diccionario> entradaList = Diccionario_DAO.getInstance().obtenerArrayConsultas_EditText(editText_palabra);
        return transformarList_Array(entradaList);
    }

    /**
     * Método que nos transformará un Listado a Array
     * */
    private static Entrada_Diccionario[] transformarList_Array(List<Entrada_Diccionario> entradaList){
        Entrada_Diccionario[] entDicc = new Entrada_Diccionario[entradaList.size()];

        for(int i=0; i < entradaList.size(); i++){
            entDicc[i] = entradaList.get(i);
        }
        return entDicc;

    }
}
