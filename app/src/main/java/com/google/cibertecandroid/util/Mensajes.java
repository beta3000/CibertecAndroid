package com.google.cibertecandroid.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by RUBITO on 24/06/2016.
 */
public class Mensajes {

    public static void mensaje(String mensaje, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Mensaje");
        alert.setMessage(mensaje);
        alert.show();
    }
}
