package com.example.portafolioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * BotonesFragment — Demostración de controles de botón y sus eventos.
 *
 * Incluye los siguientes tipos de controles según el diseño del proyecto:
 *   1. Button          — botón estándar (Compartir, Contactar)
 *   2. ToggleButton    — activa/desactiva opciones (Notificaciones, Modo oscuro)
 *   3. RadioButton     — selección única dentro de un RadioGroup (disponibilidad)
 *   4. CheckBox        — selección múltiple (lenguajes de programación)
 *   5. Switch          — interruptor on/off (visibilidad del portafolio)
 *
 * Cada evento muestra retroalimentación en el panel de resultado inferior.
 */
public class BotonesFragment extends Fragment implements View.OnClickListener {

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------

    // 1. Buttons
    private Button       btnCompartir;          // R.id.btn_compartir
    private Button       btnContactar;           // R.id.btn_contactar
    private Button       btnDescargar;           // R.id.btn_descargar
    private Button       btnLimpiar;             // R.id.btn_limpiar

    // 2. ToggleButtons
    private ToggleButton toggleNotificaciones;   // R.id.toggle_notificaciones
    private ToggleButton toggleModoOscuro;       // R.id.toggle_modo_oscuro

    // 3. RadioGroup + RadioButtons
    private RadioGroup   rgDisponibilidad;       // R.id.rg_disponibilidad

    // 4. CheckBoxes
    private CheckBox     cbJava;                 // R.id.cb_java
    private CheckBox     cbKotlin;               // R.id.cb_kotlin
    private CheckBox     cbPython;               // R.id.cb_python

    // 5. Switches
    private Switch       swPortafolioPublico;    // R.id.sw_portafolio_publico
    private Switch       swContactoDirecto;      // R.id.sw_contacto_directo

    // Panel de resultado
    private TextView     tvResultado;            // R.id.tv_resultado

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_botones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        btnCompartir        = view.findViewById(R.id.btn_compartir);
        btnContactar        = view.findViewById(R.id.btn_contactar);
        btnDescargar        = view.findViewById(R.id.btn_descargar);
        btnLimpiar          = view.findViewById(R.id.btn_limpiar);
        toggleNotificaciones= view.findViewById(R.id.toggle_notificaciones);
        toggleModoOscuro    = view.findViewById(R.id.toggle_modo_oscuro);
        rgDisponibilidad    = view.findViewById(R.id.rg_disponibilidad);
        cbJava              = view.findViewById(R.id.cb_java);
        cbKotlin            = view.findViewById(R.id.cb_kotlin);
        cbPython            = view.findViewById(R.id.cb_python);
        swPortafolioPublico = view.findViewById(R.id.sw_portafolio_publico);
        swContactoDirecto   = view.findViewById(R.id.sw_contacto_directo);
        tvResultado         = view.findViewById(R.id.tv_resultado);

