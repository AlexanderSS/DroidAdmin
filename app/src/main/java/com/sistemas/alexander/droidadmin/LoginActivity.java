package com.sistemas.alexander.droidadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText user;
    EditText pass;
    Button acceso;
    JSONParser jParser = new JSONParser();
    public String nombres = "";
    public String cargo = "";
    public int rol = 1;

    String IP_Server = "192.168.0.12";
    String URL_connect = "http://" + IP_Server + "/admin/android/r_android/acceso";
    //String URL_connect = "http://pruebanew.esy.es/index.php/android/r_android/acceso";

    boolean result_back;
    private ProgressDialog pDialog;

    JSONObject resultado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtContrasena);
        acceso = (Button) findViewById(R.id.btnAcceder);

        acceso.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String usuario = user.getText().toString();
                String contra = pass.getText().toString();

                if (checklogindata(usuario, contra) == true) {
                    new asynclogin().execute(usuario, contra);

                } else {
                    err_login();
                }

            }
        });
    }

    public boolean checklogindata(String username, String password) {

        if (username.equals("") || password.equals("")) {
            Log.e("Login ui", "checklogindata user or pass error");
            return false;

        } else {

            return true;
        }

    }

    public void err_login() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast error = Toast.makeText(getApplicationContext(),"Error: Nombre de usuario o contraseña incorrecto",Toast.LENGTH_SHORT);
        error.show();

    }

    class asynclogin extends AsyncTask<String, String, String> {

        String user, pass;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Autenticando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            user = args[0];
            pass = args[1];

            if (loginStatus(user, pass) == true) {
                return "ok";
            } else {
                return "err";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            pDialog.dismiss();// ocultamos progess dialog.
            Log.e("onPostExecute=", "" + result);

            if (result.equals("ok")) {

                Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                i.putExtra("user", user);
                i.putExtra("nombres", nombres);
                i.putExtra("cargo", cargo);
                i.putExtra("rol", rol);
                startActivity(i);

            } else {
                err_login();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public boolean loginStatus(String username, String password) {
        // TODO Auto-generated method stuba
        int logstatus = 1;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("usuario", username));
        params.add(new BasicNameValuePair("password", password));

        JSONObject json = jParser.makeHttpRequest(URL_connect, "POST",params);
        Log.d("JSON: ", json.toString());
        if (json != null && json.length() > 0) {
            try {
                resultado = json.getJSONObject("resultado");
                //JSONObject c = resultado.getJSONObject(0);
                logstatus = resultado.getInt("logstatus");
                nombres = resultado.getString("Nombres");
                cargo = resultado.getString("Cargo");
                rol = resultado.getInt("rol");
                Log.e("loginstatus", "logstatus= " + logstatus);
                Log.e("Nombres", "Nombres= " +nombres);
                Log.e("Cargo", "Cargo= " +cargo);
                Log.e("Rol", "Rol= " +rol);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (logstatus == 0) {
                Log.e("loginstatus ", "invalido");
                return false;
            } else {
                Log.e("loginstatus ", "valido");
                return true;
            }

        } else {
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // no hacemos nada.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
