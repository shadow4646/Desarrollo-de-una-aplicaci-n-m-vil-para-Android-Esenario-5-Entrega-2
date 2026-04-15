package com.example.portafolioapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * FotosAdapter — Adaptador RecyclerView para la galería de proyectos.
 *
 * Vincula cada ModeloFoto con el layout item_foto.xml.
 * Notifica al FotosFragment cuando el usuario selecciona un elemento
 * mediante la interfaz OnFotoClickListener.
 */
public class FotosAdapter extends RecyclerView.Adapter<FotosAdapter.FotoViewHolder> {

    // -----------------------------------------------------------------------
    // Interfaz de eventos
    // -----------------------------------------------------------------------

    public interface OnFotoClickListener {
        void onFotoClick(ModeloFoto foto);
    }

    // -----------------------------------------------------------------------
    // Variables
    // -----------------------------------------------------------------------
    private final List<ModeloFoto>    listaFotos;   // Datos del adaptador
    private final OnFotoClickListener listener;      // Callback de clic

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public FotosAdapter(List<ModeloFoto> listaFotos, OnFotoClickListener listener) {
        this.listaFotos = listaFotos;
        this.listener   = listener;
    }

    // -----------------------------------------------------------------------
    // ViewHolder — Vínculos con vistas del item
    // -----------------------------------------------------------------------

    public static class FotoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFotoItem;     // R.id.iv_foto_item
        TextView  tvFotoTitulo;   // R.id.tv_foto_titulo
        TextView  tvFotoCategoria;// R.id.tv_foto_categoria

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFotoItem      = itemView.findViewById(R.id.iv_foto_item);
            tvFotoTitulo    = itemView.findViewById(R.id.tv_foto_titulo);
            tvFotoCategoria = itemView.findViewById(R.id.tv_foto_categoria);
        }
    }

    // -----------------------------------------------------------------------
    // Métodos del adaptador
    // -----------------------------------------------------------------------

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto, parent, false);
        return new FotoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        ModeloFoto foto = listaFotos.get(position);

        // Vincular datos con vistas
        holder.ivFotoItem.setImageResource(foto.getImagenResId());
        holder.tvFotoTitulo.setText(foto.getTitulo());
        holder.tvFotoCategoria.setText(foto.getCategoria());

        // Registrar evento de clic sobre el item
        holder.itemView.setOnClickListener(v -> listener.onFotoClick(foto));
    }

    @Override
    public int getItemCount() {
        return listaFotos.size();
    }
}