        // Registrar eventos — Buttons
        btnCompartir.setOnClickListener(this);
        btnContactar.setOnClickListener(this);
        btnDescargar.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);

        // Registrar eventos — ToggleButtons
        toggleNotificaciones.setOnCheckedChangeListener(this::onToggleChanged);
        toggleModoOscuro.setOnCheckedChangeListener(this::onToggleChanged);

        // Registrar eventos — RadioGroup
        rgDisponibilidad.setOnCheckedChangeListener(this::onRadioChanged);

        // Registrar eventos — CheckBoxes
        cbJava.setOnCheckedChangeListener(this::onCheckChanged);
        cbKotlin.setOnCheckedChangeListener(this::onCheckChanged);
        cbPython.setOnCheckedChangeListener(this::onCheckChanged);

        // Registrar eventos — Switches
        swPortafolioPublico.setOnCheckedChangeListener(this::onSwitchChanged);
        swContactoDirecto.setOnCheckedChangeListener(this::onSwitchChanged);
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos — View.OnClickListener (Buttons)
    // -----------------------------------------------------------------------

    /**
     * Enruta cada clic de botón estándar al método correspondiente.
     *
     * @param view  Vista que disparó el evento de clic.
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_compartir) {
            accionCompartir();
        } else if (id == R.id.btn_contactar) {
            accionContactar();
        } else if (id == R.id.btn_descargar) {
            accionDescargar();
        } else if (id == R.id.btn_limpiar) {
            accionLimpiar();
        }
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos — ToggleButton
    // -----------------------------------------------------------------------

    /**
     * Maneja el cambio de estado de los ToggleButtons.
     *
     * @param boton     ToggleButton que cambió.
     * @param activado  true si quedó activado, false si se desactivó.
     */
    private void onToggleChanged(CompoundButton boton, boolean activado) {
        int id = boton.getId();
        String estado = activado ? "ACTIVADO" : "DESACTIVADO";

        if (id == R.id.toggle_notificaciones) {
            mostrarResultado("ToggleButton — Notificaciones: " + estado);
        } else if (id == R.id.toggle_modo_oscuro) {
            mostrarResultado("ToggleButton — Modo oscuro: " + estado);
        }
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos — RadioGroup
    // -----------------------------------------------------------------------

    /**
     * Maneja la selección dentro del RadioGroup de disponibilidad.
     *
     * @param grupo     RadioGroup que cambió.
     * @param checkedId ID del RadioButton seleccionado.
     */
    private void onRadioChanged(RadioGroup grupo, int checkedId) {
        String opcion = "Desconocida";
        if (checkedId == R.id.rb_disponible)  opcion = "Disponible para proyectos";
        else if (checkedId == R.id.rb_ocupado)  opcion = "Ocupado actualmente";
        else if (checkedId == R.id.rb_freelance) opcion = "Disponible como Freelance";

        mostrarResultado("RadioButton — Estado laboral: " + opcion);
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos — CheckBox
    // -----------------------------------------------------------------------

    /**
     * Maneja el cambio de estado de los CheckBoxes de lenguajes.
     *
     * @param boton     CheckBox que cambió.
     * @param marcado   true si quedó marcado, false si se desmarcó.
     */
    private void onCheckChanged(CompoundButton boton, boolean marcado) {
        String lenguaje  = boton.getText().toString();
        String estado    = marcado ? "seleccionado" : "deseleccionado";

        // Construir la lista de lenguajes actualmente seleccionados
        StringBuilder seleccionados = new StringBuilder();
        if (cbJava.isChecked())   seleccionados.append("Java ");
        if (cbKotlin.isChecked()) seleccionados.append("Kotlin ");
        if (cbPython.isChecked()) seleccionados.append("Python ");

        mostrarResultado("CheckBox — " + lenguaje + " " + estado
                + "\nLenguajes activos: "
                + (seleccionados.length() > 0 ? seleccionados.toString().trim() : "ninguno"));
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos — Switch
    // -----------------------------------------------------------------------

    /**
     * Maneja el cambio de estado de los Switches.
     *
     * @param boton     Switch que cambió.
     * @param activado  true si quedó activado, false si se desactivó.
     */
    private void onSwitchChanged(CompoundButton boton, boolean activado) {
        int id = boton.getId();
        String estado = activado ? "ON" : "OFF";

        if (id == R.id.sw_portafolio_publico) {
            mostrarResultado("Switch — Portafolio público: " + estado
                    + "\n" + (activado
                            ? "Tu portafolio es visible para todos."
                            : "Tu portafolio está oculto."));
        } else if (id == R.id.sw_contacto_directo) {
            mostrarResultado("Switch — Contacto directo: " + estado
                    + "\n" + (activado
                            ? "Los reclutadores pueden contactarte directamente."
                            : "Contacto directo deshabilitado."));
        }
    }

    // -----------------------------------------------------------------------
    // Métodos de acción (Buttons)
    // -----------------------------------------------------------------------

    /** Abre el selector del sistema para compartir el perfil. */
    private void accionCompartir() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Perfil Profesional — Mateo Moncada");
        intent.putExtra(Intent.EXTRA_TEXT,
                "Te comparto mi portafolio profesional.\n" +
                "Nombre: Mateo Moncada Moncada\n" +
                "Cargo: Ingeniero de Software\n" +
                "Email: mateo.moncada@email.com");
        startActivity(Intent.createChooser(intent, "Compartir perfil mediante..."));
        mostrarResultado("Button — Compartir perfil: abriendo selector de apps...");
    }

    /** Abre el cliente de correo con datos del contacto prellenados. */
    private void accionContactar() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:mateo.moncada@email.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contacto desde PortafolioApp");
        intent.putExtra(Intent.EXTRA_TEXT, "Hola Mateo,\n\nMe pongo en contacto contigo porque...");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        mostrarResultado("Button — Contactar: abriendo correo a mateo.moncada@email.com");
    }

    /** Simula la descarga del CV. */
    private void accionDescargar() {
        mostrarResultado("Button — Descargar CV\n" +
                "Archivo: Mateo_Moncada_CV_2024.pdf\n" +
                "Tamaño: 245 KB\n" +
                "✓ Descarga completada en /Descargas/");
    }

    /** Restaura el panel de resultado al estado inicial. */
    private void accionLimpiar() {
        tvResultado.setText(R.string.resultado_default);
    }

    // -----------------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------------

    /**
     * Muestra un mensaje en el panel de resultado.
     *
     * @param mensaje  Texto descriptivo de la acción ejecutada.
     */
    private void mostrarResultado(String mensaje) {
        tvResultado.setText(mensaje);
    }
}
