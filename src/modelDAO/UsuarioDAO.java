package modelDAO;

import interfaces.CRUDUsuario;
import java.sql.*;
import java.util.LinkedList;
import model.Conexion;
import model.Usuario;

public class UsuarioDAO implements CRUDUsuario {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario u = new Usuario();
    
    @Override
    public LinkedList<Usuario> listar() {
        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        String sql = "SELECT * FROM usuarios";
        try {
            //Inicializa la base de datos
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setRol(rs.getInt(8));
                user.setCorreo(rs.getString(6));
                user.setTelefono(rs.getInt(4));
                lista.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
        
    }
    
    @Override
    public Usuario list(int id){
        String sql = "SELECT * FROM usuarios WHERE usuario_id = " + id;
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setTelefono(rs.getInt(4));
                u.setDireccion(rs.getString(5));
                u.setCorreo(rs.getString(6));
                u.setFecha(rs.getString(7));
                u.setRol(rs.getInt(8));
                u.setActividad(rs.getInt(9));
                u.setContrasenia(rs.getString(10));
            }
        } catch (Exception e) {
        }
        return u;  
    }
    
    @Override
    public void add(Usuario usuario) {
        String query = "INSERT INTO usuarios VALUES (0,?,?,?,?,?,?,?,?,?);";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getTelefono());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getFecha());
            ps.setInt(7, 2);
            ps.setInt(8, usuario.getActividad());
            ps.setString(9, usuario.getContrasenia());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void edit(Usuario usuario) {
        String query = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=?, correo_electronico=?, fecha_nacimiento=?, activo=?, contrasenia=? WHERE usuario_id=?;";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getTelefono());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getFecha());
            ps.setInt(7, usuario.getActividad());
            ps.setString(8, usuario.getContrasenia());
            ps.setInt(9, usuario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "DELETE from usuarios WHERE usuario_id=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }  
    
}
