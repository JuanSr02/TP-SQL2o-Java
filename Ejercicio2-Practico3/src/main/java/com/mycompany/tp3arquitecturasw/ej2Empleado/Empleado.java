
package com.mycompany.tp3arquitecturasw.ej2Empleado;

import lombok.Data;

@Data
public class Empleado {

    private String nombre;
    private String categoria;
    private String dedicacion;
    private Integer codigo;

    public Empleado(String nombre, String categoria, String dedicacion, Integer codigo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.dedicacion = dedicacion;
        this.codigo = codigo;
    }
    
}
