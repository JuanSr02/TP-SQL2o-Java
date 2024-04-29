package com.mycompany.Empresa.Controlador;

import com.mycompany.Empresa.Modelo.Area;
import com.mycompany.Empresa.Modelo.AreaDAO;
import com.mycompany.Empresa.Modelo.Empleado;
import com.mycompany.Empresa.Modelo.EmpleadoDAO;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class EmpresaControlador {

    final static Logger registraLog = LoggerFactory.getLogger(EmpresaControlador.class);

    public static Route getAreas = (Request request, Response response) -> {

        List<Area> res;
        AreaDAO aDAO = new AreaDAO();
        res = aDAO.getAllAreas();
        registraLog.info("getAreas RES {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };
    public static Route getAreasConDirector = (Request request, Response response) -> {

        List<Area> res;
        AreaDAO aDAO = new AreaDAO();
        String nbreDirector = request.queryParams("director");
        res = aDAO.getAreasByDirector(nbreDirector);
        registraLog.info("getAreasByDirector RES {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };

    public static Route getAreasConEmpleados = (Request request, Response response) -> {

        List<Area> res;
        AreaDAO aDAO = new AreaDAO();
        res = aDAO.getAreasWithEmployees();
        registraLog.info("getAreasConEmpleados RES {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };

    public static Route getEmpleadosDedicacion = (Request request, Response response) -> {

        List<Empleado> res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        String dedicacion = request.queryParams("dedicacion");
        res = eDAO.getEmpleadosByDedicacion(dedicacion);
        registraLog.info("getEmpleadosByDedicacion RES {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };

    public static Route getEmpleadosPorCodigoArea = (Request request, Response response) -> {

        List<Empleado> res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        String codigo = request.queryParams("codigo");
        res = eDAO.getEmpleadosByAreaCodigo(Integer.parseInt(codigo));
        registraLog.info("getEmpleadosByAreaCodigo RES {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };
    public static Route getDirectorEmpleado = (Request request, Response response) -> {

        Empleado res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        res = eDAO.findDirectorEmpleado();
        registraLog.info(" RES findDirectorEmpleado {}", res);
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(res);

    };

}
