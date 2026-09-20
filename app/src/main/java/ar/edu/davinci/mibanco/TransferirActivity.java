package ar.edu.davinci.mibanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransferirActivity extends AppCompatActivity {

    public static final String EXTRA_SALDO = "extra_saldo";
    public static final String EXTRA_DESTINO = "extra_destino";
    public static final String EXTRA_MONTO = "extra_monto";

    private EditText etDestino;
    private EditText etMonto;
    private LinearLayout contenedorContactos;

    private double saldoDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);

        etDestino = findViewById(R.id.etDestino);
        etMonto = findViewById(R.id.etMonto);
        contenedorContactos = findViewById(R.id.contenedorContactos);

        TextView tvSaldoDisponible = findViewById(R.id.tvSaldoDisponible);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnVolver = findViewById(R.id.btnVolver);

        saldoDisponible = getIntent().getDoubleExtra(EXTRA_SALDO, 0);
        tvSaldoDisponible.setText("Disponible: " + MainActivity.formatearPesos(saldoDisponible));

        // Contactos creados dinamicamente desde Java
        agregarContacto("Martina Rios", "martina.rios");
        agregarContacto("Juan Pablo Torres", "jp.torres");
        agregarContacto("Sofia Duarte", "sofi.duarte");

        btnVolver.setOnClickListener(v -> finish());
        btnConfirmar.setOnClickListener(v -> confirmar());
    }

    /**
     * Crea una fila de contacto por codigo (sin XML propio) y la suma al contenedor.
     */
    private void agregarContacto(String nombre, String alias) {
        TextView fila = new TextView(this);
        fila.setText(nombre + "  ·  " + alias);
        fila.setTextColor(getColor(R.color.navy));
        fila.setPadding(28, 28, 28, 28);
        fila.setBackgroundResource(R.drawable.bg_card_white);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 12);
        fila.setLayoutParams(params);

        fila.setOnClickListener(v -> etDestino.setText(alias));

        contenedorContactos.addView(fila);
    }

    private void confirmar() {
        String destino = etDestino.getText().toString().trim();
        String montoTexto = etMonto.getText().toString().trim();

        if (destino.isEmpty()) {
            Toast.makeText(this, R.string.msg_destinatario_vacio, Toast.LENGTH_SHORT).show();
            return;
        }

        if (montoTexto.isEmpty()) {
            Toast.makeText(this, R.string.msg_monto_invalido, Toast.LENGTH_SHORT).show();
            return;
        }

        double monto = Double.parseDouble(montoTexto);

        if (monto <= 0) {
            Toast.makeText(this, R.string.msg_monto_invalido, Toast.LENGTH_SHORT).show();
            return;
        }

        if (monto > saldoDisponible) {
            Toast.makeText(this, R.string.msg_saldo_insuficiente, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultado = new Intent();
        resultado.putExtra(EXTRA_DESTINO, destino);
        resultado.putExtra(EXTRA_MONTO, monto);
        setResult(RESULT_OK, resultado);
        finish();
    }
}