package com.mycompany.Empresa.Modelo;

import com.mycompany.Empresa.Modelo.Empleado;
import java.util.List;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class EmpleadoDAO {

    public List<Empleado> getEmpleadosByDedicacion(String dedicacion) {
        String sql = "SELECT * FROM EMPLEADO WHERE dedicacion = :dedicacion";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("dedicacion", dedicacion)
                    .executeAndFetch(Empleado.class);
        }
    }

    public List<Empleado> getEmpleadosByAreaCodigo(int codigo) {
        String sql = "SELECT * FROM EMPLEADO WHERE codigo = :codigo";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                    .addParameter("codigo", codigo)
                    .executeAndFetch(Empleado.class);
        }
    }

    public Empleado findDirectorEmpleado() {
        String sql = "SELECT * FROM EMPLEADO WHERE nombre IN (SELECT director FROM AREA)";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetchFirst(Empleado.class);
        }
    }

}
