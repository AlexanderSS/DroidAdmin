package com.sistemas.alexander.droidadmin;

/**
 * Created by Alexander on 26/01/2015.
 */
public class Titular {

    private String titulo;
    private int idImagen;
    //private String subtitulo;

    public Titular(String tit, int idi) {
        titulo = tit;
        idImagen=idi;
        //subtitulo = sub;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdImagen(){
        return idImagen;
    }

//	public String getSubtitulo() {
//		return subtitulo;
//	}
}
