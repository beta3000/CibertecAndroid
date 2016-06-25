package com.google.cibertecandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.cibertecandroid.bean.Cuota;
import com.google.cibertecandroid.bean.Socio;
import com.google.cibertecandroid.dao.SociosSQLiteHelper;
import com.google.cibertecandroid.util.Metodos;

import java.util.ArrayList;

public class ObligacionPago extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerSocio;
    EditText editTextMonto;
    EditText editTextFechaInicioCobro;
    EditText editTextTasa;
    EditText editTextCuotas;
    Button buttonRegistrarObligacionPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obligacion_pago);

        ArrayList<Socio> socioArrayList = new ArrayList<Socio>();
        SociosSQLiteHelper sociosSQLiteHelper = new SociosSQLiteHelper(this);
        socioArrayList = sociosSQLiteHelper.listaSocio();

        ArrayList<String> nombresSocioArrayList = new ArrayList<>();


        for (Socio socio : socioArrayList) {
            nombresSocioArrayList.add(socio.getIdSocio() + "-" + socio.getNombres());
        }

        spinnerSocio = (Spinner) findViewById(R.id.spinnerSocio);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, nombresSocioArrayList);
        spinnerSocio.setAdapter(spinnerArrayAdapter);

        buttonRegistrarObligacionPago = (Button) findViewById(R.id.buttonRegistrarObligacionPago);
        buttonRegistrarObligacionPago.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        ArrayList<Socio> socioArrayList = new ArrayList<>();
        SociosSQLiteHelper sociosSQLiteHelper = new SociosSQLiteHelper(this);
        com.google.cibertecandroid.bean.ObligacionPago obligacionPago = null;
        Cuota cuota = null;
        switch (view.getId()) {
            case R.id.buttonRegistrarObligacionPago:
                editTextMonto = (EditText) findViewById(R.id.editTextMonto);
                editTextFechaInicioCobro = (EditText) findViewById(R.id.editTextFechaInicioCobro);
                editTextTasa = (EditText) findViewById(R.id.editTextTasa);
                editTextCuotas = (EditText) findViewById(R.id.editTextCuotas);

                int idSocio = Integer.parseInt(spinnerSocio.getSelectedItem().toString().substring(0, spinnerSocio.getSelectedItem().toString().indexOf("-")));
                Double montoTotal = Double.parseDouble(editTextMonto.getText().toString());
                String fechaRegistro = editTextFechaInicioCobro.getText().toString();
                Double tasa = Double.parseDouble(editTextTasa.getText().toString()) / 100.0;
                Integer numeroCuotas = Integer.parseInt(editTextCuotas.getText().toString());

                obligacionPago = new com.google.cibertecandroid.bean.ObligacionPago();
                obligacionPago.setIdSocio(idSocio);
                obligacionPago.setMontoObligacion(montoTotal);
                obligacionPago.setFechaRegistro(fechaRegistro);
                obligacionPago.setTasa(tasa);
                obligacionPago.setNumeroCuotas(numeroCuotas);

                sociosSQLiteHelper.insertaObligacionPago(obligacionPago);

                String primeraFecha = fechaRegistro;

                for (int i = 0; i < Integer.parseInt(editTextCuotas.getText().toString()); i++) {
                    cuota = new Cuota();

                    cuota.setFechaPagoCuota(primeraFecha);
                    cuota.setIdSocio(idSocio);
                    cuota.setFechaPagoCuota(primeraFecha);
                    primeraFecha = Metodos.subeUnMes(primeraFecha);
                    cuota.setIdObligacion(1);
                    cuota.setMontoCuota(Metodos.redondear(montoTotal / numeroCuotas * (1 + tasa), 4));
                    cuota.setEstadoCuota("Pendiente");

                    sociosSQLiteHelper.insertaCuota(cuota);
                }

                Metodos.mensaje("Obligación Creada Correctamente", this);
                break;
            default:
                break;
        }
    }
}
