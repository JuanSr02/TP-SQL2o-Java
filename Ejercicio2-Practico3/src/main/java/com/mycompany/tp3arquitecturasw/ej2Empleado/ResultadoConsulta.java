
package com.mycompany.tp3arquitecturasw.ej2Empleado;

import java.util.Map;
import lombok.Data;
import lombok.NonNull;
@Data
public class ResultadoConsulta {
    @NonNull private String nombreArea;
    private int cantidadEmpleados;

    public ResultadoConsulta(String nombreArea, int cantidadEmpleados) {
        this.nombreArea = nombreArea;
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public static ResultadoConsulta fromMap(Map<String, Object> map) {
        String nombreArea = (String) map.get("nombre");
        long cantidadEmpleados = (long) map.get("cantidad");
        return new ResultadoConsulta(nombreArea, (int) cantidadEmpleados);
    }
}
