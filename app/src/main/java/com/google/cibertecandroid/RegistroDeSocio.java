package com.google.cibertecandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.cibertecandroid.bean.Socio;
import com.google.cibertecandroid.dao.SociosSQLiteHelper;
import com.google.cibertecandroid.util.Metodos;

public class RegistroDeSocio extends AppCompatActivity implements View.OnClickListener {
    Button buttonRegistrarSocio;
    EditText editTextNombres;
    EditText editTextApellidos;
    EditText editTextFechaNacimiento;
    Spinner spinnerSexo;
    EditText editTextEmail;
    EditText editTextDNI;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_de_socio);

        spinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        buttonRegistrarSocio = (Button) findViewById(R.id.buttonRegistrarSocio);
        buttonRegistrarSocio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Socio socio = null;

        switch (view.getId()) {
            case R.id.buttonRegistrarSocio:
                editTextNombres = (EditText) findViewById(R.id.editTextNombres);
                editTextApellidos = (EditText) findViewById(R.id.editTextApellidos);
                editTextFechaNacimiento = (EditText) findViewById(R.id.editTextFechaNacimiento);
                editTextEmail = (EditText) findViewById(R.id.editTextEmail);
                editTextDNI = (EditText) findViewById(R.id.editTextDNI);
                editTextPassword = (EditText) findViewById(R.id.editTextPassword);

                socio = new Socio();
                socio.setNombres(editTextNombres.getText().toString());
                socio.setApellidos(editTextApellidos.getText().toString());
                socio.setFechaNacimiento(editTextFechaNacimiento.getText().toString());
                socio.setEmail(editTextEmail.getText().toString());
                socio.setSexo(spinnerSexo.getSelectedItem().toString());
                socio.setDNI(Integer.parseInt(editTextDNI.getText().toString()));
                socio.setPassword(editTextPassword.getText().toString());

                SociosSQLiteHelper sociosSQLiteHelper = new SociosSQLiteHelper(this);
                sociosSQLiteHelper.insertaSocio(socio);
                Metodos.mensaje("Socio Registrado Correctamente", this);

                intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
