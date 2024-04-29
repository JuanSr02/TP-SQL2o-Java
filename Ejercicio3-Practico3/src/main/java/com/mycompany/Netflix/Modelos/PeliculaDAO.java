package com.mycompany.Netflix.Modelos;

import com.mycompany.Netflix.Modelos.Pelicula;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class PeliculaDAO {
    
       public List<Pelicula> getPeliculas(String usuario) {
        String sql = "SELECT * FROM pelicula WHERE titulo NOT IN ( SELECT pelicula FROM info_usuario WHERE usuario = :usuario);";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("usuario", usuario).executeAndFetch(Pelicula.class);
        }
    }

    public List<Pelicula> getPeliculasPorDirector(String director) {
        String sql = "SELECT * FROM PELICULA WHERE nombre=:director";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("director", director).executeAndFetch(Pelicula.class);
        }
    }
    
        public List<Pelicula> getPeliculasPorActor(String actor) {
        String sql = "SELECT p.titulo,anio,nacion,idioma,color,restriccion,resumen,p.nombre,p.urlImagen FROM pelicula AS p INNER JOIN reparto AS r ON p.titulo = r.titulo WHERE r.nombre =:nombre";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("nombre", actor).executeAndFetch(Pelicula.class);
        }
    }
        
}
