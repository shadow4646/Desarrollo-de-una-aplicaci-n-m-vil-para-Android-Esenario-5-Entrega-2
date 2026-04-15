package com.example.portafolioapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * PerfilFragment — Muestra el perfil profesional completo.
 *
 * Contiene: foto, nombre, título, estudios, experiencia,
 * habilidades y datos de contacto. Los campos de texto
 * están dentro de un ScrollView para optimizar la pantalla.
 */
public class PerfilFragment extends Fragment {

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------
    private ImageView ivFotoPerfil;          // R.id.iv_foto_perfil
    private TextView  tvNombre;              // R.id.tv_nombre
    private TextView  tvTituloProfesional;   // R.id.tv_titulo_profesional
    private TextView  tvCiudad;              // R.id.tv_ciudad
    private TextView  tvEstudios;            // R.id.tv_estudios
    private TextView  tvExperiencia;         // R.id.tv_experiencia
    private TextView  tvHabilidades;         // R.id.tv_habilidades
    private TextView  tvEmail;               // R.id.tv_email
    private TextView  tvTelefono;            // R.id.tv_telefono

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        ivFotoPerfil        = view.findViewById(R.id.iv_foto_perfil);
        tvNombre            = view.findViewById(R.id.tv_nombre);
        tvTituloProfesional = view.findViewById(R.id.tv_titulo_profesional);
        tvCiudad            = view.findViewById(R.id.tv_ciudad);
        tvEstudios          = view.findViewById(R.id.tv_estudios);
        tvExperiencia       = view.findViewById(R.id.tv_experiencia);
        tvHabilidades       = view.findViewById(R.id.tv_habilidades);
        tvEmail             = view.findViewById(R.id.tv_email);
        tvTelefono          = view.findViewById(R.id.tv_telefono);

        // Inicializar datos del perfil
        inicializarPerfil();
    }

    // -----------------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------------

    /**
     * Carga los datos del perfil en las vistas.
     * Los textos estáticos vienen de strings.xml; aquí se puede
     * conectar con una fuente de datos dinámica si se requiere.
     */
    private void inicializarPerfil() {
        // La foto de perfil usa el drawable ic_persona como placeholder
        ivFotoPerfil.setImageResource(R.drawable.ic_persona);

        // Los TextViews ya tienen sus textos definidos en el XML mediante
        // android:text="@string/...", pero se pueden sobreescribir aquí:
        tvNombre.setText(R.string.perfil_nombre);
        tvTituloProfesional.setText(R.string.perfil_titulo);
        tvCiudad.setText(R.string.perfil_ciudad);
        tvEstudios.setText(R.string.perfil_estudios);
        tvExperiencia.setText(R.string.perfil_experiencia);
        tvHabilidades.setText(R.string.perfil_habilidades);
        tvEmail.setText(R.string.perfil_email);
        tvTelefono.setText(R.string.perfil_telefono);
    }
}
