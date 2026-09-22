package ar.edu.davinci.mibanco;

import java.io.Serializable;

/**
 * Modelo de un movimiento de la cuenta.
 * Implementa Serializable para poder viajar en un Intent hacia MovimientosActivity.
 */
public class Movimiento implements Serializable {

    private final int avatar;          // R.drawable.avatar_*
    private final String titulo;
    private final String fecha;
    private final double monto;
    private final boolean esIngreso;

    public Movimiento(int avatar, String titulo, String fecha,
                      double monto, boolean esIngreso) {
        this.avatar = avatar;
        this.titulo = titulo;
        this.fecha = fecha;
        this.monto = monto;
        this.esIngreso = esIngreso;
    }

    public int getAvatar() { return avatar; }
    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
    public double getMonto() { return monto; }
    public boolean esIngreso() { return esIngreso; }
}