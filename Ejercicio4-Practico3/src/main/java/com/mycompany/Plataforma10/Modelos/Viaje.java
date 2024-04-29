
package com.mycompany.Plataforma10.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor

public class Viaje {
    private int idRuta;
    @NonNull
    private String nombreEmpresa;
    @NonNull
    private String origen;
    @NonNull
    private String destino;
    private double precio;
    @NonNull
    private String fecha;
}
