package ar.edu.davinci.mibanco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MovimientoAdapter
        extends RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder> {

    private final List<Movimiento> movimientos;

    public MovimientoAdapter(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {
        holder.bind(movimientos.get(position));
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

    /**
     * Inserta un movimiento al principio de la lista y avisa al RecyclerView.
     */
    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(0, movimiento);
        notifyItemInserted(0);
    }

    static class MovimientoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final ImageView ivBadge;
        private final TextView tvTitulo;
        private final TextView tvFecha;
        private final TextView tvMonto;

        MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            ivBadge = itemView.findViewById(R.id.ivBadge);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvMonto = itemView.findViewById(R.id.tvMonto);
        }

        void bind(Movimiento movimiento) {
            boolean ingreso = movimiento.esIngreso();

            ivAvatar.setImageResource(movimiento.getAvatar());
            ivBadge.setImageResource(
                    ingreso ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_up);
            ivBadge.setBackgroundResource(
                    ingreso ? R.drawable.bg_circle_teal : R.drawable.bg_circle_navy);

            tvTitulo.setText(movimiento.getTitulo());
            tvFecha.setText(movimiento.getFecha());
            tvMonto.setText(formatearMonto(movimiento));
            tvMonto.setTextColor(ContextCompat.getColor(
                    itemView.getContext(),
                    ingreso ? R.color.teal : R.color.navy));
        }
        // formatearMonto() queda igual
    }

        private static String formatearMonto(Movimiento movimiento) {
            NumberFormat formato = NumberFormat.getNumberInstance(new Locale("es", "AR"));
            formato.setMaximumFractionDigits(0);

            String signo = movimiento.esIngreso() ? "+" : "-";
            return signo + "$" + formato.format(Math.abs(movimiento.getMonto()));
        }
    }

