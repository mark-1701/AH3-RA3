package interfaces;

import java.util.LinkedList;
import model.Usuario;

public interface CRUDUsuario {
    public LinkedList<Usuario> listar();
    public Usuario list(int id);
    public void add(Usuario usuario);
    public void edit(Usuario usuario);
    public void eliminar(int id);
}
