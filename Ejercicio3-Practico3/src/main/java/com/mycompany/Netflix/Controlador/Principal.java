package com.mycompany.Netflix.Controlador;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.debug.DebugScreen.enableDebugScreen;
import util.Path;

public class Principal {

    public static void main(String[] args) {

        Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
        enableDebugScreen();

        get(Path.Web.INICIO, NetflixControlador.getInicio);
        get(Path.Web.DIRECTOR, NetflixControlador.getPeliculasPorDirector);
        get(Path.Web.ACTOR, NetflixControlador.getPeliculasPorActor); 
        get(Path.Web.NACION, NetflixControlador.getNacionalidadActoresPorDirector);
        get(Path.Web.RECOMENDADAS, NetflixControlador.getRecomendadas);
        post(Path.Web.VERPELICULA, NetflixControlador.watchMovie);
    }
}
