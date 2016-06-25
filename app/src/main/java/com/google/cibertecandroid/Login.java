package com.google.cibertecandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.cibertecandroid.bean.Socio;
import com.google.cibertecandroid.dao.SociosSQLiteHelper;
import com.google.cibertecandroid.util.Metodos;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmailLogin;
    EditText editTextPasswordLogin;
    Button buttonIngresar;
    Button buttonRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(this);

        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        buttonRegistrar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        ArrayList<Socio> socioArrayList = new ArrayList<>();
        SociosSQLiteHelper sociosSQLiteHelpe;
        Socio socio = null;
        switch (view.getId()) {
            case R.id.buttonRegistrar:
                intent = new Intent(this, RegistroDeSocio.class);
                startActivity(intent);
                break;
            case R.id.buttonIngresar:
                editTextEmailLogin = (EditText) findViewById(R.id.editTextEmailLogin);
                editTextPasswordLogin = (EditText) findViewById(R.id.editTextPasswordLogin);

                if (editTextEmailLogin.getText().toString().equals("admin") && editTextPasswordLogin.getText().toString().equals("cibertec")) {
                    intent = new Intent(this, ObligacionPago.class);

                    startActivity(intent);
                } else {
                    sociosSQLiteHelpe = new SociosSQLiteHelper(this);
                    socioArrayList = sociosSQLiteHelpe.listaSocio();

                    for (Socio auxiliar : socioArrayList) {
                        if (auxiliar.getEmail().equals(editTextEmailLogin.getText().toString()) && auxiliar.getPassword().equals(editTextPasswordLogin.getText().toString())) {
                            socio = auxiliar;
                        }
                    }

                    if (socio != null) {
                        intent = new Intent(Login.this, Cuotas.class);
                        intent.putExtra("socio", socio);
                        startActivity(intent);
                        Metodos.mensaje("Login Correcto", this);
                    } else {
                        Metodos.mensaje("Error en Email y/o Password", this);
                    }
                }
                break;
            default:
                break;
        }
    }
}
