package ar.edu.davinci.mibanco;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovimientos;
    private Button btnTransferir;
    private Button btnAlias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a las vistas
        rvMovimientos = findViewById(R.id.rvMovimientos);
        btnTransferir = findViewById(R.id.btnTransferir);
        btnAlias = findViewById(R.id.btnAlias);

        // Configuración de la lista de movimientos
        rvMovimientos.setLayoutManager(new LinearLayoutManager(this));
        rvMovimientos.setAdapter(new MovimientoAdapter(crearMovimientos()));

        // Acciones de los botones
        btnTransferir.setOnClickListener(v ->
                Toast.makeText(this, R.string.accion_transferir, Toast.LENGTH_SHORT).show());

        btnAlias.setOnClickListener(v ->
                Toast.makeText(this, R.string.accion_alias, Toast.LENGTH_SHORT).show());
    }


    private List<Movimiento> crearMovimientos() {
        List<Movimiento> lista = new ArrayList<>();

        lista.add(new Movimiento("↓", "Transferencia recibida", "Hoy, 10:32", 25000, true));
        lista.add(new Movimiento("↑", "Pago de servicios", "Hoy, 09:15", 8450, false));
        lista.add(new Movimiento("↑", "Compra en supermercado", "Ayer, 19:40", 32100, false));
        lista.add(new Movimiento("↓", "Acreditación de sueldo", "Ayer, 08:00", 180000, true));
        lista.add(new Movimiento("↑", "Transferencia enviada", "12/09, 16:22", 15000, false));

        return lista;
    }
}