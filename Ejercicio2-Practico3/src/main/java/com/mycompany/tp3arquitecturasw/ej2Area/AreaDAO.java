package com.mycompany.tp3arquitecturasw.ej2Area;

import java.util.List;
import java.util.Map;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class AreaDAO {

    public List<Area> getAllAreas() {
        String sql = "SELECT * FROM AREA";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Area.class);
        }
    }

    public List<Area> getAreasByDirector(String directorName) {
        String sql = "SELECT * FROM AREA WHERE director = :director";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("director", directorName)
                    .executeAndFetch(Area.class);
        }
    }

    public List<Area> getAreasWithEmployees() {
        String sql = "SELECT DISTINCT a.* FROM AREA a INNER JOIN EMPLEADO e ON a.codigo = e.codigo";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Area.class);
        }
    }

    public String insert(Area area) {
        String sql = "INSERT INTO AREA (codigo, nombre, director) VALUES (:codigo, :nombre, :director)";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("codigo", area.getCodigo())
                    .addParameter("nombre", area.getNombre())
                    .addParameter("director", area.getDirector())
                    .executeUpdate();
            return "Area creada exitosamente";
        } catch (Exception e) {
            return "Fallo al crear el area";
        }
    }

    public List<Map<String, Object>> getEmpleadosArea() {
        String sql = "SELECT a.nombre,COUNT(e.nombre) as cantidad FROM EMPLEADO AS e,AREA AS a WHERE a.codigo = e.codigo GROUP BY a.codigo";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetchTable().asList();
        }
    }

}
