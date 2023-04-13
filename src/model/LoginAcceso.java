package model;

import java.sql.*;
import java.util.LinkedList;
import model.Conexion;

public class LoginAcceso {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String nivel = "null";
    public static String nombre = "";

    public String acceso(String correo, String contrasenia) {
        String sql = "SELECT rol_id, activo, nombre FROM usuarios WHERE correo_electronico = '" + correo + "' AND contrasenia = '" + contrasenia + "'";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rolId = rs.getInt(1);
                int activo = rs.getInt(2);
                nombre = rs.getString(3);
                if (activo == 0) {
                    return "inactivo";
                } else if (rolId == 1) {
                    nivel = "administrador";
                } else if (rolId == 2) {
                    nivel = "gerente";
                } else {
                    nivel = "otro";
                }
            } else {
                return "no encontrado";
            }

        } catch (Exception e) {
            System.out.println("Error consulta acceso: " + e);
        }
        return nivel;
    }

}
