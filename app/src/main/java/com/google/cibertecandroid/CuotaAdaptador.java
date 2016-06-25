package com.google.cibertecandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.cibertecandroid.bean.Cuota;

import java.util.ArrayList;

/**
 * Created by RUBITO on 25/06/2016.
 */
public class CuotaAdaptador extends BaseAdapter {

    private ArrayList<Cuota> data = null;
    private Activity a = null;

    public CuotaAdaptador(Activity a, ArrayList<Cuota> data) {
        this.a = a;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(String.valueOf(data.get(position).getIdCuota()));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vista = view;

        if (vista == null) {
            LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Se menciona que la vista es lo que se diseño en item
            vista = inflater.inflate(R.layout.item, null);
        }

        TextView textViewNumeroCuota = (TextView) vista.findViewById(R.id.textViewNumeroCuota);
        TextView textViewMontoCuota = (TextView) vista.findViewById(R.id.textViewMontoCuota);
        TextView textViewFechaVencimiento = (TextView) vista.findViewById(R.id.textViewFechaVencimiento);
        TextView textViewEstadoCuota = (TextView) vista.findViewById(R.id.textViewEstadoCuota);

        Cuota c = data.get(position);

        textViewNumeroCuota.setText(String.valueOf(c.getIdCuota()));
        textViewMontoCuota.setText(String.valueOf(c.getMontoCuota()));
        textViewFechaVencimiento.setText(c.getFechaPagoCuota());
        textViewEstadoCuota.setText(c.getEstadoCuota());
        return vista;
    }
}
