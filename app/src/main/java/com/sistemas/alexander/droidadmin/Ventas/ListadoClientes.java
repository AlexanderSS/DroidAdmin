package com.sistemas.alexander.droidadmin.Ventas;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sistemas.alexander.droidadmin.R;

/**
 * Created by Alexander on 29/06/2015.
 */
public class ListadoClientes extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ventas_listcliente, container, false);
        return rootView;
    }
}
