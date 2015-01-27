package com.sistemas.alexander.droidadmin;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Alexander on 26/01/2015.
 */
public abstract class AdaptadorTitulares extends BaseAdapter {
    private int R_layout_IdView;
    private Context contexto;
    private ArrayList<?> entradas;

    public AdaptadorTitulares(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
        super();
        this.contexto = contexto;
        this.entradas = entradas;
        this.R_layout_IdView = R_layout_IdView;
    }


    @Override
    public View getView(int posicion, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (entradas.get(posicion), view);
        return view;

        // TextView lblSubtitulo = (TextView) item
        // .findViewById(R.id.LblSubTitulo);
        // lblSubtitulo.setText(datos[position].getSubtitulo());
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return entradas.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return entradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public abstract void onEntrada (Object entrada, View view);
}
