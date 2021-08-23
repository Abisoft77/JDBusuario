import datos.UsuarioDAO;
import domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<>(); //almacenara la lista de objetos
        UsuarioDAO usuario = new UsuarioDAO();

        //Crear usuario
        Usuario user = new Usuario(3);

        //Actualizar Usuario
        //usuario.actualizar(user);

        //eliminar usuario
        usuario.eliminar(user);

        //usuario.insertar(user);

        usuarios = usuario.obtenerDatos();
        usuarios.forEach(System.out::println);

    }
}
