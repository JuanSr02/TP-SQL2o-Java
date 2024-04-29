package util;

import lombok.Getter;

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {

        @Getter
        public static final String AREAS = "/api/empresa/getAreas";
        @Getter
        public static final String EMPLEADOS = "/api/empresa/getEmpleados";
        @Getter
        public static final String AREASDIRECTOR = "/api/empresa/getAreasDirector/id";
        @Getter
        public static final String DEDICACION = "/api/empresa/getEmpleadosDedicacion/dedicacion";
        @Getter
        public static final String AREASCONEMPLEADOS = "/api/empresa/getAreasConEmpleados";
        @Getter
        public static final String EMPLEADOSAREACODIGO = "/api/empresa/empleadosArea";
        @Getter
        public static final String DIRECTORYEMPLEADO = "/api/empresa/getDirectorEmpleado";
        @Getter
        public static final String AGREGARAREA = "/api/empresa/agregarArea";
        @Getter
        public static final String BUSCAREMPLEADO = "/api/empresa/buscarEmpleado";
        @Getter
        public static final String CANTIDADEMPLEADOSPORAREA = "/api/empresa/cantidadEmpleadosPorArea";
        @Getter
        public static final String AGREGAREMPLEADOAREA = "/api/empresa/agregarEmpleado";
        @Getter
        public static final String INICIOGETS = "/api/empresa";

    }

    public static class Template {

        public final static String LAYOUT = "templates/layout.vtl";
        public final static String EMPRESA = "templates/empresa.vtl";

    }

}
