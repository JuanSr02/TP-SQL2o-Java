package com.mycompany.Empresa.Modelo;

import java.util.List;
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
}
