
package com.mycompany.Netflix.Modelos;

import com.mycompany.Netflix.Modelos.Nacion;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;
public class DirectorDAO {

     public List<String> getNacionalidadActoresPorDirector(String director) {
        String sql = "SELECT DISTINCT a.nacionalidad FROM actor a INNER JOIN reparto r ON a.nombre = r.nombre INNER JOIN pelicula p ON r.titulo = p.titulo WHERE p.nombre =:nombre";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("nombre", director).executeAndFetch(String.class);
        }
    }
     
     public List<Nacion> getNacion() {
        String sql = "SELECT * FROM nacion";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Nacion.class);
        }
    }
     

}
