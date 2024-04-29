package com.mycompany.EmpresaVTL.Modelo;

import com.mycompany.EmpresaVTL.Modelo.Empleado;
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

    public List<Empleado> getAllEmpleados() {
        String sql = "SELECT * FROM EMPLEADO";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Empleado.class);
        }
    }

    public Empleado getEmpleado(String nombre) {
        String sql = "SELECT * FROM EMPLEADO WHERE nombre = :nombre";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).addParameter("nombre", nombre).executeAndFetchFirst(Empleado.class);
        }
    }

    public String insert(Empleado empleado) {
        String sql = "INSERT INTO EMPLEADO (nombre, categoria, dedicacion, codigo) VALUES (:nombre, :categoria, :dedicacion, :codigo)";
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("nombre", empleado.getNombre())
                    .addParameter("categoria", empleado.getCategoria())
                    .addParameter("dedicacion", empleado.getDedicacion())
                    .addParameter("codigo", empleado.getCodigo())
                    .executeUpdate();
            return "Empleado creado exitosamente";
        } catch (Exception e) {
            return "Fallo al crear el Empleado";
        }
    }

}
