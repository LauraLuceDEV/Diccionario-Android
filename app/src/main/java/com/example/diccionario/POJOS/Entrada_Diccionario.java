package com.example.diccionario.POJOS;

import java.util.Calendar;
import java.util.Date;

public class Entrada_Diccionario {
    private String palabra_ing;
    private String palabra_esp;
    private String palabra_tipo;
    private Date fecha_intro;
    private  Date fecha_latest_test;
    private int num_aciertos;

    //CONSTRUCTORES
    public Entrada_Diccionario(){
        this.num_aciertos = 0;
    }

    public Entrada_Diccionario(String palabra_ing, String palabra_esp, String palabra_tipo){
        this.palabra_ing = palabra_ing;
        this.palabra_esp = palabra_esp;
        this.palabra_tipo = palabra_tipo;

        Date currentTime = Calendar.getInstance().getTime();
        this.fecha_intro = currentTime;
        this.fecha_latest_test = currentTime;
        this.num_aciertos = 0;

    }

    public Entrada_Diccionario(String palabra_ing, String palabra_esp, String palabra_tipo,
                               Date fecha_intro, Date fecha_latest_test){
        this.palabra_ing = palabra_ing;
        this.palabra_esp = palabra_esp;
        this.palabra_tipo = palabra_tipo;
        this.fecha_intro = fecha_intro;
        this.fecha_latest_test = fecha_latest_test;
        this.num_aciertos = 0;
    }

    public Entrada_Diccionario(String palabra_ing, String palabra_esp, String palabra_tipo,
                               Date fecha_intro, Date fecha_latest_test, int num_aciertos){
        this.palabra_ing = palabra_ing;
        this.palabra_esp = palabra_esp;
        this.palabra_tipo = palabra_tipo;
        this.fecha_intro = fecha_intro;
        this.fecha_latest_test = fecha_latest_test;
        this.num_aciertos = 0;
    }

    //SETTER Y GETTER
    public void setPalabra_ing(String palabra_ing){ this.palabra_ing = palabra_ing;}
    public void setPalabra_esp(String palabra_esp){ this.palabra_esp = palabra_esp;}
    public void setPalabra_tipo(String palabra_tipo){ this.palabra_tipo = palabra_tipo;}
    public void setFecha_intro(Date fecha_intro){ this.fecha_intro = fecha_intro;}
    public void setFecha_latest_test(Date fecha_latest_test){ this.fecha_latest_test = fecha_latest_test;}
    public  void setNumAciertos(int num){this.num_aciertos = num;}

    public String getPalabra_ing(){return this.palabra_ing;}
    public String getPalabra_esp(){return this.palabra_esp;}
    public String getPalabraTipo(){return this.palabra_tipo;}
    public  Date getFechaIntro(){return this.fecha_intro;}
    public Date getFecha_latest_test(){return this.fecha_latest_test;}
    public int getNumAciertos(){return this.num_aciertos;}
}
