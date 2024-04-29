package com.mycompany.Plataforma10.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class EmpresaTransporte {

    @NonNull
    private String nombre;
    @NonNull
    private String pais;
}
