package com.mycompany.PedidosYa.Modelos;

import lombok.*;

@Data
@AllArgsConstructor

public class Pedido {

    private int cantProducto;
    @NonNull
    private String fecha_pedido;
    @NonNull
    private String nombre_producto;
    private double precio_producto;
    @NonNull
    private String nombre_comercio_producto;
}
