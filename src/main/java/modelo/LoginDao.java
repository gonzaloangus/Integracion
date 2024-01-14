package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Conexion;

public class LoginDao {

    private static Usuario obtenerUsuarioDesdeResultSet(ResultSet rs) {
        try {

            Usuario usuario = new Usuario();
            usuario.setRut(rs.getString("Rut"));
            usuario.setUsername(rs.getString("username"));
            usuario.setNombre(rs.getString("Nombre"));
            usuario.setApellido(rs.getString("Apellido"));
            usuario.setTelefono(rs.getString("Telefono"));
            usuario.setFechaIngreso(rs.getDate("FechaIngreso")); 


            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
    }

    public static boolean validar(String username, String password) {
        Connection con;
        Conexion cn = new Conexion();

        PreparedStatement ps;
        ResultSet rs;


        String sql = "SELECT * FROM usuario u INNER JOIN persona p ON u.Rut = p.Rut WHERE u.username = ? AND u.password = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();


            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Usuario obtenerInformacionUsuario(String username, String password) {
        Connection con;
        Conexion cn = new Conexion();

        PreparedStatement ps;
        ResultSet rs;


        String sql = "SELECT u.Rut, u.username, u.password, p.Nombre, p.Apellido, p.Telefono, p.FechaIngreso " +
                "FROM usuario u INNER JOIN persona p ON u.Rut = p.Rut " +
                "WHERE u.username = ? AND u.password = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {

                return obtenerUsuarioDesdeResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
    }
}
