package com.example.portafolioapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * VideoFragment — Reproductor de video con controles personalizados.
 *
 * Incluye VideoView nativo de Android con MediaController incorporado,
 * más botones personalizados de Play/Pausa, Detener y Reiniciar.
 * El video se carga desde los recursos raw de la app.
 */
public class VideoFragment extends Fragment implements View.OnClickListener {

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------
    private VideoView vvReproductor;   // R.id.vv_reproductor
    private Button    btnPlayPause;    // R.id.btn_play_pause
    private Button    btnDetener;      // R.id.btn_detener
    private Button    btnReiniciar;    // R.id.btn_reiniciar
    private TextView  tvEstadoVideo;   // R.id.tv_estado_video

    private boolean estaReproduciendo = false;  // Estado actual del reproductor

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        vvReproductor = view.findViewById(R.id.vv_reproductor);
        btnPlayPause  = view.findViewById(R.id.btn_play_pause);
        btnDetener    = view.findViewById(R.id.btn_detener);
        btnReiniciar  = view.findViewById(R.id.btn_reiniciar);
        tvEstadoVideo = view.findViewById(R.id.tv_estado_video);

        // Registrar eventos de clic
        btnPlayPause.setOnClickListener(this);
        btnDetener.setOnClickListener(this);
        btnReiniciar.setOnClickListener(this);

        // Configurar reproductor
        configurarReproductor();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Pausar reproducción al salir del fragmento
        if (vvReproductor.isPlaying()) {
            vvReproductor.pause();
            actualizarEstado("Pausado");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        vvReproductor.stopPlayback();
    }

    // -----------------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------------

    /**
     * Configura el VideoView con la URI del video y añade
     * el MediaController nativo de Android como overlay de controles.
     */
    private void configurarReproductor() {
        // Añadir controles nativos de Android sobre el VideoView
        MediaController controles = new MediaController(requireContext());
        controles.setAnchorView(vvReproductor);
        vvReproductor.setMediaController(controles);

        // Usar un video de demostración desde una URL pública
        // En producción: Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video_presentacion)
        Uri uriVideo = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
        vvReproductor.setVideoURI(uriVideo);

        // Evento: video listo para reproducirse
        vvReproductor.setOnPreparedListener(mp -> {
            mp.setLooping(false);
            actualizarEstado("Listo para reproducir");
        });

        // Evento: video terminó de reproducirse
        vvReproductor.setOnCompletionListener(mp -> {
            estaReproduciendo = false;
            btnPlayPause.setText("▶");
            actualizarEstado("Reproducción completada");
        });

        // Evento: error de reproducción
        vvReproductor.setOnErrorListener((mp, what, extra) -> {
            actualizarEstado("Error al cargar el video");
            return true;
        });
    }

    /**
     * Actualiza el texto del estado de reproducción.
     *
     * @param estado  Mensaje descriptivo del estado actual.
     */
    private void actualizarEstado(String estado) {
        tvEstadoVideo.setText("Estado: " + estado);
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos
    // -----------------------------------------------------------------------

    /**
     * Maneja los clics en los controles personalizados del reproductor.
     *
     * @param view  Vista que disparó el evento.
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_play_pause) {
            manejarPlayPause();

        } else if (id == R.id.btn_detener) {
            manejarDetener();

        } else if (id == R.id.btn_reiniciar) {
            manejarReiniciar();
        }
    }

    /** Alterna entre reproducir y pausar el video. */
    private void manejarPlayPause() {
        if (vvReproductor.isPlaying()) {
            vvReproductor.pause();
            estaReproduciendo = false;
            btnPlayPause.setText("▶");
            actualizarEstado("Pausado");
        } else {
            vvReproductor.start();
            estaReproduciendo = true;
            btnPlayPause.setText("⏸");
            actualizarEstado("Reproduciendo...");
        }
    }

    /** Detiene completamente la reproducción y vuelve al inicio. */
    private void manejarDetener() {
        vvReproductor.pause();
        vvReproductor.seekTo(0);
        estaReproduciendo = false;
        btnPlayPause.setText("▶");
        actualizarEstado("Detenido");
    }

    /** Reinicia el video desde el principio y comienza a reproducirlo. */
    private void manejarReiniciar() {
        vvReproductor.seekTo(0);
        vvReproductor.start();
        estaReproduciendo = true;
        btnPlayPause.setText("⏸");
        actualizarEstado("Reproduciendo desde el inicio...");
    }
}
