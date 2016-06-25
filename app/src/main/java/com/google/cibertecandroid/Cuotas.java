package com.google.cibertecandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.cibertecandroid.bean.Cuota;
import com.google.cibertecandroid.bean.Socio;
import com.google.cibertecandroid.dao.SociosSQLiteHelper;

import java.util.ArrayList;

public class Cuotas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuotas);

        Intent intent = getIntent();
        Socio socio = (Socio) intent.getExtras().getSerializable("socio");

        SociosSQLiteHelper sociosSQLiteHelper = new SociosSQLiteHelper(this);

        ArrayList<Cuota> data = new ArrayList<Cuota>();
        data = sociosSQLiteHelper.listaCuotaSocio(socio.getIdSocio());

        CuotaAdaptador adaptador = new CuotaAdaptador(this, data);

        ListView lista = (ListView) findViewById(R.id.listViewCuotas);
        lista.setAdapter(adaptador);

    }
}
