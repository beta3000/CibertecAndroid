package com.google.cibertecandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

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

        switch (view.getId()) {
            case R.id.buttonRegistrar:
                intent = new Intent(this, RegistroDeSocio.class);
                startActivity(intent);
                break;
            case R.id.buttonIngresar:

                break;
            default:
                break;
        }
    }
}
