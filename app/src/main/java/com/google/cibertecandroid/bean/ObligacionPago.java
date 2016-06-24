package com.google.cibertecandroid.bean;

/**
 * Created by RUBITO on 24/06/2016.
 */
public class ObligacionPago {
    private int idObligacionPago;
    private int idSocio;
    private double montoObligacion;
    private String fechaRegistro;
    private double tasa;
    private int numeroCuotas;

    public ObligacionPago() {
    }

    public int getIdObligacionPago() {
        return idObligacionPago;
    }

    public void setIdObligacionPago(int idObligacionPago) {
        this.idObligacionPago = idObligacionPago;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public double getMontoObligacion() {
        return montoObligacion;
    }

    public void setMontoObligacion(double montoObligacion) {
        this.montoObligacion = montoObligacion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }
}
