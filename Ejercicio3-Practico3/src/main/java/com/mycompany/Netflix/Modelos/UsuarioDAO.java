package com.mycompany.Netflix.Modelos;

import com.mycompany.Netflix.Modelos.Pelicula;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class UsuarioDAO {

    public String insert(Map<String, String> usuarios) {
        String sql = "INSERT INTO usuarios (nombre_de_usuario, contraseña) VALUES (:nombreusuario,:contraseña)";
        for (Map.Entry<String, String> entry : usuarios.entrySet()) {
            try ( Connection con = Sql2oDAO.getSql2o().open()) {
                con.createQuery(sql)
                        .addParameter("nombreusuario", entry.getKey())
                        .addParameter("contraseña", entry.getValue())
                        .executeUpdate();
            } catch (Exception e) {
                return "Fallo al crear el usuario " + entry.getKey();
            }
        }
        return "Usuarios creados exitosamente";

    }

    public String watchMovie(String usuario, String pelicula) {
        String sql = "INSERT INTO info_usuario (pelicula,usuario) VALUES (:pelicula,:usuario)";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("pelicula", pelicula)
                    .addParameter("usuario", usuario)
                    .executeUpdate();
        } catch (Exception e) {
            return "Fallo al ver la pelicula " + pelicula;
        }
        return "Pelicula vista exitosamente";

    }

    public List<Pelicula> getPeliculasVistas(String usuario) {
        String sql = "SELECT p.titulo, p.anio, p.nacion, p.idioma,p.color,p.restriccion,p.resumen,p.nombre FROM pelicula AS p INNER JOIN info_usuario AS i ON p.titulo = i.pelicula WHERE i.usuario = :usuario";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("usuario", usuario)
                    .executeAndFetch(Pelicula.class);
        }
    }

    public List<Pelicula> getPeliculasRecomendadas(List<Pelicula> peliculasVistas) {
        Set<Pelicula> recomendadas = new HashSet<>();

        // Recomendar películas por nación
        for (Pelicula vista : peliculasVistas) {
            List<Pelicula> mismaNacion = getPeliculasPorNacion(vista.getNacion());
            recomendadas.addAll(mismaNacion);
        }
        
        // Recomendar películas por director
        for (Pelicula vista : peliculasVistas) {
            List<Pelicula> mismoDirector = getPeliculasPorDirector(vista.getNombre());
            recomendadas.addAll(mismoDirector);
        }

        // Recomendar películas por actores
        for (Pelicula vista : peliculasVistas) {
            List<Pelicula> mismosActores = getPeliculasPorActores(vista.getTitulo());
            recomendadas.addAll(mismosActores);
        }
        
        // Eliminar las películas ya vistas de la lista de recomendaciones
        List<Pelicula>recomendada  = new ArrayList(recomendadas);
         for (Pelicula vista : peliculasVistas) {
            for (int i=0 ;i<recomendada.size();i++){
                if(vista.getTitulo().equals(recomendada.get(i).getTitulo())){
                    recomendada.remove(i);
                }
            }
        }
        
        return recomendada;
    }

    public List<Pelicula> getPeliculasPorNacion(String nacion) {
        String sql = "SELECT * FROM PELICULA WHERE nacion=:nacion";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("nacion", nacion)
                    .executeAndFetch(Pelicula.class);
        }
    }

    public List<Pelicula> getPeliculasPorDirector(String nombreDirector) {
        String sql = "SELECT * FROM PELICULA WHERE nombre=:nombreDirector";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("nombreDirector", nombreDirector)
                    .executeAndFetch(Pelicula.class);
        }
    }
    
    public List<Pelicula> getPeliculasPorActores(String titulo) {
        String sql = "SELECT p.titulo, p.anio, p.nacion, p.idioma,p.color,p.restriccion,p.resumen,p.nombre,p.urlImagen FROM pelicula p JOIN reparto r ON p.titulo = r.titulo WHERE r.nombre IN (SELECT nombre FROM reparto WHERE titulo = :titulo) AND p.titulo !=:titulo";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("titulo", titulo)
                    .executeAndFetch(Pelicula.class);
        }
    }
}
