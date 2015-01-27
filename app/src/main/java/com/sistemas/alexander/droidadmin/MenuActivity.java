package com.sistemas.alexander.droidadmin;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends Activity {

    TextView vNombres;
    TextView vCargo;
    int xrol;

    private ArrayList<Titular> datos = new ArrayList<Titular>();
    AdaptadorTitulares adaptador;
    public static final int FIN_PROGRAMA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vNombres = (TextView) findViewById(R.id.LblNombres);
        vCargo = (TextView) findViewById(R.id.LblCargo);
        Bundle valores = getIntent().getExtras();
        if (valores != null) {
            String xuser = valores.getString("user");
            setTitle("Usuario: " + xuser);
            String xnombres = valores.getString("nombres");
            vNombres.setText(xnombres);
            String xcargo = valores.getString("cargo");
            vCargo.setText(xcargo);
            xrol = valores.getInt("rol");
        }
        ListView lv = (ListView) findViewById(R.id.OpcMenu);

        switch (xrol) {
            case 1:
                datos.add(new Titular("VENTAS", R.drawable.ventas));
                datos.add(new Titular("CAJA", R.drawable.caja));
                datos.add(new Titular("RRHH", R.drawable.rrhh));
                datos.add(new Titular("ALMACÉN", R.drawable.almacen));
                datos.add(new Titular("CERRAR SESIÓN", R.drawable.destroy1));
                break;

            case 2:
                datos.add(new Titular("VENTAS", R.drawable.ventas));
                datos.add(new Titular("CAJA", R.drawable.caja));
                datos.add(new Titular("CERRAR SESIÓN", R.drawable.destroy1));
                break;

            case 3:
                datos.add(new Titular("ALMACÉN", R.drawable.almacen));
                datos.add(new Titular("CERRAR SESIÓN", R.drawable.destroy1));
                break;

            case 4:
                datos.add(new Titular("RRHH", R.drawable.rrhh));
                datos.add(new Titular("CERRAR SESIÓN", R.drawable.destroy1));
                break;
        }

        adaptador = new AdaptadorTitulares(this, R.layout.listitem_titular,
                datos) {

            @Override
            public void onEntrada(Object entrada, View view) {
                // TODO Auto-generated method stub
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view
                            .findViewById(R.id.LblTitulo);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Titular) entrada)
                                .getTitulo());

                    ImageView imagen_entrada = (ImageView) view
                            .findViewById(R.id.imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((Titular) entrada)
                                .getIdImagen());
                }
            }
        };

        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                escoger_opcion(position);

            }
        });
    }

    protected void escoger_opcion(int posicion) {
        // TODO Auto-generated method stub
        switch (xrol) {
            case 1:
                switch (posicion) {

                    case 0:
                        Intent e = new Intent(this, VentasActivity.class);
                        startActivity(e);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        showDialog(FIN_PROGRAMA);
                }
                break;

            case 2:
                switch (posicion) {

                    case 0:
                        Intent e = new Intent(this, VentasActivity.class);
                        startActivity(e);
                        break;
                    case 1:

                        break;
                    case 2:
                        showDialog(FIN_PROGRAMA);

                }
                break;

            case 3:
                switch (posicion) {

                    case 0:
                        //Intent e = new Intent(this, HiScreen.class);
                        //startActivity(e);
                        //break;
                    case 1:
                        showDialog(FIN_PROGRAMA);
                }
                break;
            case 4:
                switch (posicion) {

                    case 0:
                        //Intent e = new Intent(this, HiScreen.class);
                        //startActivity(e);
                        //break;
                    case 1:
                        showDialog(FIN_PROGRAMA);

                }
                break;
        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        if (id == FIN_PROGRAMA) {
            LayoutInflater contruir = LayoutInflater.from(this);
            final View pantalla = contruir.inflate(R.layout.pantalla_salida,
                    null);
            return new AlertDialog.Builder(MenuActivity .this)
                    .setTitle("Confirmación de Salida")
                    .setView(pantalla)
                    .setPositiveButton("Salir",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Saliendo del Programa, Hasta la próxima",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            })
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).create();
        } else {
            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    //public boolean onKeyDown(int keyCode, KeyEvent event) {
      //  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // no hacemos nada.
        //    return true;
        //}

//        return super.onKeyDown(keyCode, event);
  //  }

}
