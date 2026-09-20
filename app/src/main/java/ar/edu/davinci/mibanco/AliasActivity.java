package ar.edu.davinci.mibanco;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AliasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alias);

        TextView tvAlias = findViewById(R.id.tvAlias);
        TextView tvCbu = findViewById(R.id.tvCbu);
        Button btnCopiarAlias = findViewById(R.id.btnCopiarAlias);
        Button btnCopiarCbu = findViewById(R.id.btnCopiarCbu);
        Button btnVolver = findViewById(R.id.btnVolverAlias);

        btnVolver.setOnClickListener(v -> finish());

        btnCopiarAlias.setOnClickListener(v ->
                copiar("alias", tvAlias.getText().toString(), R.string.msg_alias_copiado));

        btnCopiarCbu.setOnClickListener(v ->
                copiar("cbu", tvCbu.getText().toString(), R.string.msg_cbu_copiado));
    }

    private void copiar(String etiqueta, String valor, int mensaje) {
        ClipboardManager portapapeles =
                (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        portapapeles.setPrimaryClip(ClipData.newPlainText(etiqueta, valor));
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}