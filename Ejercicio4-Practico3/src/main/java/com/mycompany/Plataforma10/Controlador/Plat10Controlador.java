package com.mycompany.Plataforma10.Controlador;

import com.mycompany.Plataforma10.Modelos.RutaDAO;
import com.mycompany.Plataforma10.Modelos.UsuarioDAO;
import com.mycompany.Plataforma10.Modelos.Ruta;
import com.mycompany.Plataforma10.Modelos.Viaje;
import spark.Request;
import spark.Response;
import spark.Route;
import util.ViewUtil;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Path;

public class Plat10Controlador {

    final static Logger registraLog = LoggerFactory.getLogger(Plat10Controlador.class);
    final static String USUARIO = "usuario1";

    // Pantalla de inicio
    public static Route getInicio = (Request request, Response response) -> {
        List<Ruta> viajes;
        RutaDAO rutaDAO = new RutaDAO();
        viajes = rutaDAO.getViajes();
        HashMap model = new HashMap();
        model.put("viajes", viajes);
        model.put("text","Disponibles");
        registraLog.info("Viajes {}", viajes);
        return ViewUtil.render(request, model, Path.Template.PLAT10);
    };

    //Listar los viajes comprados por un usuario.
    public static Route getViajesComprados = (Request request, Response response) -> {
        String usuario = USUARIO;
        List<Viaje> viajes;
        RutaDAO rutaDAO = new RutaDAO();
        viajes = rutaDAO.getViajesComprados(usuario);
        HashMap model = new HashMap();
        model.put("viajes", viajes);
         model.put("text","Comprados");
        registraLog.info("Viajes {}", viajes);
        return ViewUtil.render(request, model, Path.Template.PLAT10);
    };

    // Listar los viajes que salen desde un origen elegido
    public static Route getViajesPorOrigenYDestino = (Request request, Response response) -> {
        String origen = request.queryParams("origen");
        String destino = request.queryParams("destino");
        List<Ruta> viajes;
        RutaDAO rutaDAO = new RutaDAO();
        viajes = rutaDAO.getViajesPorOrigenYDestino(origen, destino);
        HashMap model = new HashMap();
        model.put("viajes", viajes);
        model.put("text", "Buscados");
        registraLog.info("Viajes {}", viajes);
        return ViewUtil.render(request, model, Path.Template.PLAT10);
    };

    //Comprar un viaje
    public static Route comprarViaje = (Request request, Response response) -> {
        String usuario = USUARIO;
        int ruta = Integer.parseInt(request.queryParams("ruta"));
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String ret = usuarioDAO.comprarViaje(usuario, ruta);
        registraLog.info(ret);
        List<Ruta> viajes;
        RutaDAO rutaDAO = new RutaDAO();
        viajes = rutaDAO.getViajes();
        HashMap model = new HashMap();
        model.put("viajes", viajes);
        model.put("text","Disponibles");
        registraLog.info("Viajes {}", viajes);
        return ViewUtil.render(request, model, Path.Template.PLAT10);
    };

}
