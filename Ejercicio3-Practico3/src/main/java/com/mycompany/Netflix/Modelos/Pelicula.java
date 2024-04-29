package com.mycompany.Netflix.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Pelicula {

    @NonNull
    private String titulo;
    @NonNull
    private String anio;
    @NonNull
    private String nacion;
    @NonNull
    private String idioma;
    @NonNull
    private String color;
    @NonNull
    private String restriccion;
    @NonNull
    private String resumen;
    @NonNull
    private String nombre;
    @NonNull
    private String urlImagen;

}
