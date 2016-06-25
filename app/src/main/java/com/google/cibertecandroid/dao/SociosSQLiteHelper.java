package com.google.cibertecandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.cibertecandroid.bean.Cuota;
import com.google.cibertecandroid.bean.ObligacionPago;
import com.google.cibertecandroid.bean.Socio;

import java.util.ArrayList;

/**
 * Created by RUBITO on 23/06/2016.
 */
public class SociosSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB = "socios";
    private static final int VERSION = 1;
    private String TABLE_SOCIO = "create table socio (idSocio integer primary key autoincrement, nombres text, apellidos text, fechaNacimiento text, sexo text, email text, DNI integer, password text)";
    private String TABLE_OBLIGACION_PAGO = "create table obligacionPago (idObligacionPago integer primary key autoincrement, idSocio integer, montoObligacion double, fechaRegistro text, tasa double, numeroCuotas integer)";
    private String TABLE_CUOTA = "create table cuota (idCuota integer primary key autoincrement,idSocio integer, idObligacion integer, montoCuota double, fechaPagoCuota text, estadoCuota text)";

    public SociosSQLiteHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    //Se ejecuta al inicio para crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_SOCIO);
        sqLiteDatabase.execSQL(TABLE_OBLIGACION_PAGO);
        sqLiteDatabase.execSQL(TABLE_CUOTA);
    }

    //Se ejecuta cuando se quiere actualizar la DB a una nueva versión
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*SOCIO*/
    public void insertaSocio(Socio socio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombres", socio.getNombres());
        contentValues.put("apellidos", socio.getApellidos());
        contentValues.put("fechaNacimiento", socio.getFechaNacimiento());
        contentValues.put("sexo", socio.getSexo());
        contentValues.put("email", socio.getEmail());
        contentValues.put("DNI", socio.getDNI());
        contentValues.put("password", socio.getPassword());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("socio", null, contentValues);
        sqLiteDatabase.close();
    }

    public Socio buscaSocio(int idSocio) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "socio";
        String[] columnas = {"idSocio", "nombres", "apellidos", "fechaNacimiento", "sexo", "email", "DNI", "password"};
        String where = "id = ?";
        String[] valuesWhere = {String.valueOf(idSocio)};
        Cursor cursor = sqLiteDatabase.query(tabla, columnas, where, valuesWhere, null, null, null);

        Socio socio = null;
        if (cursor.moveToNext()) {
            socio = new Socio();
            socio.setIdSocio(cursor.getInt(0));
            socio.setNombres(cursor.getString(1));
            socio.setApellidos(cursor.getString(2));
            socio.setFechaNacimiento(cursor.getString(3));
            socio.setSexo(cursor.getString(4));
            socio.setEmail(cursor.getString(5));
            socio.setDNI(cursor.getInt(6));
            socio.setPassword(cursor.getString(7));
        }
        sqLiteDatabase.close();
        return socio;
    }

    public ArrayList<Socio> listaSocio() {
        ArrayList<Socio> socioArrayList = new ArrayList<>();
        Socio socio = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "socio";
        String[] columnas = {"idSocio", "nombres", "apellidos", "fechaNacimiento", "sexo", "email", "DNI", "password"};
        Cursor cursor = sqLiteDatabase.query(tabla, columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                socio = new Socio();
                socio.setIdSocio(cursor.getInt(0));
                socio.setNombres(cursor.getString(1));
                socio.setApellidos(cursor.getString(2));
                socio.setFechaNacimiento(cursor.getString(3));
                socio.setSexo(cursor.getString(4));
                socio.setEmail(cursor.getString(5));
                socio.setDNI(cursor.getInt(6));
                socio.setPassword(cursor.getString(7));
                socioArrayList.add(socio);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return socioArrayList;
    }

    /*OBLIGACION DE PAGO*/
    public void insertaObligacionPago(ObligacionPago obligacionPago) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("idSocio", obligacionPago.getIdSocio());
        contentValues.put("montoObligacion", obligacionPago.getMontoObligacion());
        contentValues.put("fechaRegistro", obligacionPago.getFechaRegistro());
        contentValues.put("tasa", obligacionPago.getTasa());
        contentValues.put("numeroCuotas", obligacionPago.getNumeroCuotas());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("obligacionPago", null, contentValues);
        sqLiteDatabase.close();
    }

    /*CUOTA*/
    public void insertaCuota(Cuota cuota) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("idSocio", cuota.getIdSocio());
        contentValues.put("idObligacion", cuota.getIdObligacion());
        contentValues.put("montoCuota", cuota.getMontoCuota());
        contentValues.put("fechaPagoCuota", cuota.getFechaPagoCuota());
        contentValues.put("estadoCuota", cuota.getEstadoCuota());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("cuota", null, contentValues);
        sqLiteDatabase.close();
    }

    public void actulizaCuota(Cuota cuota) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "cuota";

        ContentValues contentValues = new ContentValues();
        contentValues.put("idSocio", cuota.getIdSocio());
        contentValues.put("idObligacion", cuota.getIdObligacion());
        contentValues.put("montoCuota", cuota.getMontoCuota());
        contentValues.put("fechaPagoCuota", cuota.getFechaPagoCuota());
        contentValues.put("estadoCuota", cuota.getEstadoCuota());

        sqLiteDatabase.update(tabla, contentValues, "idCuota=" + cuota.getIdCuota(), null);
        sqLiteDatabase.close();
    }

    public Cuota buscaCuota(int idCuota) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "cuota";
        String[] columnas = {"idCuota", "idSocio", "idObligacion", "montoCuota", "fechaPagoCuota", "estadoCuota"};
        String where = "id = ?";
        String[] valuesWhere = {String.valueOf(idCuota)};
        Cursor cursor = sqLiteDatabase.query(tabla, columnas, where, valuesWhere, null, null, null);

        Cuota cuota = null;
        if (cursor.moveToNext()) {
            cuota = new Cuota();
            cuota.setIdCuota(cursor.getInt(0));
            cuota.setIdSocio(cursor.getInt(1));
            cuota.setIdObligacion(cursor.getInt(2));
            cuota.setMontoCuota(cursor.getDouble(3));
            cuota.setFechaPagoCuota(cursor.getString(4));
            cuota.setEstadoCuota(cursor.getString(5));
        }
        sqLiteDatabase.close();
        return cuota;
    }

    public ArrayList<Cuota> listaCuota() {
        ArrayList<Cuota> cuotaArrayList = new ArrayList<>();
        Cuota cuota = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "cuota";
        String[] columnas = {"idCuota", "idSocio", "idObligacion", "montoCuota", "fechaPagoCuota", "estadoCuota"};
        Cursor cursor = sqLiteDatabase.query(tabla, columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                cuota = new Cuota();
                cuota.setIdCuota(cursor.getInt(0));
                cuota.setIdSocio(cursor.getInt(1));
                cuota.setIdObligacion(cursor.getInt(2));
                cuota.setMontoCuota(cursor.getDouble(3));
                cuota.setFechaPagoCuota(cursor.getString(4));
                cuota.setEstadoCuota(cursor.getString(5));
                cuotaArrayList.add(cuota);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return cuotaArrayList;
    }

    public ArrayList<Cuota> listaCuotaSocio(int idSocio) {
        ArrayList<Cuota> cuotaArrayList = new ArrayList<>();
        Cuota cuota = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String tabla = "cuota";
        String[] columnas = {"idCuota", "idSocio", "idObligacion", "montoCuota", "fechaPagoCuota", "estadoCuota"};

        String where = "idSocio = ?";
        String[] valuesWhere = {String.valueOf(idSocio)};
        Cursor cursor = sqLiteDatabase.query(tabla, columnas, where, valuesWhere, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                cuota = new Cuota();
                cuota.setIdCuota(cursor.getInt(0));
                cuota.setIdSocio(cursor.getInt(1));
                cuota.setIdObligacion(cursor.getInt(2));
                cuota.setMontoCuota(cursor.getDouble(3));
                cuota.setFechaPagoCuota(cursor.getString(4));
                cuota.setEstadoCuota(cursor.getString(5));
                cuotaArrayList.add(cuota);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return cuotaArrayList;
    }
}
