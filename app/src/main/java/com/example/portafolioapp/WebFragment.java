package com.example.portafolioapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * WebFragment — Navegador web integrado con barra de direcciones.
 *
 * Permite cargar cualquier URL introducida por el usuario.
 * Incluye botones de navegación (atrás, adelante, recargar),
 * barra de progreso de carga y el WebView como superficie de renderizado.
 */
public class WebFragment extends Fragment implements View.OnClickListener {

    // -----------------------------------------------------------------------
    // Variables / Vínculos con identificadores XML
    // -----------------------------------------------------------------------
    private EditText    etUrl;           // R.id.et_url
    private Button      btnIr;           // R.id.btn_web_ir
    private Button      btnAtras;        // R.id.btn_web_atras
    private Button      btnAdelante;     // R.id.btn_web_adelante
    private Button      btnRecargar;     // R.id.btn_web_recargar
    private ProgressBar pbCargaWeb;      // R.id.pb_carga_web
    private WebView     wvNavegador;     // R.id.wv_navegador

    // URL de inicio por defecto
    private static final String URL_DEFECTO = "https://www.google.com";

    // -----------------------------------------------------------------------
    // Ciclo de vida
    // -----------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vincular variables con identificadores XML
        etUrl       = view.findViewById(R.id.et_url);
        btnIr       = view.findViewById(R.id.btn_web_ir);
        btnAtras    = view.findViewById(R.id.btn_web_atras);
        btnAdelante = view.findViewById(R.id.btn_web_adelante);
        btnRecargar = view.findViewById(R.id.btn_web_recargar);
        pbCargaWeb  = view.findViewById(R.id.pb_carga_web);
        wvNavegador = view.findViewById(R.id.wv_navegador);

        // Registrar eventos de clic en botones
        btnIr.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        btnAdelante.setOnClickListener(this);
        btnRecargar.setOnClickListener(this);

        // Evento: tecla "IR" del teclado virtual en el campo URL
        etUrl.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                navegarA(etUrl.getText().toString());
                return true;
            }
            return false;
        });

        // Configurar WebView
        configurarWebView();

        // Cargar URL por defecto
        navegarA(URL_DEFECTO);
        etUrl.setText(URL_DEFECTO);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wvNavegador != null) {
            wvNavegador.destroy();
        }
    }

    // -----------------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------------

    /**
     * Configura los ajustes del WebView: JavaScript, zoom,
     * WebViewClient propio y barra de progreso mediante WebChromeClient.
     */
    private void configurarWebView() {
        WebSettings settings = wvNavegador.getSettings();
        settings.setJavaScriptEnabled(true);    // Habilitar JavaScript
        settings.setBuiltInZoomControls(true);  // Permitir zoom
        settings.setDisplayZoomControls(false); // Ocultar botones de zoom

        // WebViewClient: evita que los enlaces abran el navegador externo
        wvNavegador.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // Actualizar la barra de URL al navegar
                etUrl.setText(request.getUrl().toString());
                return false;  // false = el WebView maneja la carga
            }
        });

        // WebChromeClient: captura el progreso de carga
        wvNavegador.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    pbCargaWeb.setVisibility(View.VISIBLE);
                    pbCargaWeb.setProgress(newProgress);
                } else {
                    pbCargaWeb.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Navega a la URL indicada, añadiendo el prefijo "https://" si falta.
     *
     * @param url  URL introducida por el usuario.
     */
    private void navegarA(String url) {
        if (url == null || url.trim().isEmpty()) return;
        url = url.trim();
        // Añadir protocolo si no tiene
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        wvNavegador.loadUrl(url);
        etUrl.setText(url);
    }

    // -----------------------------------------------------------------------
    // Implementación de eventos
    // -----------------------------------------------------------------------

    /**
     * Maneja los clics en la barra de navegación web.
     *
     * @param view  Vista que disparó el evento.
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_web_ir) {
            navegarA(etUrl.getText().toString());

        } else if (id == R.id.btn_web_atras) {
            if (wvNavegador.canGoBack()) {
                wvNavegador.goBack();
            }

        } else if (id == R.id.btn_web_adelante) {
            if (wvNavegador.canGoForward()) {
                wvNavegador.goForward();
            }

        } else if (id == R.id.btn_web_recargar) {
            wvNavegador.reload();
        }
    }
}
