package com.mycompany.PedidosYa.Modelos;

import com.mycompany.PedidosYa.Modelos.Pedido;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class PedidoDAO {

    public List<Pedido> getPedidos(int idUsuario) {
        String sql = "SELECT p.cantProducto, p.fecha_pedido,pr.nombre AS nombre_producto,pr.precio AS precio_producto, pr.nombre_comercio AS nombre_comercio_producto FROM pedido p INNER JOIN producto pr ON p.idProducto = pr.idProducto WHERE p.idUsuario = :idUsuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("idUsuario", idUsuario).executeAndFetch(Pedido.class);
        }
    }

    public String finalizarPedido(int idUsuario) {
        String sql = "INSERT INTO pedido (idUsuario, idProducto, cantProducto, fecha_pedido) SELECT idUsuario, idProducto, cantProducto, NOW() AS fecha_pedido FROM carrito WHERE idUsuario =:idUsuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("idUsuario", idUsuario).executeUpdate();
        } catch (Exception e) {
            return "Error finalizando pedido";
        }
        sql = "DELETE FROM carrito WHERE idUsuario =:idUsuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("idUsuario", idUsuario).executeUpdate();
        }
        return "Pedido finalizado exitosamente";
    }
    
    public String vaciarCarrito(int idUsuario) {
        String sql = "DELETE FROM carrito WHERE idUsuario =:idUsuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("idUsuario", idUsuario).executeUpdate();
        }
        return "Carrito vaciado";
    }
}
