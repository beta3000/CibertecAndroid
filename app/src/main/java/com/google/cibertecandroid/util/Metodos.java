package com.google.cibertecandroid.util;

import android.app.AlertDialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RUBITO on 24/06/2016.
 */
public class Metodos {

    public static void mensaje(String mensaje, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Mensaje");
        alert.setMessage(mensaje);
        alert.show();
    }

    public static String subeUnMes(String fecha) {
        String salida = "-1";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(fecha);

            long longDate = date.getTime();
            longDate = longDate + (long) 30 * 24 * 60 * 60 * 1000;

            Date dateSalida = new Date(longDate);

            salida = sdf.format(dateSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;
    }

    public static double redondear(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
