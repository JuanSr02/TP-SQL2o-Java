package com.mycompany.PedidosYa.Modelos;

import lombok.*;

@Data
@AllArgsConstructor

public class Producto {

    private int idProducto;
    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;
    private double precio;
    @NonNull
    private String nombre_comercio;
    @NonNull
    private String urlImagen;
}
