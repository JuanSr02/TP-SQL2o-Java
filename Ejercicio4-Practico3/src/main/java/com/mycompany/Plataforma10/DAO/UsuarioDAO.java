package com.mycompany.Plataforma10.DAO;

import java.time.LocalDate;
import org.sql2o.Connection;
import util.Sql2oDAO;

public class UsuarioDAO {

    public String comprarViaje(String usuario, int ruta) {
        String sql = "INSERT INTO INFO_USUARIO VALUES (:ruta,:usuario,:fecha)";
        LocalDate date = LocalDate.now();
        String fecha = date.toString();
        try ( Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("usuario", usuario).addParameter("ruta", ruta).addParameter("fecha", fecha).executeUpdate();
        } catch (Exception e) {
            return "Error comprando viaje";
        }
        return "Viaje comprado exitosamente";

    }
}
