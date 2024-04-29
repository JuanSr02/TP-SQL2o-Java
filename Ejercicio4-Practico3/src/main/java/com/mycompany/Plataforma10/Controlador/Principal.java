package com.mycompany.Plataforma10.Controlador;

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

        get(Path.Web.INICIO, Plat10Controlador.getInicio);
        get(Path.Web.COMPRADOS, Plat10Controlador.getViajesComprados);
        get(Path.Web.VIAJES, Plat10Controlador.getViajesPorOrigenYDestino); 
        post(Path.Web.COMPRARVIAJE, Plat10Controlador.comprarViaje);
    }
}
