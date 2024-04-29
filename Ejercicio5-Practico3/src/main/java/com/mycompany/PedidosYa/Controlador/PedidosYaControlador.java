package com.mycompany.PedidosYa.Controlador;

import com.mycompany.PedidosYa.Modelos.PedidoDAO;
import com.mycompany.PedidosYa.Modelos.ProductoDAO;
import com.mycompany.PedidosYa.Modelos.Carrito;
import com.mycompany.PedidosYa.Modelos.Pedido;
import com.mycompany.PedidosYa.Modelos.Producto;
import java.util.ArrayList;
import spark.Request;
import spark.Response;
import spark.Route;
import util.ViewUtil;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Path;

public class PedidosYaControlador {

    final static Logger registraLog = LoggerFactory.getLogger(PedidosYaControlador.class);
    final static int USUARIO = 1;

    // Pantalla de inicio
    public static Route getInicio = (Request request, Response response) -> {
        List<Producto> productos;
        ProductoDAO pDAO = new ProductoDAO();
        productos = pDAO.getProductos(USUARIO);
        HashMap model = new HashMap();
        model.put("productos", productos);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYA);
    };

    //Obtener el carrito de un usuario
    public static Route getCarrito = (Request request, Response response) -> {
        int usuario = USUARIO;
        List<Carrito> productos;
        ProductoDAO pDAO = new ProductoDAO();
        productos = pDAO.getCarrito(usuario);
        double acum = 0;
        for (Carrito p : productos) {
            acum += p.getCantProducto() * p.getPrecio();
        }
        HashMap model = new HashMap();
        model.put("productos", productos);
        model.put("costoTotal", acum);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYACARRITO);
    };

    //Agregar al carrito un producto
    public static Route comprarProducto = (Request request, Response response) -> {
        int usuario = USUARIO;
        ProductoDAO pDAO = new ProductoDAO();
        int p = Integer.parseInt(request.queryParams("idProducto"));
        String ret = pDAO.comprarProducto(p, usuario);
        registraLog.info(ret);
        List<Carrito> productos;
        productos = pDAO.getCarrito(usuario);
        double acum = 0;
        for (Carrito p2 : productos) {
            acum += p2.getCantProducto() * p2.getPrecio();
        }
        HashMap model = new HashMap();
        model.put("productos", productos);
        model.put("costoTotal", acum);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYACARRITO);
    };

    //Agregar + cantidad de un producto al carrito
    public static Route agregarProducto = (Request request, Response response) -> {
        int usuario = USUARIO;
        ProductoDAO pDAO = new ProductoDAO();
        int producto = Integer.parseInt(request.queryParams("idProducto"));
        String ret = pDAO.agregarProducto(usuario, producto);
        registraLog.info(ret);
        List<Carrito> productos;
        productos = pDAO.getCarrito(usuario);
        double acum = 0;
        for (Carrito p2 : productos) {
            acum += p2.getCantProducto() * p2.getPrecio();
        }
        HashMap model = new HashMap();
        model.put("productos", productos);
        model.put("costoTotal", acum);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYACARRITO);
    };

    //Finalizar Pedido
    public static Route finalizarPedido = (Request request, Response response) -> {
        int usuario = USUARIO;
        PedidoDAO pDAO = new PedidoDAO();
        String ret = pDAO.finalizarPedido(usuario);
        registraLog.info(ret);
        List<Carrito> productos;
        ProductoDAO prDAO = new ProductoDAO();
        productos = prDAO.getCarrito(usuario);
        double acum = 0;
        for (Carrito p2 : productos) {
            acum += p2.getCantProducto() * p2.getPrecio();
        }
        HashMap model = new HashMap();
        model.put("productos", productos);
        model.put("costoTotal", acum);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYACARRITO);
    };
    
    //Vaciar Carrito
    public static Route vaciarCarrito = (Request request, Response response) -> {
        int usuario = USUARIO;
        PedidoDAO pDAO = new PedidoDAO();
        String ret = pDAO.vaciarCarrito(usuario);
        registraLog.info(ret);
        List<Carrito> productos;
        ProductoDAO prDAO = new ProductoDAO();
        productos = prDAO.getCarrito(usuario);
        double acum = 0;
        for (Carrito p2 : productos) {
            acum += p2.getCantProducto() * p2.getPrecio();
        }
        HashMap model = new HashMap();
        model.put("productos", productos);
        model.put("costoTotal", acum);
        registraLog.info("Productos {}", productos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYACARRITO);
    };

    // Ver los pedidos que fueron hechos
    public static Route getPedidos = (Request request, Response response) -> {
        int usuario = USUARIO;
        List<Pedido> pedidosA;
        PedidoDAO pDAO = new PedidoDAO();
        pedidosA = pDAO.getPedidos(usuario);
        HashMap model = new HashMap();
        List<Pedido> pedidos = new ArrayList<Pedido>();
        for (Pedido p : pedidosA) {
            p.setPrecio_producto(p.getCantProducto() * p.getPrecio_producto());
            pedidos.add(p);
        }
        model.put("pedidos", pedidos);
        registraLog.info("Pedidos {}", pedidos);
        return ViewUtil.render(request, model, Path.Template.PEDIDOSYAPEDIDO);
    };

}
