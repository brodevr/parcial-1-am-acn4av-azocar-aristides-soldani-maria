package ar.edu.davinci.mibanco;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Pantalla secundaria: lista completa de movimientos, scrolleable.
 */
public class MovimientosActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIMIENTOS = "extra_movimientos";

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        List<Movimiento> movimientos =
                (ArrayList<Movimiento>) getIntent().getSerializableExtra(EXTRA_MOVIMIENTOS);

        if (movimientos == null) {
            movimientos = new ArrayList<>();
        }

        RecyclerView rv = findViewById(R.id.rvTodosMovimientos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MovimientoAdapter(movimientos));

        ImageView btnVolver = findViewById(R.id.btnVolverMovimientos);
        btnVolver.setOnClickListener(v -> finish());
    }
}