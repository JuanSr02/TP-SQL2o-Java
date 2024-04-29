package com.mycompany.EmpresaVTL.Controlador;

import com.mycompany.EmpresaVTL.Modelo.Area;
import com.mycompany.EmpresaVTL.Modelo.AreaDAO;
import com.mycompany.EmpresaVTL.Modelo.Empleado;
import com.mycompany.EmpresaVTL.Modelo.EmpleadoDAO;
import com.mycompany.EmpresaVTL.Modelo.ResultadoConsulta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;
import util.ViewUtil;

public class EmpresaControlador {

    final static Logger registraLog = LoggerFactory.getLogger(EmpresaControlador.class);

    // Método para cargar toda la información necesaria en la página de inicio
    public static Route getInicio = (Request request, Response response) -> {
        HashMap model = dataNecesaria();
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getAreas = (Request request, Response response) -> { // ITEM 1
        List<Area> areas;
        AreaDAO aDAO = new AreaDAO();
        areas = aDAO.getAllAreas();
        HashMap model = dataNecesaria();
        model.put("areas", areas);
        registraLog.info("getAreas RES {}", areas);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getAreasConDirector = (Request request, Response response) -> {
        List<Area> areas;
        AreaDAO aDAO = new AreaDAO();
        String nbreDirector = request.queryParams("director");
        areas = aDAO.getAreasByDirector(nbreDirector);
        HashMap model = dataNecesaria();
        model.put("areas", areas);
        registraLog.info("getAreasByDirector RES {}", areas);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getAreasConEmpleados = (Request request, Response response) -> {
        List<Area> res;
        AreaDAO aDAO = new AreaDAO();
        res = aDAO.getAreasWithEmployees();
        HashMap model = dataNecesaria();
        model.put("areas", res);
        registraLog.info("getAreasConEmpleados RES {}", res);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getEmpleadosDedicacion = (Request request, Response response) -> {
        List<Empleado> res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        String dedicacion = request.queryParams("dedicacion");
        res = eDAO.getEmpleadosByDedicacion(dedicacion);
        HashMap model = dataNecesaria();
        model.put("empleados", res);
        registraLog.info("getEmpleadosByDedicacion RES {}", res);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getEmpleadosPorCodigoArea = (Request request, Response response) -> { // ITEM 2
        List<Empleado> res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        String codigo = request.queryParams("codigo");
        res = eDAO.getEmpleadosByAreaCodigo(Integer.parseInt(codigo));
        HashMap model = dataNecesaria();
        model.put("empleadosC", res);
        registraLog.info("getEmpleadosByAreaCodigo RES {}", res);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getDirectorEmpleado = (Request request, Response response) -> {
        Empleado res;
        EmpleadoDAO eDAO = new EmpleadoDAO();
        res = eDAO.findDirectorEmpleado();
        HashMap model = dataNecesaria();
        model.put("empleado", res);
        registraLog.info(" RES findDirectorEmpleado {}", res);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route agregarArea = (Request request, Response response) -> { // ITEM 3
        Area area = new Area(Integer.parseInt(request.queryParams("codigo")), request.queryParams("nombre"), request.queryParams("director"));
        AreaDAO areaDAO = new AreaDAO();
        String ret = areaDAO.insert(area); // Llamar al método de inserción en el DAO
        HashMap model = dataNecesaria();

        model.put("mensaje1", ret);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getEmpleados = (Request request, Response response) -> { // ITEM 4

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Obtener todos los empleados del DAO
        HashMap model = dataNecesaria();

        model.put("empleados", empleados);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getEmpleado = (Request request, Response response) -> { // ITEM 5

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        String nbre = request.queryParams("nombre");
        Empleado empleado = empleadoDAO.getEmpleado(nbre);
        HashMap model = dataNecesaria();

        model.put("empleadoB", empleado);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route getCantidadEmpleadosArea = (Request request, Response response) -> { // ITEM 6
        AreaDAO aDAO = new AreaDAO();
        List<ResultadoConsulta> res;
        List<Map<String, Object>> areas = aDAO.getEmpleadosArea(); // Obtener la cantidad de empleados por area del DAO
        res = procesarConsulta(areas);
        HashMap model = dataNecesaria();
        model.put("reporte", res);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static Route agregarEmpleado = (Request request, Response response) -> { // ITEM 7
        Empleado empleado = new Empleado(request.queryParams("nombre"), request.queryParams("dedicacion"), request.queryParams("categoria"), (Integer) Integer.parseInt(request.queryParams("codigo")));

        EmpleadoDAO eDAO = new EmpleadoDAO();
        String ret = eDAO.insert(empleado); // Llamar al método de inserción en el DAO
        HashMap model = dataNecesaria();

        model.put("mensaje", ret);
        return ViewUtil.render(request, model, Path.Template.EMPRESA);
    };

    public static List<ResultadoConsulta> procesarConsulta(List<Map<String, Object>> resultados) {
        List<ResultadoConsulta> listaResultados = new ArrayList<>();
        for (Map<String, Object> fila : resultados) {
            ResultadoConsulta resultado = ResultadoConsulta.fromMap(fila);
            listaResultados.add(resultado);
        }

        return listaResultados;
    }

    public static HashMap dataNecesaria() {
        HashMap model = new HashMap();

        // Obtener todas las áreas
        List<Area> areas;
        AreaDAO aDAO = new AreaDAO();
        areas = aDAO.getAllAreas();
        model.put("areas", areas);

        // Obtener todos los empleados
        List<Empleado> empleados;
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleados = empleadoDAO.getAllEmpleados();
        model.put("empleados", empleados);

        // Obtener la cantidad de empleados por área
        List<ResultadoConsulta> res;
        List<Map<String, Object>> areasConEmpleados = aDAO.getEmpleadosArea();
        res = procesarConsulta(areasConEmpleados);
        model.put("reporte", res);
        model.put("empleadoB", "");
        model.put("mensaje", "");
        model.put("mensaje1", "");
        model.put("empleadosC", new ArrayList());
        return model;
    }
}
