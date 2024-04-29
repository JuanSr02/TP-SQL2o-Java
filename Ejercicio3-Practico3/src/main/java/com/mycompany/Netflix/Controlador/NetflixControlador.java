package com.mycompany.Netflix.Controlador;


import com.mycompany.Netflix.Modelos.DirectorDAO;
import com.mycompany.Netflix.Modelos.PeliculaDAO;
import com.mycompany.Netflix.Modelos.UsuarioDAO;
import com.mycompany.Netflix.Modelos.Nacion;
import com.mycompany.Netflix.Modelos.Pelicula;
import java.util.ArrayList;
import spark.Request;
import spark.Response;
import spark.Route;
import util.ViewUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Path;


public class NetflixControlador {
    final static Logger registraLog = LoggerFactory.getLogger(NetflixControlador.class);
    final static String USUARIO = "usuario1";
    
    
    // Pantalla de inicio
    public static Route getInicio = (Request request, Response response) -> {
        List<Pelicula> peliculas;
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        peliculas = peliculaDAO.getPeliculas(USUARIO);
        HashMap model = new HashMap();
        model.put("peliculas", peliculas);
        registraLog.info("Peliculas {}",peliculas);
        return ViewUtil.render(request, model, Path.Template.NETFLIX);
    };

    // Mostrar todas las películas que hay de un director dado.
    public static Route getPeliculasPorDirector = (Request request, Response response) -> {
        String nombreDirector = request.queryParams("param");
        List<Pelicula> peliculas;
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        peliculas = peliculaDAO.getPeliculasPorDirector(nombreDirector);
        HashMap model = new HashMap();
        model.put("peliculas", peliculas);
        registraLog.info("Peliculas del director {} : {}", nombreDirector, peliculas);
        return ViewUtil.render(request, model, Path.Template.NETFLIX);
    };

    // Listar las películas en las que trabaja un actor dado.
    public static Route getPeliculasPorActor = (Request request, Response response) -> {
        String nombreActor = request.queryParams("param");
        List<Pelicula> peliculas;
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        peliculas = peliculaDAO.getPeliculasPorActor(nombreActor);
        HashMap model = new HashMap();
        model.put("peliculas", peliculas);
        registraLog.info("Peliculas con el actor {} : {}", nombreActor, peliculas);
        return ViewUtil.render(request, model, Path.Template.NETFLIX);
    };

    // La nacionalidad de los actores que un director ha dirigido.
    public static Route getNacionalidadActoresPorDirector = (Request request, Response response) -> {
        String nombreDirector = request.queryParams("param");
        List<String> nacionalidades;
        DirectorDAO directorDAO = new DirectorDAO();
        nacionalidades = directorDAO.getNacionalidadActoresPorDirector(nombreDirector);
        HashMap model = new HashMap();
        List<Integer> frecuencia = new ArrayList();
        nacionalidades = contarFrecuencias(nacionalidades,frecuencia);
        List<Nacion> naciones = directorDAO.getNacion();
        model.put("nacionalidades", nacionalidades);
        model.put("frecuencia", frecuencia);
        model.put("naciones",naciones);
        registraLog.info("Nacionalidades de actores dirigidos por {} : {}", nombreDirector, nacionalidades);
        return ViewUtil.render(request, model, Path.Template.NETFLIX2);
    };

    // Listar películas recomendadas
    public static Route getRecomendadas = (Request request, Response response) -> {
        String nombreUsuario = USUARIO;
        // Obtener las películas vistas por el usuario
        List<Pelicula> peliculasVistas;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        peliculasVistas = usuarioDAO.getPeliculasVistas(nombreUsuario);
        // Calcular y obtener las películas recomendadas
        List<Pelicula> peliculasRecomendadas;
        peliculasRecomendadas = usuarioDAO.getPeliculasRecomendadas(peliculasVistas);
        HashMap model = new HashMap();
        model.put("peliculas", peliculasRecomendadas);
        registraLog.info("Peliculas recomendadas para el usuario {}: {}", nombreUsuario, peliculasRecomendadas);
        return ViewUtil.render(request, model, Path.Template.NETFLIX);
    };

    
    public static Route watchMovie = (Request request, Response response) -> {
        String usuario = USUARIO;
        String pelicula = request.queryParams("pelicula");
        // Insertar el usuario y la película en la tabla info_usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String ret = usuarioDAO.watchMovie(usuario,pelicula);
        List<Pelicula> peliculas;
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        peliculas = peliculaDAO.getPeliculas(USUARIO);
        HashMap model = new HashMap();
        model.put("peliculas", peliculas);
        registraLog.info("Peliculas {}",peliculas);
        return ViewUtil.render(request, model, Path.Template.NETFLIX);
    };
    // Métodos auxiliares

    
       private static List<String> contarFrecuencias(List<String> strings, List<Integer> frecuencias) {
        Map<String, Integer> contador = new HashMap<>();
        
        // Contar frecuencias de cada string
        for (String str : strings) {
            contador.put(str, contador.getOrDefault(str, 0) + 1);
        }
        
        // Llenar la lista de frecuencias y preparar la lista de strings sin repetidos
        List<String> sinRepetidos = new ArrayList<>();
        for (Map.Entry<String, Integer> entrada : contador.entrySet()) {
            sinRepetidos.add(entrada.getKey());
            frecuencias.add(entrada.getValue());
        }
        
        return sinRepetidos;
    }

}
