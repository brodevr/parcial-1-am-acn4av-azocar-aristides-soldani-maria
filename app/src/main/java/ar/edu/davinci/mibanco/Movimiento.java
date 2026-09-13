package ar.edu.davinci.mibanco;

public class Movimiento {
    private String glyph;      // "↓" o "↑"
    private String titulo;     // "Transferencia recibida"
    private String fecha;      // "Hoy, 10:32"
    private double monto;      // 25000.0
    private boolean esIngreso;

    public Movimiento(String glyph, String titulo, String fecha,
                      double monto, boolean esIngreso) {
        this.glyph = glyph;
        this.titulo = titulo;
        this.fecha = fecha;
        this.monto = monto;
        this.esIngreso = esIngreso;
    }

    public String getGlyph() { return glyph; }
    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
    public double getMonto() { return monto; }
    public boolean esIngreso() { return esIngreso; }
}