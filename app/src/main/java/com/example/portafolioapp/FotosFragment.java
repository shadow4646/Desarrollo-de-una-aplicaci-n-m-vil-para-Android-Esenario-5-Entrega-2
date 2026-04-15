package com.example.portafolioapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * FotosFragment — Galería de proyectos con barra de desplazamiento.
 *
 * Muestra una lista scrollable de imágenes representativas de proyectos.
 * Al seleccionar una imagen, despliega su descripción en el panel inferior.
 */
public class FotosFragment extends Fragment implements FotosAdapter.OnFotoClickListener {

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------
    private RecyclerView rvFotos;             // R.id.rv_fotos
    private TextView     tvDescripcionFoto;   // R.id.tv_descripcion_foto

    private FotosAdapter       adapter;       // Adaptador del RecyclerView
    private List<ModeloFoto>   listaFotos;    // Fuente de datos

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fotos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        rvFotos           = view.findViewById(R.id.rv_fotos);
        tvDescripcionFoto = view.findViewById(R.id.tv_descripcion_foto);

        // Inicializar datos y configurar RecyclerView
        cargarDatos();
        configurarRecyclerView();
    }

    // -----------------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------------

    /**
     * Carga la lista de proyectos/fotos de muestra.
     * En producción estos datos vendrían de una API o base de datos.
     */
    private void cargarDatos() {
        listaFotos = new ArrayList<>();
        listaFotos.add(new ModeloFoto(
                "App de Gestión de Inventario",
                "Android — Java",
                "Aplicación Android para control de inventario en tiempo real. " +
                "Integra Firebase Firestore para sincronización en la nube y " +
                "lectura de códigos de barras mediante CameraX.",
                R.drawable.ic_imagen));

        listaFotos.add(new ModeloFoto(
                "Portal Web Corporativo",
                "Web — React / Node.js",
                "Desarrollo del portal web de una empresa de logística. " +
                "Frontend en React con TypeScript, backend REST en Node.js " +
                "y base de datos PostgreSQL.",
                R.drawable.ic_imagen));

        listaFotos.add(new ModeloFoto(
                "Sistema de Reservas",
                "Android — Kotlin",
                "App de reservas para restaurantes con calendario interactivo, " +
                "notificaciones push y pasarela de pago integrada con PSE.",
                R.drawable.ic_imagen));

        listaFotos.add(new ModeloFoto(
                "Dashboard de Analítica",
                "Web — Vue.js",
                "Panel de control con gráficas interactivas para visualizar " +
                "métricas de ventas, uso de app y KPIs del negocio en tiempo real.",
                R.drawable.ic_imagen));

        listaFotos.add(new ModeloFoto(
                "API REST de Usuarios",
                "Backend — Spring Boot",
                "Microservicio REST para gestión de usuarios con autenticación " +
                "JWT, roles y permisos, documentación Swagger y despliegue en Docker.",
                R.drawable.ic_imagen));
    }

    /**
     * Configura el RecyclerView con GridLayoutManager de 2 columnas y su adaptador.
     */
    private void configurarRecyclerView() {
        // Grid de 2 columnas con scroll vertical (según diseño del documento)
        rvFotos.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // Crear y asignar el adaptador
        adapter = new FotosAdapter(listaFotos, this);
        rvFotos.setAdapter(adapter);
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos
    // -----------------------------------------------------------------------

    /**
     * Callback invocado cuando el usuario toca un elemento de la lista.
     * Muestra la descripción completa de la foto seleccionada.
     *
     * @param foto  Modelo de la foto seleccionada.
     */
    @Override
    public void onFotoClick(ModeloFoto foto) {
        tvDescripcionFoto.setText(foto.getDescripcion());
    }
}
