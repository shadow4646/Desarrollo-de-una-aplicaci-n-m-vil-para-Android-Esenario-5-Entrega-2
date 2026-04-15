package com.example.portafolioapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * NavigacionFragment — Fragmento izquierdo de navegación.
 *
 * Contiene cinco botones que representan las secciones de la app.
 * Comunica la selección a la MainActivity mediante la interfaz
 * OnNavegacionListener (patrón de comunicación Fragment → Activity).
 */
public class NavigacionFragment extends Fragment implements View.OnClickListener {

    // -----------------------------------------------------------------------
    // Constantes de opciones
    // -----------------------------------------------------------------------
    public static final int OPCION_PERFIL   = 0;
    public static final int OPCION_FOTOS    = 1;
    public static final int OPCION_VIDEO    = 2;
    public static final int OPCION_WEB      = 3;
    public static final int OPCION_BOTONES  = 4;

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------
    private Button btnPerfil;     // R.id.btn_nav_perfil
    private Button btnFotos;      // R.id.btn_nav_fotos
    private Button btnVideo;      // R.id.btn_nav_video
    private Button btnWeb;        // R.id.btn_nav_web
    private Button btnBotones;    // R.id.btn_nav_botones

    private Button btnActivo;     // Referencia al botón actualmente seleccionado

    // Listener para comunicar la selección a la Activity
    private OnNavegacionListener listener;

    // -----------------------------------------------------------------------
    // Interfaz de comunicación con la Activity
    // -----------------------------------------------------------------------

    /**
     * Interfaz que debe implementar la Activity que contenga este fragmento.
     * Permite desacoplar el fragmento de la Activity concreta.
     */
    public interface OnNavegacionListener {
        void onOpcionSeleccionada(int opcion);
    }

    // -----------------------------------------------------------------------
    // Ciclo de vida del fragmento
    // -----------------------------------------------------------------------

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verificar que la Activity implementa la interfaz
        if (context instanceof OnNavegacionListener) {
            listener = (OnNavegacionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debe implementar OnNavegacionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_navegacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        btnPerfil  = view.findViewById(R.id.btn_nav_perfil);
        btnFotos   = view.findViewById(R.id.btn_nav_fotos);
        btnVideo   = view.findViewById(R.id.btn_nav_video);
        btnWeb     = view.findViewById(R.id.btn_nav_web);
        btnBotones = view.findViewById(R.id.btn_nav_botones);

        // Registrar eventos de clic
        btnPerfil.setOnClickListener(this);
        btnFotos.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnBotones.setOnClickListener(this);

        // Resaltar Perfil como opción activa por defecto
        marcarBotonActivo(btnPerfil);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;  // Evitar fugas de memoria
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos (View.OnClickListener)
    // -----------------------------------------------------------------------

    /**
     * Maneja los clics en los botones de navegación.
     * Actualiza el estilo visual del botón activo y notifica al listener.
     *
     * @param view  Vista que disparó el evento.
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_nav_perfil) {
            marcarBotonActivo(btnPerfil);
            listener.onOpcionSeleccionada(OPCION_PERFIL);

        } else if (id == R.id.btn_nav_fotos) {
            marcarBotonActivo(btnFotos);
            listener.onOpcionSeleccionada(OPCION_FOTOS);

        } else if (id == R.id.btn_nav_video) {
            marcarBotonActivo(btnVideo);
            listener.onOpcionSeleccionada(OPCION_VIDEO);

        } else if (id == R.id.btn_nav_web) {
            marcarBotonActivo(btnWeb);
            listener.onOpcionSeleccionada(OPCION_WEB);

        } else if (id == R.id.btn_nav_botones) {
            marcarBotonActivo(btnBotones);
            listener.onOpcionSeleccionada(OPCION_BOTONES);
        }
    }

    // -----------------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------------

    /**
     * Resalta visualmente el botón seleccionado y restaura el anterior.
     *
     * @param boton  Botón que debe quedar activo.
     */
    private void marcarBotonActivo(Button boton) {
        // Restaurar color del botón anterior
        if (btnActivo != null) {
            btnActivo.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                            android.graphics.Color.TRANSPARENT));
        }
        // Aplicar color al botón activo
        boton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        getResources().getColor(R.color.colorNavSelected, null)));
        btnActivo = boton;
    }
}
