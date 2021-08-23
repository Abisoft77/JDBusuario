import static datos.Conexion.*;
import datos.UsuarioDAO;
import java.sql.*;
import java.util.*;
import domain.Usuario;
public class App {

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<>(); //almacenara la lista de objetos
        Connection conn = null;
        try {
            conn = establecerConexion();
            if (conn.getAutoCommit()) conn.setAutoCommit(false);
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario nuevoUsuario = new Usuario("gueva540","IpadPro77");
            usuarioDAO.insertar(nuevoUsuario);

            Usuario actualizarUsuario = new Usuario(3,"abisai77","password");
            usuarioDAO.actualizar(actualizarUsuario);


            conn.commit(); //hacemos commit
        } catch (SQLException er) {
            er.printStackTrace();
            //agregando el bloque rollback
            try {
                conn.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        usuarios.forEach(System.out::println);
    }
}
