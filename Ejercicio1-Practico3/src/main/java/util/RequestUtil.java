/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import static spark.Spark.stop;

/**
 *
 * @author Daniel
 */
public class RequestUtil {
    final static Logger registraLog = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    public static Integer getParamId(Request request) {
        String s = request.queryParams("id");
        Integer i = null;
        try {
            i = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            registraLog.error("Falló el formato {}", s, e); // con e imprime la traza de error
            stop();
        }
        return i;
    }
    
    public static String getParamNombre(Request request) {
        return request.params("nombre");
    }

    public static String getQueryUsuarioNombre(Request request) {
        return request.queryParams("usuarioNombre");
    }

    public static Integer getQueryUsuarioNivel(Request request) {
        String s = request.queryParams("usuarioNivel");
        Integer i = null;
        try {
            i = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            registraLog.error("Falló el formato {}", s, e); // con e imprime la traza de error
            stop();
        }
        return i;
    }

    public static String getQueryContrasena(Request request) {
        return request.queryParams("contrasena");
    }

    public static String getSessionUsuarioLogeado(Request request) {
        return request.session().attribute("usuarioLogeado");
    }

    public static Integer getSessionUsuarioNivel(Request request) {
        return request.session().attribute("usuarioNivel");
    }
    
    public static String getQueryLoginRedirect(Request request) {
        return request.queryParams("loginRedirect");
    }

    public static String getSessionLocale(Request request) {
        return request.session().attribute("locale");
    }

    
    public static boolean removeSessionAttrLoggedOut(Request request) {
        Object loggedOut = request.session().attribute("loggedOut");
        request.session().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Request request) {
        String loginRedirect = request.session().attribute("loginRedirect");
        request.session().removeAttribute("loginRedirect");
        return loginRedirect;
    }
    
}
