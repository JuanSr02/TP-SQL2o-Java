/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import lombok.Getter;

/**
 *
 * @author Daniel
 */

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {
        @Getter public static final String AREAS = "/api/empresa/getAreas";
        @Getter public static final String AREASDIRECTOR = "/api/empresa/getAreasDirector/id";
        @Getter public static final String DEDICACION = "/api/empresa/getEmpleadosDedicacion/dedicacion";
        @Getter public static final String AREASCONEMPLEADOS = "/api/empresa/getAreasConEmpleados";
        @Getter public static final String EMPLEADOSAREACODIGO = "/api/empresa/empleadosArea/id";
        @Getter public static final String DIRECTORYEMPLEADO = "/api/empresa/getDirectorEmpleado";
    }
    
    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
     
    }

}
