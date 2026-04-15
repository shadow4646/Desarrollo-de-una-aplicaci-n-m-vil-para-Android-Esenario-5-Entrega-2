package com.example.portafolioapp;

/**
 * ModeloFoto — Clase de datos para cada elemento de la galería.
 * Encapsula el título, categoría, descripción detallada y recurso de imagen.
 */
public class ModeloFoto {

    // Atributos del modelo
    private String titulo;
    private String categoria;
    private String descripcion;
    private int    imagenResId;   // ID del recurso drawable

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public ModeloFoto(String titulo, String categoria, String descripcion, int imagenResId) {
        this.titulo       = titulo;
        this.categoria    = categoria;
        this.descripcion  = descripcion;
        this.imagenResId  = imagenResId;
    }

    // -----------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------

    public String getTitulo()      { return titulo; }
    public String getCategoria()   { return categoria; }
    public String getDescripcion() { return descripcion; }
    public int    getImagenResId() { return imagenResId; }
}
