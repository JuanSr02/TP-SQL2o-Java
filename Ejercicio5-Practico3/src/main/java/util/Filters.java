/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import spark.*;

/**
 *
 * @author Daniel
 */
public class Filters {
    /* Agregar la barra al final de cada solicitud para evitar si es llamado como "path" solo o "path/" */
     public static Filter agregarBarraFinal = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };
}
