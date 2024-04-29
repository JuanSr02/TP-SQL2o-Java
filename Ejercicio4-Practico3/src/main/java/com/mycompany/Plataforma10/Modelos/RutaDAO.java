package com.mycompany.Plataforma10.Modelos;

import com.mycompany.Plataforma10.Modelos.Ruta;
import com.mycompany.Plataforma10.Modelos.Viaje;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class RutaDAO {

    public List<Ruta> getViajes() {
        String sql = "SELECT * FROM RUTA";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Ruta.class);
        }
    }

    public List<Viaje> getViajesComprados(String usuario) {
        String sql = "SELECT r.idRuta, r.nombreEmpresa ,r.origen, r.destino, r.precio,iu.fecha FROM ruta r INNER JOIN info_usuario iu ON r.idRuta = iu.viaje INNER JOIN usuarios u ON iu.usuario = u.nombre_de_usuario WHERE u.nombre_de_usuario =:usuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("usuario", usuario).executeAndFetch(Viaje.class);
        }
    }
    
    public List<Ruta> getViajesPorOrigenYDestino(String origen,String destino) {
        String sql = "SELECT * FROM ruta WHERE origen =:origen AND destino=:destino";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("origen",origen).addParameter("destino", destino).executeAndFetch(Ruta.class);
        }
    }
    
    
}
