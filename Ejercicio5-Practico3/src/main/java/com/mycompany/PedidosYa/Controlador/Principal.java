package com.mycompany.PedidosYa.Controlador;

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

        get(Path.Web.INICIO, PedidosYaControlador.getInicio);
        get(Path.Web.CARRITO, PedidosYaControlador.getCarrito);
        post(Path.Web.COMPRARPRODUCTO, PedidosYaControlador.comprarProducto);
        post(Path.Web.AGREGARPRODUCTO, PedidosYaControlador.agregarProducto);
        post(Path.Web.FINALIZAR, PedidosYaControlador.finalizarPedido);
        post(Path.Web.VACIAR, PedidosYaControlador.vaciarCarrito);
        get(Path.Web.PEDIDOS, PedidosYaControlador.getPedidos);

    }
}
