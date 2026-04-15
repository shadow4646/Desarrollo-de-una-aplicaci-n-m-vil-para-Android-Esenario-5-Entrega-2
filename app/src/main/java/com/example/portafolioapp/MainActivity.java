package com.example.portafolioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

/**
 * MainActivity — Actividad principal de PortafolioApp.
 *
 * Gestiona dos contenedores de fragmentos distribuidos horizontalmente:
 *   - contenedor_navegacion (izquierdo, 30%): NavigationFragment con el menú de opciones.
 *   - contenedor_contenido  (derecho,   70%): Fragmento de contenido dinámico.
 *
 * Implementa la interfaz OnNavegacionListener para recibir eventos
 * del fragmento de navegación y cargar el fragmento correspondiente.
 */
public class MainActivity extends AppCompatActivity
        implements NavigacionFragment.OnNavegacionListener {

    // -----------------------------------------------------------------------
    // Variables / Identificadores de vistas
    // -----------------------------------------------------------------------
    private FragmentManager fragmentManager;   // Gestor de fragmentos
    private int ultimaOpcion = -1;             // Última opción seleccionada

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular el gestor de fragmentos
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Cargar fragmento de navegación (izquierdo) — persiste siempre
            cargarFragmentoNavegacion();
            // Mostrar Perfil por defecto al iniciar
            cargarFragmentoContenido(new PerfilFragment(), 0);
        }
    }

    // -----------------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------------

    /**
     * Carga el fragmento de navegación en el contenedor izquierdo.
     * Solo se hace una vez al iniciar la app.
     */
    private void cargarFragmentoNavegacion() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor_navegacion, new NavigacionFragment());
        transaction.commit();
    }

    /**
     * Carga un fragmento de contenido en el contenedor derecho.
     * Evita recargar si ya está activo el mismo fragmento.
     *
     * @param fragmento  Instancia del fragmento a mostrar.
     * @param opcionId   Identificador de la opción (0=Perfil, 1=Fotos, etc.).
     */
    private void cargarFragmentoContenido(Fragment fragmento, int opcionId) {
        if (ultimaOpcion == opcionId) return;  // No recargar si ya está activo
        ultimaOpcion = opcionId;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );
        transaction.replace(R.id.contenedor_contenido, fragmento);
        transaction.commit();
    }

    // -----------------------------------------------------------------------
    // Implementación de interfaz OnNavegacionListener
    // -----------------------------------------------------------------------

    /**
     * Callback invocado cuando el usuario selecciona una opción
     * en el fragmento de navegación (izquierdo).
     *
     * @param opcion  Constante que identifica la sección seleccionada.
     */
    @Override
    public void onOpcionSeleccionada(int opcion) {
        switch (opcion) {
            case NavigacionFragment.OPCION_PERFIL:
                cargarFragmentoContenido(new PerfilFragment(), opcion);
                break;
            case NavigacionFragment.OPCION_FOTOS:
                cargarFragmentoContenido(new FotosFragment(), opcion);
                break;
            case NavigacionFragment.OPCION_VIDEO:
                cargarFragmentoContenido(new VideoFragment(), opcion);
                break;
            case NavigacionFragment.OPCION_WEB:
                cargarFragmentoContenido(new WebFragment(), opcion);
                break;
            case NavigacionFragment.OPCION_BOTONES:
                cargarFragmentoContenido(new BotonesFragment(), opcion);
                break;
        }
    }
}
