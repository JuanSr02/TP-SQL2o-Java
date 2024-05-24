package com.mycompany.PedidosYa.DAO;

import com.mycompany.PedidosYa.Modelos.Carrito;
import com.mycompany.PedidosYa.Modelos.Producto;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class ProductoDAO {

    public List<Producto> getProductos(int idUsuario) {
        String sql = "SELECT p.* FROM producto p LEFT JOIN carrito c ON p.idProducto = c.idProducto AND c.idUsuario = :idUsuario WHERE c.idCarrito IS NULL";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("idUsuario",idUsuario).executeAndFetch(Producto.class);
        }
    }

    public String comprarProducto(int idProducto, int idUsuario) {
        String sql = "INSERT INTO carrito (idUsuario, idProducto, cantProducto) VALUES (:idUsuario,:idProducto,:cantProducto);";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("idUsuario", idUsuario).addParameter("idProducto",idProducto).addParameter("cantProducto", 1).executeUpdate();
        } catch (Exception e) {
            return "Error agregando producto al carrito";
        }
        return "Producto Agregado al Carrito";
    }

    public String agregarProducto(int idUsuario, int idProducto) {
        String sql = "UPDATE carrito SET cantProducto = cantProducto + 1 WHERE idUsuario=:idUsuario AND idProducto=:idProducto";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("idUsuario", idUsuario).addParameter("idProducto", idProducto).executeUpdate();
        } catch (Exception e) {
            return "Error agregando producto al carrito";
        }
        return "Producto Agregado al Carrito";
    }

    public List<Carrito> getCarrito(int idUsuario) {
        String sql = "SELECT producto.*,cantProducto FROM producto JOIN carrito ON producto.idProducto = carrito.idProducto WHERE carrito.idUsuario = :idUsuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("idUsuario", idUsuario).executeAndFetch(Carrito.class);
        }
    }
}
