package util;

import lombok.Getter;

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {

        @Getter
        public static final String ACTOR = "/api/netflix/getPeliculasPorActor";
        @Getter
        public static final String DIRECTOR = "/api/netflix/getPeliculasPorDirector";
        @Getter
        public static final String INICIO = "/api/netflix";
        @Getter
        public static final String NACION = "/api/netflix/getNacionActoresPorDirector";
        @Getter
        public static final String VERPELICULA = "/api/netflix/verPelicula";
        @Getter
        public static final String RECOMENDADAS = "/api/netflix/getRecomendadas";

    }

    public static class Template {

        public final static String LAYOUT = "templates/layout.vtl";
        public final static String NETFLIX = "templates/netflix.vtl";
        public final static String NETFLIX2 = "templates/nacion.vtl";


    }

}
