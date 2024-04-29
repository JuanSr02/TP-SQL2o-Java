package util;

import lombok.Getter;

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {

        @Getter
        public static final String VIAJES = "/api/plat10/getViajes";
        @Getter
        public static final String COMPRADOS = "/api/plat10/getComprados";
        @Getter
        public static final String INICIO = "/api/plat10";
        @Getter
        public static final String COMPRARVIAJE = "/api/plat10/comprarViaje";
    }

    public static class Template {

        public final static String LAYOUT = "templates/layout.vtl";
        public final static String PLAT10 = "templates/plat10.vtl";
        public final static String PLAT102 = "templates/plat102.vtl";

    }

}
